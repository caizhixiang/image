package com.caizhixiang.springboot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author caizhixiang
 * @description: 前台
 * @Date 2019-04-25 15:09
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
