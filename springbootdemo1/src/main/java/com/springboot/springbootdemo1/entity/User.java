package com.springboot.springbootdemo1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author alex wong
 * @since 2021-06-01
 */
@Data
public class User {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    private String name;

    private String city;

    private String password;




}
