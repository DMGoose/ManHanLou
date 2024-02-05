package com.itheima.mhl.Javabean;

public class Menu {
    private Integer id;
    private String name;
    private String type;
    private Integer price;

    public Menu() {
    }

    public Menu(Integer id, String name, String type, Integer price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                id + "\t\t" +name + "\t\t" + type + "\t\t" + price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
