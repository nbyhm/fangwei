package com.dowell.dal.vo;

import com.dowell.dal.model.AdminUsers;

/**
 *VO，用于查询或传递信息
 */
public class AdminUsersVO extends AdminUsers {

	private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}