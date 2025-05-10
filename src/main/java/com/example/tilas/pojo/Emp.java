package com.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private Integer id; // ID
    private String username; // 用户名
    private String password; // 密码
    private String name; // 姓名
    private Short gender; // 性别，1 - 男，2 - 女
    private String image; // 图片url
    private Short job; // 职位
    private Date entrydate; // 入职日期
    private Integer deptId; // 部门ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

}
