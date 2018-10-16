package com.dowell.service.user;

import com.dowell.dal.entity.UserEntity;
import com.dowell.dal.form.UserForm;
import com.dowell.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description UserService接口继承IService接口
 * @create 2018-09-28
 **/
public interface UserService extends IService<UserEntity> {

    /**
     * 根据手机号查找用户信息
     * @param mobile 手机号
     * @return 返回用户信息
     */
    UserEntity findByMobile(String mobile);

    /**
     * 用户登录
     * @param form 登录表单
     * @return 返回登录信息
     */
    Map<String, Object> login(UserForm form);

    /**
     * 查询用户所有信息
     * @param userEntity
     * @return
     */
    List<UserEntity> list(UserEntity userEntity);

    /**
     *  根据主键批量删除用户
     * @param userIds
     */
    boolean deleteUsers(String userIds);

    List<UserEntity> queryFreezeUsers(String mobile, String userName);

    Integer sum1(String mobile);
}
