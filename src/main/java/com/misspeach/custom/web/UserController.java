package com.misspeach.custom.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangtengfei on 16/7/20.
 */
@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String user() {
        return "hello";
    }
}
