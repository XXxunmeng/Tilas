package com.example.tilas.controller;

import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.Result;
import com.example.tilas.service.EmpService;
import com.example.tilas.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        Emp result = empService.check(emp);
        System.out.println(emp);
        if (result != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", result.getId());
            claims.put("name", result.getName());
            claims.put("username", result.getUsername());
            String jwt = JwtUtils.getJwt(claims);
            return Result.success(jwt);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

}
