package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Result;
import com.baizhi.travels.entity.User;
import com.baizhi.travels.service.UserService;
import com.baizhi.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sfkstart
 * @create 2021-11-24-21:43
 */
//将Controller转化为RestController，可以省去添加@ResponseBody
//@Controller
@RestController
@RequestMapping("user")
@CrossOrigin //允许跨域  因为以后的前端架构在其他地方  域不一样
@Slf4j //打印日志
public class UserController {

    @Autowired
    private UserService userService;


    //开发登录功能
    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        log.info("user: " + user);
        try {
            User userDB = userService.login(user);
            //登录成功之后保存用户的标记
            request.getServletContext().setAttribute(userDB.getId(), userDB);
            result.setMsg("登录成功~~~").setUserId(userDB.getId());
        } catch (Exception e) {
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }


    /**
     * 用户注册
     *
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) {

        Result result = new Result();

        log.info("接收的验证码：" + code);
        log.info("接收到user对象" + user);
        //验证验证码
        String keyCode = (String) request.getServletContext().getAttribute(key);
        log.info(keyCode);
        try {
            if (code.equalsIgnoreCase((keyCode))) {
                //注册用户
                userService.register(user);
                result.setMsg("注册成功！！！");
            } else {
                throw new RuntimeException("验证码错误！！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;

    }


    /**
     * 生成验证码
     *
     * @throws IOException 在前后端分离的架构中，在验证码阶段，不能获取我们上一期创建好的session
     *                     因此验证码不能往session中去存放。我们可以通过base64的形式对图片进行
     *                     编码，通过ajax去响应图片来解决
     */
    @GetMapping("getImage")
//要通过流去相应一个验证码   还要构建session 因为我们要把数据存储到session中
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
    /*
    验证码阶段解决方案
     */
        //1.获取验证码
        String securityCode = createImageCode.getCode();
    /*
    2.通过session的对象来获取应用里最大的作用域，即servletContext的作用域
    因为它是唯一的，因此不论哪个请求来，都可以取到
    使用时间戳来生成一个key 通过这种方式为每个请求来生成唯一的key
    setAttribute()作用就是保存数据，然后还可以用getAttribute方法来取出
    之后通过异步的方式将图片和值一起响应到前台
     */
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        //3.生成图片
        BufferedImage image = createImageCode.getBuffImg();

    /*
    4.进行base64的编码 我们将其写到 内存中的数组ByteArrayOutputStream 字节数组输出流
    其在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。
     */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        //将内存中的字节编码成base64的字符串
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }

}
