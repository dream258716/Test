package com.springboot.springbootdemo1.vo;

import lombok.Data;

import java.util.List;

@Data
public class BarVo {

    private List<String> name;

    private List<DataVo> value;

}
