package com.example.tilas.service.impl;

import com.example.tilas.mapper.EmpMapper;
import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.PageBean;
import com.example.tilas.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean listByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 下面是传统的分页查询
//        Long total = empMapper.culEmp();
//        List<Emp> emps = empMapper.list((page - 1) * pageSize, pageSize);
//        PageBean pageBean = new PageBean(total, emps);
//        return pageBean;

        // PageHelper进行分页查询
        PageHelper.startPage(page, pageSize); // 设置分页参数
        List<Emp> empList = empMapper.list(name, gender, begin, end); // 直接正常查询
        System.out.println(empList);
        Page<Emp> p = (Page<Emp>) empList; // 直接把上面的结果强转
        System.out.println(p);
        return new PageBean(p.getTotal(), p.getResult());
    }

    // 批量删除员工数据
    @Override
    public void deleteByIds(Integer[] ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        return emp;
    }

    @Override
    public void modifyEmp(Emp emp) {
        System.out.println(emp);
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }
}
