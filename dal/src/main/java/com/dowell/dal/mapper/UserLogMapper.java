package com.dowell.dal.mapper;

import com.dowell.dal.entity.UserLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author nanbo
 * @description 用户日志
 * @create 2018-10-05
 **/
@Component
@Mapper
public interface UserLogMapper extends BaseMapper<UserLogEntity> {

}
