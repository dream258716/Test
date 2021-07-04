package com.springboot.springbootdemo1.util;

import java.util.HashMap;
import java.util.Map;

public class EchartsUtil {

    public static Map<String,String> createStyle(Integer nums){

        String color = "";

        if (nums>1){
            color = "#96dee8";
        }
        if (nums>3){
            color = "#c4ebad";
        }
        if (nums>5){
            color = "#6be6c1";
        }
        if (nums>7){
            color = "#a0a7e6";
        }
        Map<String,String> map = new HashMap<>();
        map.put("color",color);
        return map;
    }

}
