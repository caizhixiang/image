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
 * @description: 后台
 * @Date 2019-04-25 15:10
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ImageService imageService;
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    public List<Image> findAll() {
        return imageService.findAll();
    }
}
