package com.fss.usersystem.service.impl.discuss;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fss.usersystem.mapper.DiscussMapper;
import com.fss.usersystem.pojo.Discuss;
import com.fss.usersystem.pojo.Users;
import com.fss.usersystem.service.discuss.AddDiscussService;
import com.fss.usersystem.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddDiscussServiceImpl implements AddDiscussService {
    @Autowired
    private DiscussMapper discussMapper;

    @Override
    public Map<String, String> addDiscuss(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        Users user = loginUser.getUser();
        String title = data.get("title");
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();

        if (title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }

        if (title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        if (content == null || content.length() == 0) {
            map.put("error_message", "内容不能为空");
            return map;
        }

        if (content.length() > 10000) {
            map.put("error_message", "内容长度不能超过10000");
            return map;
        }

        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());

        if (discussMapper.selectCount(queryWrapper) >= 2) {
            map.put("error_message", "每个用户最多只能创建2个帖子");
            return map;
        }

        Date now = new Date();
        Discuss discuss = new Discuss(null,user.getId(), title, content, 0, now);

        discussMapper.insert(discuss);
        map.put("error_message", "success");

        return map;
    }
}
