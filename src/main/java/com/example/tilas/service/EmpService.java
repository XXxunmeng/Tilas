package com.example.tilas.service;

import com.example.tilas.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {

    PageBean listByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end); // 分页查询

    void deleteByIds(Integer[] ids); // 批量删除员工信息

}
