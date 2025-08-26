package com.lww.sharding.user.controller;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryUser")
    public User queryUser() {
//        User user = new User(1L, "张三");
        return null;
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/page")
    public List<User> page(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize) {
        return userService.page(pageNum,pageSize);
    }

    @GetMapping("/save")
    public void save() {
        User user = new User();
        user.setName("张三");
        user.setId(1L);
        userService.save(user);
    }

    @GetMapping("/list2")
    public List<User> list2() {
        return userService.list2();
    }

}
