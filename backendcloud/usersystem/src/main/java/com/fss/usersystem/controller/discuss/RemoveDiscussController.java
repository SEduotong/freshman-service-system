package com.fss.usersystem.controller.discuss;

import com.fss.usersystem.service.discuss.RemoveDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveDiscussController {
    @Autowired
    private RemoveDiscussService removeDiscussService;

    @PostMapping("/discuss/remove/")
    public Map<String, String> removeDiscuss(@RequestParam Map<String, String> data) {
        return removeDiscussService.removeDiscuss(data);
    }

}
