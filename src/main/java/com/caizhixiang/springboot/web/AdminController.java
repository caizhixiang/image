package com.caizhixiang.springboot.web;

import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.DTO.ImageDTO;
import com.caizhixiang.springboot.service.ImageService;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.sg.prism.web.NGWebView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @RequestMapping("/add")
    public ModelAndView toAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/add");
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView toEditPage(Model model,@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ImageDTO image = imageService.findById(id);
        modelAndView.addObject("image", image);
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }


    @RequestMapping("/findPage")
    public PageInfo<Image> findAll(Integer position, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "20") Integer PageSize) {

        return imageService.findPage(position, pageNo, PageSize);
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Image image) {

        imageService.saveOrUpdate(image);
        return "success";
    }
    @RequestMapping("/remove")
    public String remove(Integer id) {

        imageService.remove(id);
        return "success";
    }


    @RequestMapping("/upload")
    @ResponseBody
    Integer upload(@RequestParam("file") MultipartFile file) throws Exception{

        byte[] bytes = file.getBytes();
        Image image = new Image();
        image.setUrl(bytes);
        imageService.saveOrUpdate(image);


        return image.getId();
    }

}
