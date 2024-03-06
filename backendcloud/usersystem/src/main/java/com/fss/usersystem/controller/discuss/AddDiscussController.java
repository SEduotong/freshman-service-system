package com.fss.usersystem.controller.discuss;

import com.fss.usersystem.service.discuss.AddDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddDiscussController {
    @Autowired
    private AddDiscussService addDiscussService;
    @PostMapping("/discuss/add/")
    public Map<String, String> addDiscuss(@RequestParam Map<String, String> data) {
        return addDiscussService.addDiscuss(data);
    }
}
