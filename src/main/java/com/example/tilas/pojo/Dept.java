package com.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // 提供get/set等方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
public class Dept {

    private Integer id; // id
    private String name; // 部门名称
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
