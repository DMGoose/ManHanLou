package com.itheima.mhl.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于Druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;
    //在静态代码块完成ds初始化
    //思路：数据源对象，加载配置，设置配置，抛出运行异常、获取连接、关闭连接
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //编写getConnect方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    //关闭连接，不是断掉连接，只是把Connection对象放回连接池,和之前JDBCUtils中的close原理是不一样的
    //Connection是一个接口，mysql厂商和阿里巴巴厂商都实现了这个接口，但实现接口close方法的内容不同
    // JDBCUtils关闭连接是Mysql直接把连接关闭 ，Druid是把引用的连接放回到连接池等待下一次引用，并不是真正的关闭
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if(resultSet != null ){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
