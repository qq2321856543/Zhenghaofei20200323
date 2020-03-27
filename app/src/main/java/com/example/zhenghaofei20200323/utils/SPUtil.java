package com.example.zhenghaofei20200323.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    public static void putString(Context context,String key,String values){
        SharedPreferences aa = context.getSharedPreferences("aa", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = aa.edit();
        edit.putString(key,values);
        edit.commit();
    }
    public static String getString(Context context,String key){
        SharedPreferences aa = context.getSharedPreferences("aa", Context.MODE_PRIVATE);
        String string = aa.getString(key, "");
        return string;
    }
}
