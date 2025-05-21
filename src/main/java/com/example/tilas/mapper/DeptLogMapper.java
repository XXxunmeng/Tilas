package com.example.tilas.mapper;

import com.example.tilas.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DeptLogMapper {

    @Insert("insert into dept_log(create_time, description)" +
            "values(#{createTime}, #{description})")
    public void insert(DeptLog deptLog);

}
