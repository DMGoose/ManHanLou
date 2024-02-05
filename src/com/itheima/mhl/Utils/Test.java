package com.itheima.mhl.Utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试工具类
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        System.out.println("输一个整数");
        int i = Utility.readInt();
        System.out.println("得到int i = " + i);

        //测试JDBCUtilsByDruid
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
