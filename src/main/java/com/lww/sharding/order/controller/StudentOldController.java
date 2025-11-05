package com.lww.sharding.order.controller;/**
 * @author wu
 * @date 2025/11/5
 */

import cn.hutool.core.util.RandomUtil;
import com.lww.sharding.order.service.StudentOldService;
import com.lww.sharding.user.entity.StudentBoughtClass;
import com.lww.sharding.user.service.StudentBoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-11-05  10:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("/student/old")
public class StudentOldController {
    @Autowired
    StudentOldService studentOldService;
    @Autowired
    StudentBoughtService studentBoughtService;
    @GetMapping("/list")
    public List<StudentBoughtClass> list() {
        return studentOldService.list();
    }

    @GetMapping("/save")
    public String save() {
        StudentBoughtClass studentBoughtClass = new StudentBoughtClass();
        studentBoughtClass.setUserNumber(RandomUtil.randomLong(0,Long.MAX_VALUE));
        studentBoughtClass.setCourseNumber(RandomUtil.randomLong());
        studentBoughtClass.setBuyClassNumber(RandomUtil.randomLong());
        studentBoughtClass.setClassNumber(RandomUtil.randomLong());
        studentOldService.save(studentBoughtClass);
         studentBoughtService.save(studentBoughtClass);
        return "成功";
    }

    @GetMapping("/getByUserNumber")
    public List<StudentBoughtClass> list(@RequestParam("number") long  userNumber) {
        return studentOldService.getByUserNumber(userNumber);
    }

    @GetMapping("/flush")
    public void flush(@RequestParam("minId") long minId, @RequestParam("maxId") long maxId) {
        studentOldService.flush(minId,maxId);
    }

}
