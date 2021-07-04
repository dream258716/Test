package com.springboot.springbootdemo1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.springbootdemo1.entity.User;
import com.springboot.springbootdemo1.entity.UserPath;
import com.springboot.springbootdemo1.vo.BarVo;
import org.apache.ibatis.annotations.Param;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author alex wong
 * @since 2021-06-01
 */
public interface UserService {

    List<User> queryListUser();

    User getUserId(int id);

    int addUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

    User getUserMassage(String name ,String password);

    List<UserPath> queryListUserPath();

    User getUserName(String name);

    BarVo barVoList();

    List<String>  userPath();

    int upState(int id ,boolean state);




}
