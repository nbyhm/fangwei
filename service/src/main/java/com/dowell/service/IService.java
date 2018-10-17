package com.dowell.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nanbo
 * @description 通用Service,通用方法
 * @create 2018-09-28
 **/
@Service
public interface IService<T> {
    //查询
    List<T> selectAll();

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int batchDelete(List<String> list, String property, Class<T> clazz);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
}
