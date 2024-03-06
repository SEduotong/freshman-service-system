package com.fss.usersystem.controller.discuss;

import com.fss.usersystem.pojo.Discuss;
import com.fss.usersystem.service.discuss.GetDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDiscussController {
    @Autowired
    private GetDiscussService getDiscussService;

    @GetMapping("/discuss/getlist/")
    public List<Discuss> getDiscuss() {
        return getDiscussService.getAllDiscuss();
    }
}
