package com.itheima.mhl.Javabean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 是一个javaBean 和 bill表对应
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTable;
    private LocalDateTime billDate;
    private String state;

    public Bill(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer dinningTable, LocalDateTime billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTable = dinningTable;
        this.billDate = billDate;
        this.state = state;
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
                "\t\t\t\t\t\t" + state;
    }
    public Bill() {
    }
}
