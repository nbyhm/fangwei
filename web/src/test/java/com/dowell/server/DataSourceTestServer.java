package com.dowell.server;

import com.dowell.dal.entity.UserEntity;
import com.dowell.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nanbo
 * @description 测试多数据源
 * @create 2018-09-29
 **/
@Service
public class DataSourceTestServer {

    @Autowired
    UserService userService;

    public UserEntity getUser(Long userId){
        return null;
    }

}
