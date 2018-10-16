package com.dowell.dal.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author nanbo
 * @description 通用Mapper接口
 * @create 2018-09-28
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
