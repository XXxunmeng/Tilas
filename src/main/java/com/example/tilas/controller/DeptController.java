package com.example.tilas.controller;
import com.example.tilas.anno.Log;
import com.example.tilas.pojo.Dept;
import com.example.tilas.pojo.Result;
import com.example.tilas.service.DeptService;
import com.example.tilas.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 自动生成日志
@RestController
@RequestMapping("/depts") // 抽取出公共的路径
public class DeptController {
    @Autowired
    // 依赖注入
    private DeptService deptService;

    /**
     * 查询全部部门数据
     * 调用方法为Get
     */
    @GetMapping //会限制请求方式为Get
    public Result list() {
        log.info("查询全部部门数据"); // 这就是日志
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
//        return Result.success();
    }

    /**
     * 根据id删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result dele(@PathVariable Integer id) {
        // PathVariable -- 路由参数
        log.info("删除指定部门数据");
        deptService.dele(id);
        return Result.success();
    }

    /**
     * 增加部门
     * 使用RequestBody注解能够从前端json获取实体
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept) {
        log.info("给定部门名增加部门");
        boolean insertResult = deptService.insert(dept);
        if (insertResult) {
            return Result.success();
        } else {
            return Result.error("添加失败，请检查部门名是否重复");
        }
    }

    /**
     * 根据id查询部门信息
     * 使用PathVariable获取路由中的参数
     * 参数为部门id
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id) {
        log.info("根据id查询部门信息");
        Dept dept = deptService.query(id);
        return Result.success(dept);
    }

    /**
     * 修改部门数据
     */
    @Log
    @PutMapping
    public Result modify(@RequestBody Dept dept) {
        log.info("修改部门数据");
        deptService.modify(dept);
        return Result.success();
    }

}
