package com.itheima.mhl.View;

import com.itheima.mhl.Javabean.*;
import com.itheima.mhl.Service.BillService;
import com.itheima.mhl.Service.DinningTableService;
import com.itheima.mhl.Service.EmployeeService;
import com.itheima.mhl.Service.MenuService;
import com.itheima.mhl.Utils.Utility;
import jdk.jshell.execution.Util;

import java.util.List;

/**
 * 这是主界面
 */
public class MhlView {
    private boolean loop = true;//控制是否退出菜单
    private boolean loop2 = true;//控制二级菜单
    private String key = ""; //接受用户的选择
    //定义EmpService对象
    private EmployeeService employeeService = new EmployeeService();
    //定义DinningTableService对象
    private DinningTableService dinningTableService = new DinningTableService();
    //定义MenuService对象
    private MenuService menuService = new MenuService();
    //定义BillService对象
    private BillService billService = new BillService();

    public static void main(String[] args) {
        MhlView mhlView = new MhlView();
        mhlView.mainMenu();
    }

    //完成点餐，要判断菜id是否合法
    public void orderMenu(){
        System.out.println("==========Order Service==========");

        System.out.println("Please enter the number of the table(-1 exit)");
        int orderDinningTableId = Utility.readInt();
        if (orderDinningTableId == -1) {
            System.out.println("Order Canceled");
            return;
        }
        while(true) {
            //验证餐桌号是否存在
            DinningTable dinningTable = dinningTableService.tableExist(orderDinningTableId);
            String state = dinningTable.getState();
            if (dinningTable == null || !(state.equals("空"))) {
                System.out.println("The Table does not exist or not available");
                break;
            }

            System.out.println("Please enter the dish number");
            int menuId = Utility.readInt();
            if (menuId == -1){
                System.out.println("Order Canceled");
                return;
            }
            //验证菜品是否存在
            Menu menu = menuService.getMenuObjectById(menuId);
            if (menu == null){
                System.out.println("The Dish is not exist, Please try again");
                continue;
            }

            System.out.println("Please enter the quantity of dishes (-1 exit)");
            int nums = Utility.readInt();
            if (nums == -1) {
                System.out.println("Order Canceled");
                return;
            }

            //终于开始点餐了
            boolean orderSuccessOrNot = billService.orderMeal(menuId, nums, orderDinningTableId);
            if (orderSuccessOrNot){
                System.out.println("Ordered successfully");
                return;
            }else {
                System.out.println("Ordered failed");
            }
        }
    }

    //完成加菜服务
    public void addDish(){
        System.out.println("==========加菜服务==========");

        System.out.println("请输入加菜的桌号(-1表示退出)");
        int orderDinningTableId = Utility.readInt();
        if (orderDinningTableId == -1) {
            System.out.println("取消加菜");
            return;
        }

        while(true) {
            //验证餐桌号是否存在
            DinningTable dinningTable = dinningTableService.tableExist(orderDinningTableId);
            if (dinningTable == null) {
                System.out.println("餐桌号不存在");
                break;
            }
            System.out.println("请输入菜品编号");
            int menuId = Utility.readInt();
            if (menuId == -1){
                System.out.println("取消加菜");
                return;
            }
            //验证菜品是否存在
            Menu menu = menuService.getMenuObjectById(menuId);
            if (menu == null){
                System.out.println("您输入的菜品不存在，请重新输入");
                continue;
            }

            System.out.println("请输入该菜品的数量(-1表示退出)");
            int nums = Utility.readInt();
            if (nums == -1) {
                System.out.println("取消加菜");
                return;
            }

            //终于开始点餐了
            boolean orderSuccessOrNot = billService.orderMeal(menuId, nums, orderDinningTableId);
            if (orderSuccessOrNot){
                System.out.println("加菜成功");
                return;
            }else {
                System.out.println("加菜失败");
            }
        }
    }

    //显示账单信息
    public void listBill() {
//        List<Bill> allBills = billService.list();
//        System.out.println("\n编号\t\t菜品号\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
//        for (Bill allBill : allBills) {
//            System.out.println(allBill);
//        }
//        System.out.println("============显示完毕============");
//    }
        List<MultiTableBean> multiTableBeans = billService.list2();
        System.out.println("\n编号\t\t菜品号\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t\t菜品名\t\t\t价格");
        for (MultiTableBean multiTableBean : multiTableBeans) {
            System.out.println(multiTableBean);
        }
    }

        //完成结账
    public void payBill(){

        //如果这里使用事务的话，需要用ThreadLocal来解决，框架中比如mybatis提供了事务支持
        System.out.println("==========结账服务==========");
        System.out.println("请输入您要结账的桌号(-1表示退出)");
        int dinningTableId = Utility.readInt();
        if (dinningTableId == -1){
            System.out.println("取消结账");
            return;
        }

        //验证餐桌是否存在
        DinningTable dinningTable = dinningTableService.tableExist(dinningTableId);
        if (dinningTable == null){
            System.out.println("该餐桌不存在");
            return;
        }

        //验证餐桌是否有需要结账的账单
        if (!billService.hasBillsOrNot(dinningTableId)) {
            System.out.println("该餐桌无未结账菜单");
            return;
        }

        System.out.println("请选择结账方式(现金、支付宝、微信)，回车表示退出");
        String state = Utility.readString(20,"");  //如果回车，接收到的就是返回""
        if ("".equals(state)){
            System.out.println("取消结账");
            return;
        }
        System.out.println("请确认是否要结账");
        char key = Utility.readConfirmSelection();
        if (key == 'Y'){  //结账
            if(billService.payBill(state, dinningTableId)){
                System.out.println("完成结账");
            }else {
                System.out.println("结账失败");
            }
        }else {
            System.out.println("取消结账");
            return;
        }
    }

    //显示主菜单的方法
    public void mainMenu(){

        while(loop){
            System.out.println("==========满汉楼==========");
            System.out.println("\t\t 1 Log in 满汉楼");
            System.out.println("\t\t 2 Log out 满汉楼");
            System.out.println("Input your choice：");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("Login 满汉楼");
                    System.out.println("Input employee number:");
                    String empId = Utility.readString(50);
                    System.out.println("Input password:");
                    String psw = Utility.readString(50);
                    Employee emp = employeeService.getEmployeeByIdAndPsw(empId, psw);
                    //接下来要到数据库去判断，一会再写,先把结构写完，我假设它密码是123
                    if(emp != null){  //说明存在该用户
                        System.out.println("==========Login Successfully[" + emp.getName()+ "]==========\t");
                        //显示二级菜单,这里二级菜单也是一个循环，所以做成while
                        while(loop2){
                            System.out.println("==========满汉楼 Second-level Menu==========");
                            System.out.println("\t\t 1 Show Table State");
                            System.out.println("\t\t 2 Make a Reservation");
                            System.out.println("\t\t 3 Show all the dishes");
                            System.out.println("\t\t 4 Order Service");
                            System.out.println("\t\t 5 Check the Bills");
                            System.out.println("\t\t 6 Pay the Bills");
                            System.out.println("\t\t 7 Adding Service");
                            System.out.println("\t\t 9 Back");
                            System.out.println("Input your Choice");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("Show Table State");
                                    dinningTableService.showDinningTableState();
                                    break;
                                case "2":
                                    System.out.println("Make a Reservation");
                                    dinningTableService.orderDinningTable();
                                    break;
                                case "3":
                                    System.out.println("Show all the dishes");
                                    menuService.showMenu();
                                    break;
                                case "4":
                                    System.out.println("Order Service");
                                    orderMenu();
                                    break;
                                case "5":
                                    System.out.println("Check the Bills");
                                    listBill();
                                    break;
                                case "6":
                                    System.out.println("Pay the Bills");
                                    payBill();
                                    break;
                                case   "7":
                                    System.out.println("Adding Service");
                                    addDish();
                                    break;
                                case "9":
                                    loop2 = false;
                                    break;
                                default:
                                    System.out.println("Input Error, Please Try Again");
                                    break;
                            }
                        }
                    }else{
                        System.out.println("==========Login Failed==========");
                    }
                    break;
                case "2":
                    System.out.println("Log out 满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("Input Error, Please Try Again");
                    break;
            }
        }
    }
}
