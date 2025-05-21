package com.example.tilas.mapper;
import com.example.tilas.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 手写分页查询代码如下
//    @Select("select count(*) from emp")
//    Long culEmp();
//
//
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    // 常规的查询所有员工信息，PageHelper可以以此进行分页查询
    // @Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteByIds(Integer[] ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void addEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);

    void updateEmp(Emp emp); // 修改员工数据

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp check(Emp emp); // 检查用户名和密码是否正确

    @Delete("delete from emp where dept_id=#{id}")
    void deleteByDept(Integer id);
}
