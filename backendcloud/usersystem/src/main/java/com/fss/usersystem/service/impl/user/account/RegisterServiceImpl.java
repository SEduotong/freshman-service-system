package com.fss.usersystem.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fss.usersystem.mapper.UserMapper;
import com.fss.usersystem.pojo.Users;
import com.fss.usersystem.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if (username == null) {
            map.put("error_message", "录取通知书号不能为空");
            return map;
        }
        if (password == null && confirmedPassword == null) {
            map.put("error_message", "身份证号不能为空");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message", "录取通知书号不能为空");
            return map;
        }
        password = password.trim();
        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "身份证号不能为空");
            return map;
        }
        if (username.length() != 10) {
            map.put("error_message", "录取通知书号长度必须为10位");
            return map;
        }
        if (password.length() != 18 || confirmedPassword.length() != 18) {
            map.put("error_message", "身份证号长度必须为18位");
            return map;
        }
        if (!password.equals(confirmedPassword)) {
            map.put("error_message", "两次输入的身份证号不一致");
            return map;
        }

        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Users> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "录取通知书号已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String roles = "[\"user\"]";
        String profilePicture = "https://uploadfiles.nowcoder.com/images/20220708/997044463_1657259812758/7402D8581CBE60E6F7092E8BE138892C";
        Users user = new Users(null, username, null, null, encodedPassword, roles, profilePicture, 0, null, new Date(), new Date());
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
