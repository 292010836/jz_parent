package com.jz1yer.eduservice.controller;


import com.jz1yer.commonutils.Result;
import com.jz1yer.commonutils.ResultCode;
import com.jz1yer.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}

