package com.jz1yer.eduservice.controller;


import com.jz1yer.eduservice.service.EduTeacherService;
import com.jz1yer.eduservice.entity.EduTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jz1yer
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        return  eduTeacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeTeacherById(@PathVariable String id){
       return eduTeacherService.removeById(id);
    }
}

