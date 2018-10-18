package com.dowell.web.home;

import com.dowell.common.result.ResponseBo;
import com.dowell.dal.entity.UserEntity;
import com.dowell.service.user.UserService;
import com.dowell.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

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

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation("查询所有用户信息")
    public ResponseBo index(){
        System.out.println("web");
        List<UserEntity> list = userService.selectAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("当前时间", DateUtils.createNow());
        map.put("list",list);
        log.info("查询所有用户信息");
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
