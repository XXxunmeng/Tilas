package com.example.tilas.service.impl;

import com.example.tilas.mapper.DeptMapper;
import com.example.tilas.mapper.EmpMapper;
import com.example.tilas.pojo.Dept;
import com.example.tilas.pojo.OperateLog;
import com.example.tilas.service.DeptLogService;
import com.example.tilas.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper; // Mapper的依赖注入
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    @Transactional //事务注解
    public void dele(Integer id){
        try {
            empMapper.deleteByDept(id);
//            int t = 1 / 0; // 伪造一个异常
//            System.out.println(t);
            deptMapper.dele(id);
        } finally {
            System.out.println("error");
//            OperateLog operateLog = new OperateLog();
//            operateLog.setDescription("解散部门指令执行，此次解散的部门id为" + id);
//            operateLog.setCreateTime(LocalDateTime.now());
//            deptLogService.insert(operateLog);
        }
    }

    @Override
    public boolean insert(Dept dept) {
        // 检查一下部门名称是否重复
        List<Dept> depts = list();
//        for (Dept d : depts) {
//            if (d.getName().equals(dept.getName())) {
//                return false;
//            }
//        }
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        return deptMapper.insert(dept);
    }

    @Override
    public Dept query(Integer id) {
        return deptMapper.query(id);
    }

    @Override
    public void modify(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now()); // 设置一下更新时间
        deptMapper.modify(dept);
    }
}
