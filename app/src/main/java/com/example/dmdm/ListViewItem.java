package com.example.dmdm;

import android.widget.CheckBox;

public class ListViewItem {

    private String text ;
    private CheckBox cb;

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
}