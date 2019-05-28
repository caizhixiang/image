package com.caizhixiang.springboot.web;

import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    @Autowired
    private ImageService imageService;
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<Image> images = imageService.findAll();

        modelAndView.addObject("images", images);
        modelAndView.setViewName("api/index");
        return modelAndView;
    }
}
