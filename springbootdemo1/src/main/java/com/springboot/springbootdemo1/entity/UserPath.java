package com.springboot.springbootdemo1.entity;

import lombok.Data;

import java.util.List;


@Data
public class UserPath {

    private int id ;
    private String name ;
    private String path ;
    private List<Menu> demo;


}
