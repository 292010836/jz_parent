package com.jz1yer.eduservice.controller;


import com.jz1yer.commonutils.R;
import com.jz1yer.commonutils.Result;
import com.jz1yer.commonutils.ResultCode;
import com.jz1yer.eduservice.service.EduTeacherService;
import com.jz1yer.eduservice.entity.EduTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return new Result(ResultCode.SUCCESS,list);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacherById(@ApiParam(name = "id", value = "讲师id",required = true) @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return new Result(ResultCode.SUCCESS);
        }else {
            return new Result(ResultCode.ERROR);
        }
    }
}

