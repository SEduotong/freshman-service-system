package com.fss.usersystem.service.impl.discuss;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fss.usersystem.mapper.DiscussMapper;
import com.fss.usersystem.pojo.Discuss;
import com.fss.usersystem.service.discuss.GetDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDiscussServiceImpl implements GetDiscussService {
    @Autowired
    private DiscussMapper discussMapper;

    @Override
    public List<Discuss> getAllDiscuss() {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        return discussMapper.selectList(queryWrapper);
    }
}
