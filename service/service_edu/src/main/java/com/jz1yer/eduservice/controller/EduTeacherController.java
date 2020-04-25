package com.jz1yer.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jz1yer.commonutils.Result;
import com.jz1yer.commonutils.ResultCode;
import com.jz1yer.eduservice.entity.EduTeacher;
import com.jz1yer.eduservice.entity.vo.TeacherQuery;
import com.jz1yer.eduservice.service.EduTeacherService;
import com.jz1yer.servicebase.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return new Result(ResultCode.SUCCESS, list);
    }
    @ApiOperation("所有讲师列表1")
    @GetMapping("findAll1")
    public Result findAllTeacher1() {
        List<EduTeacher> list = eduTeacherService.list(null);
        try {
            int u = 1/0;
        }catch (Exception e){
            throw new MyException(20001,"执行自定义异常!");
        }

        return new Result(ResultCode.SUCCESS, list);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.ERROR);
        }
    }

    @ApiOperation("分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        Map hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("rows", records);
        return new Result(ResultCode.SUCCESS, hashMap);
    }

    @ApiOperation("条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageListTeacherCondition(@PathVariable long current,
                                           @PathVariable long limit,
                                           @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        queryWrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page, queryWrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        Map hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("row", records);

        return new Result(ResultCode.SUCCESS, hashMap);

    }

    /**
     * 添加讲师
     * @param eduTeacher
     * @return
     */
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.ERROR);
        }
    }

    @GetMapping("getTeacherByid/{id}")
    public Result getTeacherByid(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return new Result(ResultCode.SUCCESS,teacher);
    }

    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.ERROR);
        }
    }
}

