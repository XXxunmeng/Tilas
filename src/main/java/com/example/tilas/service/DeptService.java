package com.example.tilas.service;

import com.example.tilas.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> list(); // 查

    boolean dele(Integer id); // 删

    boolean insert(Dept dept); // 增

    Dept query(Integer id); // 查

    void modify(Dept dept); // 改
}
