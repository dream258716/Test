package com.springboot.springbootdemo1.vo;

import lombok.Data;

import java.util.Map;

@Data
public class DataVo {

    private Integer value;
    private Map<String,String> itemStyle;

}
