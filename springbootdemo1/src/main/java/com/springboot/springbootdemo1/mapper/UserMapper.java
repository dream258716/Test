package com.springboot.springbootdemo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.springbootdemo1.entity.User;
import com.springboot.springbootdemo1.entity.UserHa;
import com.springboot.springbootdemo1.entity.UserPath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author alex wong
 * @since 2021-06-01
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> queryListUser();

    User getUserId(int id);

    int addUser(User user);

    User getUserName(String name);

    int deleteUser(int id);

    int updateUser(User user);

    User getUserMassage(@Param("name") String name ,@Param("password") String password);

    List<UserPath> queryListUserPath();

    List<User> getAllUser(@Param("name") String name, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);

    int getUserCounts(@Param("name") String name);

    List<UserHa> selectUser();

    List<String> userPath();

    int upState(int id ,boolean state);




}
