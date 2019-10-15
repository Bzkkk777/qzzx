package com.bzk.wzzx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state")String state, Model model){

        return "index";
    }


}