package com.dowell.dal.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author nanbo
 * @description admin_users
 * @create 2018-05-10 21:56
 **/
@Table(name = "admin_users")
public class AdminUsers implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String username;
    private String password;

    private String name;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;

    private String salt;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 部门ID
     */
    //@NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Transient
    private Long deptId;

    public AdminUsers() {
    }

    public AdminUsers(AdminUsers adminUsers) {
        this.id = adminUsers.id;
        this.username = adminUsers.username;
        this.password = adminUsers.password;
        this.name = adminUsers.name;
        this.rememberToken = adminUsers.rememberToken;
        this.createdAt = adminUsers.createdAt;
        this.updatedAt = adminUsers.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
