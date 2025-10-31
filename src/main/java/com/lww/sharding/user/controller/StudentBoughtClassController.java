package com.lww.sharding.user.controller;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import com.lww.sharding.user.service.StudentBoughtService;
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
@RequestMapping("/student")
public class StudentBoughtClassController {

    @Autowired
    private StudentBoughtService studentBoughtService;

    @GetMapping("/list")
    public List<StudentBoughtClassDto> list() {
        return studentBoughtService.list();
    }


}
