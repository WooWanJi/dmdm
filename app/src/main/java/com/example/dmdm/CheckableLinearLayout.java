package com.example.dmdm;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // mIsChecked = false ;
    }

    @Override
    public boolean isChecked() { //현재 Checked 상태를 리턴.
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        return cb.isChecked() ;
        // return mIsChecked ;
    }

    @Override
    public void toggle() { //현재 Checked 상태를 바꿈. (UI에 반영)
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        setChecked(cb.isChecked() ? false : true) ;
        // setChecked(mIsChecked ? false : true) ;
    }

    @Override
    public void setChecked(boolean checked) { //Checked 상태를 checked 변수대로 설정.
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;

        if (cb.isChecked() != checked) {
            cb.setChecked(checked) ;
        }

        // CheckBox 가 아닌 View의 상태 변경.
    }
}
