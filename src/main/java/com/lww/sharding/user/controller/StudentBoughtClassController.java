package com.lww.sharding.user.controller;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.order.dto.ExecSqlDto;
import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.StudentBoughtClass;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import com.lww.sharding.user.service.StudentBoughtService;
import com.lww.sharding.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<StudentBoughtClass> list() {
        return studentBoughtService.list();
    }

    @GetMapping("/getByUserNumber")
    public List<StudentBoughtClass> list(@RequestParam("number") long  userNumber) {
        return studentBoughtService.getByUserNumber(userNumber);
    }

    @GetMapping("/flush")
    public void flush(@RequestParam("minId") long minId, @RequestParam("maxId") long maxId) {
        studentBoughtService.flush(minId,maxId);
    }

    @GetMapping("/getLast")
    public StudentBoughtClass getLast() {
        return studentBoughtService.getLast();
    }

    @GetMapping("/join")
    public List<StudentBoughtClass> join(@RequestParam(value = "userNumber", required = false, defaultValue = "") Long userNumber) {
        return studentBoughtService.join(userNumber);
    }

    @GetMapping("/joinList")
    public List<StudentBoughtClass> joinList(@RequestParam(value = "userNumber", required = false, defaultValue = "") List<Long> userNumber) {
        return studentBoughtService.joinList(userNumber);
    }

    @PostMapping("/execSql")
    public void execSql(@RequestBody ExecSqlDto req) {
        studentBoughtService.execSql(req.getSql());
    }
}
