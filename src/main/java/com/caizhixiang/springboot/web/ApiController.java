package com.caizhixiang.springboot.web;

import com.caizhixiang.springboot.mapper.entity.Detail;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.DetailService;
import com.caizhixiang.springboot.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private DetailService detailService;

    /**
     * 首页
     * @param category
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(required = false,defaultValue = "4") Integer category) {
        ModelAndView modelAndView = new ModelAndView();
        List<Image> images = imageService.findAll();
        String categories = images.stream().filter(image -> image!=null&&image.getCategory()!=null).map(image -> image.getCategory().toString() ).distinct().collect(Collectors.joining(","));
        List<Image> list = images.stream().filter(image -> image != null && category.equals(image.getCategory())).collect(Collectors.toList());
        modelAndView.addObject("images", list);
        modelAndView.addObject("category", category);
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("api/index");
        return modelAndView;
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Detail detail = detailService.findById(id);
        if (detail == null) {
            detail = new Detail();
            detail.setImageId(id);
        }
        modelAndView.addObject("detail", detail);
        modelAndView.setViewName("api/detail");
        return modelAndView;
    }

    @RequestMapping("/more")
    public ModelAndView more() {
        ModelAndView modelAndView = new ModelAndView();
        List<Image> images = imageService.findAll();

        modelAndView.addObject("images", images);
        modelAndView.setViewName("api/list");
        return modelAndView;
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public List<Image> list() {
        return imageService.findAll();

    }


}
