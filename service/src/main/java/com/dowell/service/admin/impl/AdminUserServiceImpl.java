package com.dowell.service.admin.impl;

import com.dowell.dal.mapper.AdminUsersMapper;
import com.dowell.dal.model.AdminUsers;
import com.dowell.dal.vo.AdminUsersVO;
import com.dowell.service.admin.AdminUserService;
import com.dowell.utils.page.PageList;
import com.dowell.utils.page.Paginator;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUsersMapper adminUsersMapper;

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return adminUsersMapper.queryAllMenuId(userId);
    }
}
