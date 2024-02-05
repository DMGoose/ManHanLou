package com.itheima.mhl.Javabean;

import java.time.LocalDateTime;

/**
 * 这是一个含有多表field的JavaBean，用于多表查询，和多张表进行映射(menu,r)
 */
public class MultiTableBean {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTable;
    private LocalDateTime billDate;
    private String state;
    //增加一个来自menu的name，记得给构造器，给get set
    private String name;
    //增加来自menu表的列 price
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTable, LocalDateTime billDate, String state, String name, Double price) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTable = diningTable;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
        this.price = price;
    }

    public Integer getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(Integer diningTable) {
        this.diningTable = diningTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDinningTable() {
        return diningTable;
    }

    public void setDinningTable(Integer dinningTable) {
        this.diningTable = dinningTable;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + menuId +
                "\t\t" + nums +
                "\t\t" + money +
                "\t\t" + diningTable +
                "\t\t" + billDate +
                "\t\t\t\t" + state +
                "\t\t\t" + name+
                "\t\t\t" + price;
    }
    public MultiTableBean() {
    }
}
