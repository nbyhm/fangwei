package com.dowell.web;

import com.dowell.dal.model.AdminUsers;
import org.apache.shiro.SecurityUtils;

/**
 * @author nanbo
 * @description Controller公共组件
 * @createTime 2018-10-14
 */
public abstract class AbstractController {

    protected AdminUsers getUser() {
        return (AdminUsers) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
