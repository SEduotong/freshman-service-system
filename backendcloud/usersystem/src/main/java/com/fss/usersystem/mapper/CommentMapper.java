package com.fss.usersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fss.usersystem.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
