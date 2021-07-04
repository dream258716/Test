package com.springboot.springbootdemo1.service.impl;

import com.springboot.springbootdemo1.entity.User;
import com.springboot.springbootdemo1.entity.UserHa;
import com.springboot.springbootdemo1.entity.UserPath;
import com.springboot.springbootdemo1.mapper.UserMapper;
import com.springboot.springbootdemo1.service.UserService;
import com.springboot.springbootdemo1.util.EchartsUtil;
import com.springboot.springbootdemo1.vo.BarVo;
import com.springboot.springbootdemo1.vo.DataVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author alex wong
 * @since 2021-06-01
 */
@Service
public class UserServiceImpl implements UserService {

   final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public List<User> queryListUser() {
        return userMapper.queryListUser();
    }

    @Override
    public User getUserId(int id) {

        return userMapper.getUserId(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int updateUser(User user) { return userMapper.updateUser(user); }

    @Override
    public User getUserMassage(String name, String password) {
        return userMapper.getUserMassage(name,password);
    }

    @Override
    public List<UserPath> queryListUserPath() {
        return userMapper.queryListUserPath();
    }

    @Override
    public User getUserName(String name) {
        return userMapper.getUserName(name);
    }

    @Override
    public BarVo barVoList() {
        BarVo barVo = new BarVo();
        List<String> names = new ArrayList<>();
        List<DataVo> voList = new ArrayList<>();
        List<UserHa> userHas = userMapper.selectUser();
        for (UserHa userHa : userHas){
            names.add(userHa.getCity());
            DataVo dataVo = new DataVo();
            dataVo.setValue(userHa.getNums());
            dataVo.setItemStyle(EchartsUtil.createStyle(userHa.getNums()));
            voList.add(dataVo);
        }
        barVo.setName(names);
        barVo.setValue(voList);
        return barVo;
    }

    @Override
    public List<String>  userPath() {
        return userMapper.userPath();
    }

    @Override
    public int upState(int id, boolean state) {
        return userMapper.upState(id,state);
    }


}













