package com.itheima.mhl.Service;

import com.itheima.mhl.DAO.DinningTableDAO;
import com.itheima.mhl.DAO.MenuDAO;
import com.itheima.mhl.Javabean.DinningTable;
import com.itheima.mhl.Javabean.Menu;
import com.itheima.mhl.Utils.Utility;

import java.util.List;

public class DinningTableService {  //业务层
    //定义一个DinningTableDAO对象
    DinningTableDAO dinningTableDAO = new DinningTableDAO();

    //返回所有餐桌的状态
    public List<DinningTable> list(){
        List<DinningTable> dinningTables =
                dinningTableDAO.queryMulti("select id, state from dinningTable", DinningTable.class);
        return dinningTables;
    }

    //显示所有餐桌状态
    public void showDinningTableState(){
        List<DinningTable> list = list();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DinningTable dinningTable : list) {
            System.out.println(dinningTable);
        }
        System.out.println("==============显示完毕==========");
    }

    //判断餐桌是否存在，根据id,查询id对应的餐桌DinningTable对象
      // (不是看id存不存在，而是看id对应的对象存不存在) 如果DinningTable对象返回null，表示id编号对应的餐桌不存在
    public DinningTable tableExist(int id){
        DinningTable dinningTable =
                dinningTableDAO.querySingle("select * from dinningTable where id = ? ", DinningTable.class, id);
        //最好自己把sql语句放到Navicat查询里检查一下
        return dinningTable;
    }

    //修改餐桌状态1，把当前餐桌状态修改为可预订，调用该方法，对其状态进行更新
        //  包括预定人的名字和电话
    public boolean updateDinningTableState(int id, String orderName,String orderTel){
        int update = dinningTableDAO.update
                ("update dinningTable set state = '已经预定', orderName = ? ,orderTel = ? where id = ?", orderName, orderTel,id);
        return update>0;
    }

    //修改餐桌状态2，把当前餐桌状态修改为就餐中
    public boolean updateDinningTableState2(int id, String state){
        int update = dinningTableDAO.update("update dinningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    //修改餐桌状态3,纯纯的修改
    public boolean updateDinningTableState3(String state, int diningTableId ) {
        //修改dinningTable表
        int update = dinningTableDAO.update
                ("update diningTable set state = ? where id = ?"
                        ,state,diningTableId);
        return update > 0;
    }

    //提供方法，修改餐桌为空闲状态
    public boolean updateDinningTableStateToFree(String state, int diningTableId ) {
        //修改dinningTable表
        int update = dinningTableDAO.update
                ("update dinningTable set state = ?, orderName = '', orderTel = '' where id = ?"
                        ,state,diningTableId);
        return update > 0;
    }

    //完成订座
    public void orderDinningTable(){
        System.out.println("===========预定餐桌===========");
        System.out.println("目前的餐桌状态为:");
        showDinningTableState();
        System.out.println("确认是否预定(Y/N)");
        char key = Utility.readConfirmSelection();//该方法得到的是Y或者N
        System.out.println("请选择要预定的餐桌编号(-1退出)");
        int orderId = Utility.readInt();

        if(orderId == -1){
            System.out.println("=========取消预定========");
            return;
        }

        if(key == 'Y'){  //要预定
            //根据orderId返回对应的DinningTable对象，如果为null，说明该对象不存在
            DinningTable dinningTable = tableExist(orderId);
            if (dinningTable == null){
                System.out.println("你预定的餐桌不存在");
                return;
            }
            //判断该餐桌的状态是否为 “空”，也就是没人预定,然后取反
            if (!("空".equals(dinningTable.getState()))){   //说明当前的餐桌不是 “空”，就意味着被人预定了，你就不能预定
                System.out.println("该餐桌不可预订");
                return;
            }

            //这时说明可以真的预定，更新餐桌状态
            System.out.println("该餐桌可以预定,请输入预定人的姓名");
            String orderName = Utility.readString(50);
            System.out.println("请输入预定人的电话");
            String orderTel = Utility.readString(50);
            if(updateDinningTableState(orderId, orderName, orderTel)){
                System.out.println("预定成功");
            }else {
                System.out.println("预定失败");
            }

        }else {
            System.out.println("=========取消预定========");
        }
    }
}
