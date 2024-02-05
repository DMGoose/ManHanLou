package com.itheima.mhl.Service;

import com.itheima.mhl.DAO.MenuDAO;
import com.itheima.mhl.Javabean.Menu;

import java.util.List;

public class MenuService {
    MenuDAO menuDAO = new MenuDAO();

    //显示菜单
    public void showMenu() {
        List<Menu> menus = menuDAO.queryMulti("select * from menu", Menu.class);
        System.out.println("id\t\t菜品名\t\t菜品类\t\t价格\t\t");
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    //根据菜品id获取菜品对象
    public Menu getMenuObjectById(int id){
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
    //判断这个菜存不存在，
    //    思路：根据id来判断，这个菜对象存不存在
//    public Menu DishExist(String id){
//
//    }
//}
