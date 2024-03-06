package com.fss.usersystem.service.impl.discuss;

import com.fss.usersystem.mapper.DiscussMapper;
import com.fss.usersystem.pojo.Discuss;
import com.fss.usersystem.pojo.Users;
import com.fss.usersystem.service.discuss.RemoveDiscussService;
import com.fss.usersystem.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RemoveDiscussServiceImpl implements RemoveDiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Override
    public Map<String, String> removeDiscuss(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        Users user = loginUser.getUser();

        int discuss_id = Integer.parseInt(data.get("discuss_id"));
        Discuss discuss = discussMapper.selectById(discuss_id);

        Map<String, String> map = new HashMap<>();

        if (discuss == null) {
            map.put("error_message", "讨论不存在或已被删除");
            return map;
        }

        if (!discuss.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限删除该讨论");
            return map;
        }

        discussMapper.deleteById(discuss_id);

        map.put("error_message", "success");
        return map;
    }
}
