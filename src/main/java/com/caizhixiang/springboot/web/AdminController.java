package com.caizhixiang.springboot.web;


import com.caizhixiang.springboot.config.PropertyFtpConfig;
import com.caizhixiang.springboot.exception.BizException;
import com.caizhixiang.springboot.ftp.FtpClient;
import com.caizhixiang.springboot.mapper.entity.Detail;
import com.caizhixiang.springboot.mapper.entity.Dict;
import com.caizhixiang.springboot.mapper.entity.Image;
import com.caizhixiang.springboot.service.DetailService;
import com.caizhixiang.springboot.service.DictService;
import com.caizhixiang.springboot.service.ImageService;
import com.caizhixiang.springboot.service.enums.DictEnum;
import com.caizhixiang.springboot.service.enums.ErrorCodeEnum;
import com.caizhixiang.springboot.service.util.ImageUtil;
import com.caizhixiang.springboot.web.vo.ApiResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    @Autowired
    private FtpClient ftpClient;
    @Autowired
    private PropertyFtpConfig propertyFtpConfig;
    @Autowired
    private DictService dictService;
    @Autowired
    private DetailService detailService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        List<Dict> positions = dictService.findByCategory(DictEnum.IMAGE_POSITION.getCode());

        modelAndView.addObject("positions", positions);
        modelAndView.setViewName("admin/index1");
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView toAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Dict> positions = dictService.findByCategory(DictEnum.IMAGE_POSITION.getCode());

        modelAndView.addObject("positions", positions);
        modelAndView.setViewName("admin/add");
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView toEditPage(Model model, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Image image = imageService.findById(id);
        modelAndView.addObject("image", image);
        List<Dict> positions = dictService.findByCategory(DictEnum.IMAGE_POSITION.getCode());

        modelAndView.addObject("positions", positions);
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }


    @RequestMapping("/findPage")
    public PageInfo<Image> findAll(Integer position, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "20") Integer pageSize, String order, String orderName) {

        return imageService.findPage(position, pageNo, pageSize, order, orderName);
    }

    @RequestMapping("/saveOrUpdate")
    public ApiResult saveOrUpdate(Image image) throws IOException {

        imageService.saveOrUpdate(image);
        return new ApiResult();
    }

    @RequestMapping("/remove")
    public ApiResult remove(Integer id) {

        imageService.remove(id);

        return new ApiResult<>();
    }


    @RequestMapping("/upload")
    @ResponseBody
    ApiResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        Boolean flag = ftpClient.uploadFile(originalFilename, ImageUtil.saveMinPhoto(file.getInputStream()));
        if (!flag) {
            throw new BizException(ErrorCodeEnum.UPLOADFILEERROR);
        }
        String fileUrl = this.appendUrl(propertyFtpConfig.getUrlPrefix(), originalFilename);

        return new ApiResult(fileUrl);
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
        modelAndView.setViewName("admin/detail");
        return modelAndView;
    }

    @RequestMapping("/saveOrUpdateDetail")
    public ApiResult saveOrUpdateDetail(Detail detail) {

        detailService.saveOrUpdate(detail);
        return new ApiResult();
    }

    public String appendUrl(String first, String second) {
        if (StringUtils.isEmpty(first)) {
            return second;
        }
        if (StringUtils.isEmpty(second)) {
            return first;
        }
        // 两个衔接的均有斜杠
        if (first.endsWith("/") && second.startsWith("/")) {
            return first + second.substring(1);
        }
        // 两个衔接的均没有斜杠
        if (!first.endsWith("/") && !second.startsWith("/")) {
            return first + "/" + second;
        }
        return first + second;
    }


}
