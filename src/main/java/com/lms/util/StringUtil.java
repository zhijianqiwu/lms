package com.lms.util;

public class StringUtil {

    public static boolean checkString(String object){
        if(object!=null&&!object.equals(""))
            return true;
        return false;
    }
}
