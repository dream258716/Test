package com.springboot.springbootdemo1.controller;

import com.springboot.springbootdemo1.entity.QueryInfo;
import com.springboot.springbootdemo1.entity.User;
import com.springboot.springbootdemo1.entity.UserPath;
import com.springboot.springbootdemo1.mapper.UserMapper;
import com.springboot.springbootdemo1.service.UserService;

import com.springboot.springbootdemo1.vo.BarVo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author alex wong
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final UserMapper userMapper;
    private UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/getuser")
    public HashMap<String,Object> userMassage(@RequestBody User user){
        String err = "error";
        User user1 = userService.getUserMassage(user.getName(), user.getPassword());
        HashMap<String,Object> ha = new HashMap<>();
        if (user1 != null){
           err = "ok";
        }
        ha.put("err",err);
        ha.put("user",user1);
        return ha;
    }

    @GetMapping("/getuserpath")
    public HashMap<String,Object> getUserPath(){

        HashMap<String,Object> data = new HashMap<>();

        List<UserPath> userPaths = userService.queryListUserPath();
        if (userPaths != null){
            data.put("userpaths",userPaths);
        }
        return data;
    }

    @PostMapping("/adduser")
    public HashMap<String,Object> getuserMassage(@RequestBody User user) {
        String err1 = "error";
        HashMap<String, Object> ha = new HashMap<>();
        User user1 = userService.getUserName(user.getName());
        if (user1 == null) {
            int user2 = userService.addUser(user);
            err1 = "ok";
            ha.put("user", user2);
        }
        ha.put("err", err1);
        return ha;
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        List<User> list = userService.queryListUser();
        return list;
    }


    @GetMapping("/allUser")
    public HashMap<String,Object> getUserList(QueryInfo queryInfo){
        int numbers = userMapper.getUserCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();
        List<User> users = userMapper.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        return res;
    }

    @DeleteMapping("/deleteuser")
    public String deleteUser(int id){
        int i = userService.deleteUser(id);
        return i>0? "success":"error";
    }

    @GetMapping("/getUserById")
    public User getUserId(int id){
        User user = userService.getUserId(id);return user;
    }

    @PostMapping("/upUser")
    public String upUser(@RequestBody User user){
        int i =  userService.updateUser(user);
        return i>0? "success":"error";
    }

    @GetMapping("/barVo")
    public BarVo barVo(){
        return userService.barVoList();
    }

    @GetMapping("/getPath")
    public HashMap<String,List<String>> getPath(){
        HashMap<String,List<String>> hashMap = new HashMap<>();
        List<String> list = userService.userPath();
        hashMap.put("label",list);
        return hashMap;
    }

    @RequestMapping("/upState")
    public String getState(@RequestParam("id") int id ,
                           @RequestParam("state") boolean state){

        int i = userService.upState(id,state);
        return i>0? "error":"success";

    }


}


































