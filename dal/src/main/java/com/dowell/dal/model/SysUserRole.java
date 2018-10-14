package com.dowell.dal.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author nanbo
 * @description 用户与角色对应关系
 * @createTime 2018-10-14
 */
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 设置：
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：用户ID
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     * @return Long
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：角色ID
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     * @return Long
     */
    public Long getRoleId() {
        return roleId;
    }
}
