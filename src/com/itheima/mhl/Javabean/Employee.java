package com.itheima.mhl.Javabean;

/**
 * 这是一个JavaBean，和 emp表 映射
 create table employee (
     id int primary key auto_increment, #自增
     empId varchar(50) not null default '',#员工号
     pwd char(32) not null default '',#密码md5
     name varchar(50) not null default '',#姓名
     job varchar(50) not null default '' #岗位
 )charset=utf8;
 */
public class Employee {
    private Integer id;
    private String empId;
    private String psw;
    private String name;
    private String job;

    public Employee() {
    }

    public Employee(Integer id, String empId, String psw, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.psw = psw;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", psw='" + psw + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
