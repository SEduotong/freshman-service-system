package com.fss.usersystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String username;

    private String email;

    private String phone;

    private String password;

//    private String idCardNumber;
//
//    private String admissionNoticeNumber;

    private String roles;

    private String profilePicture;

    private int points;

    private String taskProgress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updatedAt;
}
