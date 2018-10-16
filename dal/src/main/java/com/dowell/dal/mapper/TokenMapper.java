package com.dowell.dal.mapper;

import com.dowell.dal.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author nanbo
 * @description 用户Token
 * @create 2018-10-03
 **/
@Component
@Mapper
public interface TokenMapper extends BaseMapper<TokenEntity> {

}
