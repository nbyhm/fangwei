package com.dowell.dal.mapper;

import com.dowell.dal.model.SysMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author nanbo
 * @description 系统菜单
 * @createTime 2018-10-14
 */
public interface SysMenuMapper extends Mapper<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();
}
