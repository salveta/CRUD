package com.salvador.perez.crud.utils;

/**
 * Created by Salva on 13/01/2018.
 */

public class StringUtil {

    public static boolean isNull(String chars){
        return "".equals(chars) || chars == null || ( chars != null && "".equals(chars.trim()) ) || "null".equalsIgnoreCase(chars);
    }

}
