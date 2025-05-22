package com.example.tilas.controller;

import com.example.tilas.anno.Log;
import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.PageBean;
import com.example.tilas.pojo.Result;
import com.example.tilas.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result listByPage(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Short gender,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) { // 设置默认值和非必须
//        log.info("分页查询员工数据");
        PageBean pageBean = empService.listByPage(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    // 删除员工数据
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable Integer[] ids) {
        log.info("批量删除员工数据");
        empService.deleteByIds(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工数据");
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据id查询员工数据");
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result modifyEmp(@RequestBody Emp emp) {
        log.info("修改员工数据");
        empService.modifyEmp(emp);
        return Result.success();
    }

}
