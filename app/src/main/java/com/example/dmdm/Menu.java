package com.example.dmdm;

public class Menu {
    private String name; private String price;
    public static final Menu[] menus = new Menu[]{
            new Menu("신라면", "dd"),
            new Menu("진라면", ""),
            new Menu("열라면", ""),
            new Menu("팔도비빔면", "")

    };
    private Menu(String name, String price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public String toString(){
        return this.name;
    }
}
