package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Result;
import com.baizhi.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author sfkstart
 * @create 2022-02-27-16:04
 */
//因为是前后端分离的项目，所以添加@RestController注解
@RestController
//允许跨域
@CrossOrigin
@RequestMapping("place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;


//    将目录注入过来了
    @Value("${upload.dir}")
    private String realPath;


    @GetMapping("delete")
    public Result delete(String id){

        Result result = new Result();
        try {
            placeService.delete(id);
            result.setMsg("删除景点信息成功");
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }



    /**
     * 文件上传
     * @param pic
     * @param place
     * @return
     */
    @PostMapping("save")
    public Result save(MultipartFile pic, Place place) throws IOException {

        Result result = new Result();


        try {
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //先做base64编码处理
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //做文件上传
            File file = new File(realPath);

            pic.transferTo(new File(realPath,newFileName));

            //保存place对象
            placeService.save(place);
            result.setMsg("保存景点信息成功");
        } catch (Exception e) {
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }






    /**
     * 根据省份id查询景点的方法
     */
    @GetMapping("findAllPlace")
    public Map<String ,Object> findAllPlace(Integer page, Integer rows, String provinceId){

        HashMap<String, Object> result = new HashMap<>();

        page = page == null ? 1 : page;
        rows = rows == null ? 3 : rows;
        //景点集合
        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);

        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        //现在counts是总页数，下面计算总条数
        Integer totalPage = counts % rows == 0 ? counts / rows : counts / rows + 1;

        result.put("places", places);
        result.put("page",page);
        result.put("counts",counts);
        result.put("totalPage",totalPage);
        return result;
    }

}
