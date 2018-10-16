package com.dowell.service.user.impl;

import com.dowell.common.exception.BizException;
import com.dowell.common.validator.Assert;
import com.dowell.dal.entity.TokenEntity;
import com.dowell.dal.entity.UserEntity;
import com.dowell.dal.form.UserForm;
import com.dowell.dal.mapper.UserMapper;
import com.dowell.service.base.BaseService;
import com.dowell.service.token.TokenService;
import com.dowell.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.*;

/**
 * @author nanbo
 * @description UserService中使用BaseService中的通用方法
 * @create 2018-09-28
 **/
@Service("userService")
public class UserServiceImpl extends BaseService<UserEntity> implements UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public UserEntity findByMobile(String mobile) {
        Example example = new Example(UserEntity.class);
        example.createCriteria().andCondition("lower(mobile)=", mobile.toLowerCase());
        List<UserEntity> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Map<String, Object> login(UserForm form) {
        UserEntity userEntity = findByMobile(form.getMobile());
        Assert.isNull(userEntity,"手机号或密码错误");

        //密码错误
        if (!userEntity.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
            throw new BizException("手机号或密码错误");
        }

        //获取用户登录token
        TokenEntity tokenEntity = tokenService.createToken(userEntity.getUserId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
        return map;
    }

    @Override
    public List<UserEntity> list(UserEntity userEntity) {

        try {
            Example example = new Example(UserEntity.class);
            Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(userEntity.getUsername())){
                criteria.andCondition("username=",userEntity.getUsername().toLowerCase());
            }
            if (StringUtils.isNotBlank(userEntity.getMobile())) {
                criteria.andCondition("mobile like", "%" + userEntity.getMobile() + "%");
            }
           /* if (StringUtils.isNotBlank(userEntity.getCrateTime())) {
                String[] timeArr = userEntity.getMobile().split("~");
                criteria.andCondition("date_format(CREATE_TIME,'%Y-%m-%d') >=", timeArr[0]);
                criteria.andCondition("date_format(CREATE_TIME,'%Y-%m-%d') <=", timeArr[1]);
            }*/
            example.setOrderByClause("create_time desc");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取用户信息失败{}", e);
            return new ArrayList<>();
        }

    }

    @Override
    public boolean deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        // 设置要删除的属性（删除存在的值）
        int result = this.batchDelete(list, "userId", UserEntity.class);
        return result > 0;
    }

	@Override
	public List<UserEntity> queryFreezeUsers(String mobile, String userName) {

        List<Integer> status = new ArrayList<>();
        status.add(0);
        Example example = new Example(UserEntity.class);
        example.createCriteria().orEqualTo("mobile", mobile)
                .orEqualTo("username", userName);

        //Criteria criteria = example.createCriteria();
        //criteria.orCondition("mobile = ", mobile);
        //criteria.orCondition("username = ", userName);
        List<UserEntity> list = userMapper.selectByExample(example);

        return list;
	}

	@Override
	public Integer sum1(String mobile) {
		return userMapper.sum1(mobile);
	}
}
