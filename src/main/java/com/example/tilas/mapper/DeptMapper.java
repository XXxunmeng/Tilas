package com.example.tilas.mapper;

import com.example.tilas.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DeptMapper {
    // 查询所有部门信息
    @Select("select * from dept")
    List<Dept> list();

    // 根据id删除部门信息
    @Delete("delete from dept where id = #{id}")
    boolean dele(Integer id);

    // 增加部门信息，id会自动增长
    @Insert("insert into dept(name, create_time, update_time) " +
            "values(#{name}, #{createTime}, #{updateTime})")
    boolean insert(Dept dept);

    // 根据id查询部门信息
    @Select("select * from dept where id = #{id}")
    Dept query(Integer id);

    // 根据id修改部门信息，只修改名字和更新时间
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void modify(Dept dept);
}
