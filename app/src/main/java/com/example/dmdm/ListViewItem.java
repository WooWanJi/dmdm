package com.example.dmdm;

import android.widget.CheckBox;

public class ListViewItem {

    private String text ;
    private CheckBox cb;
    private String price ;
    private Integer amount ;

    public void setText(String text) {
        this.text = text ;
    }
    public String getText() {
        return this.text ;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb ;
    }
    public CheckBox getCb() {
        return this.cb ;
    }

    public void setPrice(String price) {
        this.price = price ;
    }
    public String getPrice() {
        return this.price ;
    }

    public void setAmount(Integer amount) {
        this.amount = amount ;
    }
    public Integer getAmount() {
        return this.amount ;
    }

}