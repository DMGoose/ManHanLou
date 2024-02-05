package com.itheima.mhl.Service;

import com.itheima.mhl.DAO.BillDAO;
import com.itheima.mhl.DAO.MenuDAO;
import com.itheima.mhl.DAO.MultiTableDAO;
import com.itheima.mhl.Javabean.Bill;
import com.itheima.mhl.Javabean.DinningTable;
import com.itheima.mhl.Javabean.Menu;
import com.itheima.mhl.Javabean.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * 处理和账单相关的业务逻辑
 */
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DinningTableService dinningTableService = new DinningTableService();
    private String billId = UUID.randomUUID().toString();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    //编写点餐的方法  --》生成一个账单
        //还需要更新对应的餐桌的状态
    public boolean orderMeal(int menuId, int nums, int dinningTableId){   //如果成功返回true，否则返回false
        //1.生成一个账单号，用UUID,写在外面了

        //2.将账单生成到bill表
        int update = billDAO.update("insert into bill values (null,?, ?, ?, ?, ?, now(),'未结账')",
                billId, menuId, nums, menuService.getMenuObjectById(menuId).getPrice() * nums, dinningTableId);
        if (update <= 0){
            return false;
        }
        //3.更新对应的餐桌的状态
        return dinningTableService.updateDinningTableState2(dinningTableId,"就餐中");
    }

    public boolean addMeal(int menuId, int nums, int dinningTableId){   //如果成功返回true，否则返回false
        //1.将账单生成到bill表
        int update = billDAO.update("update bill set (money = money + ?), (num = num + ?) where dinningTableId = ?",
                menuService.getMenuObjectById(menuId).getPrice() * nums, nums,dinningTableId);
        return update > 0;
    }


    //返回所有的账单，提供给view调用
    public List<Bill> list(){
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasBillsOrNot(int dinningTableId){
        double sum = 0;
        Bill bill = billDAO.querySingle
                ("SELECT * FROM bill where diningTableId = ? and state = '未结账' LIMIT 0 , 1", Bill.class, dinningTableId);
        return bill != null;
    }

    //修改状态，完成结账
        // 成功返回true，失败返回false
    public boolean payBill(String state, int diningTableId ){
        //1.修改bill表
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账'",state,diningTableId);
        if (update <= 0){
            return false;
        }

        //2.修改dinningTable为空闲,写在dinningTableService，体现各司其职
        if (!dinningTableService.updateDinningTableStateToFree("空",diningTableId)){
            return false;
        }
        return true;
    }

    //返回多表查询的账单，提供给view调用
    public List<MultiTableBean> list2(){
        return multiTableDAO.queryMulti("SELECT bill.*, name, menu.price FROM bill, menu where bill.menuId = menu.id", MultiTableBean.class);
    }
}
