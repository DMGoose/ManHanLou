package com.itheima.mhl.DAO;


import com.itheima.mhl.Utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 开发BasicDAO，是其他DAO 的父类
 */
public class BasicDAO<T> {   //泛型制定具体的类型

    QueryRunner queryRunner = new QueryRunner();

    //开发通用的dml方法，针对任意的表
        //可以指定sql语句，还可以给sql语句的？指定相应值
    public int update(String sql, Object...parameters){
        //拿到连接
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();//将编译异常转成运行异常抛出
            //好处：调用者即可以捕获，也可以不捕获
            //编译异常不处理的话，你调用该类的方法还是得继续trycath，换成运行异常则不会
            //直接抛出，并没有处理异常，当你在调用该方法时还需要再次处理异常，使用try catch直接捕获并处理，当你在调用该方法时就不需要再次处理
            int update = queryRunner.update(connection, sql, parameters);//返回affected rows
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    //返回多个对象(即查询结果是多行)，针对任意表
    /**
     *
     * @param sql
     * @param clazz  传入一个类的class对象，比如Actor.class 通过反射获取field来创建Javabean对象，
     * @param parameters 传入？具体的值，可以有多个
     * @return 根据Actor.class返回对应的ArrayList集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object...parameters ){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();//将编译异常转成运行异常抛出
            return queryRunner.query(connection,sql, new BeanListHandler<T>(clazz),parameters);//返回affected rows
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    //查询单行结果的通用方法,可能返回来是个Actor，可能是Goods，所以用泛型
    public T querySingle(String sql, Class<T> clazz, Object...parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();//将编译异常转成运行异常抛出
            return queryRunner.query(connection,sql, new BeanHandler<T>(clazz),parameters);//返回affected rows
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    //查询单行单列的方法，即返回单值的方法，就是个对象
    public Object queryScalar(String sql, Object...parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();//将编译异常转成运行异常抛出
            return queryRunner.query(connection,sql, new ScalarHandler(),parameters);//返回affected rows
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
}
