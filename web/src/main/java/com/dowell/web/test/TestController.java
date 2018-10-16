package com.dowell.web.test;

import com.dowell.common.result.ResponseBo;
import com.dowell.dal.entity.UserEntity;
import com.dowell.service.user.UserService;
import com.dowell.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description 测试
 * @create 2018-09-28
 **/
@RestController
@RequestMapping("/test")
@Api(tags = "测试类")
public class TestController extends BaseController {

    @Autowired
    UserService userService;

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

}
