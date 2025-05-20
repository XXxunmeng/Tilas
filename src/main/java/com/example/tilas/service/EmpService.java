package com.example.tilas.service;

import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {

    PageBean listByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end); // 分页查询

    void deleteByIds(Integer[] ids); // 批量删除员工信息

    void addEmp(Emp emp); // 添加员工数据

    Emp findById(Integer id); // 根据id查询员工数据

    void modifyEmp(Emp emp); // 修改员工数据

    Emp check(Emp emp); // 检查用户名和密码是否正确
}
