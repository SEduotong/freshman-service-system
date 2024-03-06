package com.fss.usersystem.controller.discuss;

import com.fss.usersystem.service.discuss.UpdateDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateDiscussController {
    @Autowired
    private UpdateDiscussService updateDiscussService;

    @PostMapping("/discuss/update/")
    public Map<String, String> updateDiscuss(@RequestParam Map<String, String> data) {
        return updateDiscussService.updateDiscuss(data);
    }
}
