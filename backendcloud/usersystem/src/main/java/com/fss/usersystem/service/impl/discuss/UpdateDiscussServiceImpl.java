package com.fss.usersystem.service.impl.discuss;

import com.fss.usersystem.mapper.DiscussMapper;
import com.fss.usersystem.pojo.Discuss;
import com.fss.usersystem.pojo.Users;
import com.fss.usersystem.service.discuss.UpdateDiscussService;
import com.fss.usersystem.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateDiscussServiceImpl implements UpdateDiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Override
    public Map<String, String> updateDiscuss(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        Users user = loginUser.getUser();

        int discuss_id = Integer.parseInt(data.get("discuss_id"));

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

        Discuss discuss = discussMapper.selectById(discuss_id);

        if (discuss == null) {
            map.put("error_message", "讨论不存在或已被删除");
            return map;
        }

        if (!discuss.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限修改该讨论");
            return map;
        }

        Discuss new_discuss = new Discuss(
                discuss.getId(),
                user.getId(),
                title,
                content,
                0,
                discuss.getCreatedAt()
        );
        discussMapper.updateById(new_discuss);

        map.put("error_message", "success");

        return map;
    }
}
