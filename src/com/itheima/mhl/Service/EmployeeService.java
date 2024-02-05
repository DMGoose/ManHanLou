package com.itheima.mhl.Service;

import com.itheima.mhl.DAO.EmployeeDAO;
import com.itheima.mhl.Javabean.Employee;

/**
 * Service层，该类完成对Employee表的各种sql操作
 * 通过调用EmployeeDAO对象完成
 */
public class EmployeeService {
    //要调用EmployeeDAO对象，就要定义EmployeeDAO
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法：根据 empID 和 psw 返回 Employee 对象, 如果查询不到就返回null
    public Employee getEmployeeByIdAndPsw(String empId, String psw){
        Employee employee = employeeDAO.querySingle("select * from employee where empId = ? and psw = md5(?)",
                Employee.class, empId, psw);
        return employee;
    }
}
