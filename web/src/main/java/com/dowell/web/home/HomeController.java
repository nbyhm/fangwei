package com.dowell.web.home;

import com.dowell.common.result.ResponseBo;
import com.dowell.dal.entity.UserEntity;
import com.dowell.service.user.UserService;
import com.dowell.shiro.ShiroUtils;
import com.dowell.web.BaseController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description 测试
 * @create 2018-09-28
 **/
@Controller
@Api(tags = "测试类")
@Slf4j
public class HomeController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private Producer producer;

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation("查询所有用户信息")
    public ResponseBo index(){
        System.out.println("web");
        List<UserEntity> list = userService.selectAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("当前时间", DateUtils.createNow());
        map.put("list",list);
        return ResponseBo.ok(map);
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("name","SpringBootFavicon");
        return "index";
    }

}
