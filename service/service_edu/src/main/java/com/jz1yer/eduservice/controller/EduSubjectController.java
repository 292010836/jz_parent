package com.jz1yer.eduservice.controller;


import com.jz1yer.commonutils.Result;
import com.jz1yer.commonutils.ResultCode;
import com.jz1yer.eduservice.entity.subject.OneSubject;
import com.jz1yer.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jz1yer
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;
    //添加课程分类
    @PostMapping("addSubject")
    public Result saveSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return new Result(ResultCode.SUCCESS);
    }

    //树形结构,查询所有课程分类
    @GetMapping("getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllSubject();
       return new Result(ResultCode.SUCCESS,list);
    }

}

