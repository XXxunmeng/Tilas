package com.example.tilas.service.impl;

import com.example.tilas.mapper.DeptMapper;
import com.example.tilas.pojo.Dept;
import com.example.tilas.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper; // Mapper的依赖注入
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public boolean dele(Integer id) {
        return deptMapper.dele(id);
    }

    @Override
    public boolean insert(Dept dept) {
        // 检查一下部门名称是否重复
        List<Dept> depts = list();
        for (Dept d : depts) {
            if (d.getName().equals(dept.getName())) {
                return false;
            }
        }
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
