package com.dowell.web.menu;

import com.dowell.dal.model.SysMenu;
import com.dowell.service.menu.SysMenuService;
import com.dowell.service.result.Result;
import com.dowell.web.AbstractController;
import com.dowell.web.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author nanbo
 * @description 系统菜单
 * @createTime 2018-10-14
 */
@Controller
@RequestMapping("/sys/menu")
@Api(tags = "系统菜单接口")
public class SysMenuController extends AbstractController {

    @Autowired
    SysMenuService sysMenuService;

    @ResponseBody
    @GetMapping("nav")
    @ApiOperation("导航菜单")
    public Result nav(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(1L);
        return ResultGenerator.genSuccessResult(menuList);
    }

}
