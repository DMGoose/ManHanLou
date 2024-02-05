package com.itheima.mhl.Javabean;

/**
 * 这是一个Javabean，和 DinningTable 表对应

 create table diningTable (
         id int primary key auto_increment, #自增, 表示餐桌编号
         state varchar(20) not null default '',#餐桌的状态
         orderName varchar(50) not null default '',#预订人的名字
         orderTel varchar(20) not null default ''
 )charset=utf8;
 */
public class DinningTable {
    private Integer id;
    private String state;
    private String name;
    private String orderTel;

    public DinningTable() {
    }

    public DinningTable(Integer id, String state, String name, String orderTel) {
        this.id = id;
        this.state = state;
        this.name = name;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}
