package com.dowell.web;

import com.dowell.dal.model.SysMenu;
import com.dowell.service.menu.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author nanbo
 * @description
 * @createTime 2018-10-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminTest {

    @Autowired
    SysMenuService sysMenuService;

    @Test
    public void menuTest(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(1L);
        log.info("测试菜单列表" + menuList);
    }

}
