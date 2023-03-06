package com.mysite.sbb.sbbproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    // URL 매핑
    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "index";
    }
}
