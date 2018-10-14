package com.dowell.service.admin;

import java.util.List;

public interface AdminUserService {

    /**
     * 查询用户的所有菜单Id
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

}
