package com.example.dmdm;

import android.provider.BaseColumns;

public class DataBases {
    public static final class CreateDB implements BaseColumns {
        public static final String c_id = "c_id";
        public static final String SmallCatego = "SmallCatego";
        public static final String p_id = "p_id";
        public static final String p_name = "p_name";
        public static final String check = "check";
        public static final String amount = "amount";
        public static final String _TABLENAME = "checklist";
        public static final String _CREATE = "create table "+_TABLENAME+"("
                +c_id+" text not null , "
                +SmallCatego+" text not null , "
                +p_id+" integer not null , "
                +p_name+" text not null , "
                +check+" boolean not null , "
                +amount+" integer not null, );";
    }
}
