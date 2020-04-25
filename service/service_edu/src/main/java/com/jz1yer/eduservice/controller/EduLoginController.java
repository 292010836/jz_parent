package com.jz1yer.eduservice.controller;


import com.jz1yer.commonutils.Result;
import com.jz1yer.commonutils.ResultCode;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public Result login(){
        Map map = new HashMap();
        map.put("token","admin");
        return new Result(ResultCode.SUCCESS,map);
    }

    @GetMapping("info")
    public Result info(){
        Map map = new HashMap();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new Result(ResultCode.SUCCESS,map);
    }
}
