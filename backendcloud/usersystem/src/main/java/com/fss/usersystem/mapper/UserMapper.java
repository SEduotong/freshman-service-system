package com.fss.usersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fss.usersystem.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<Users> {
}
