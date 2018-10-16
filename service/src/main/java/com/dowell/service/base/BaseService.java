package com.dowell.service.base;

import com.dowell.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author nanbo
 * @description 特别说明：如果 **ServiceImpl有和BaseService中的方法重名的，将会优先使用 **ServiceImpl 中的方法
 * @create 2018-09-28
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public List<T> selectAll() {
        // 说明：查询所有数据
        return mapper.selectAll();
    }

    @Override
    public T selectByKey(Object key) {
        // 说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        // 说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        // 说明：根据主键字段进行删除，方法参数必须包含完整的主键属性(也可以直接传入主键值,比如：delete(95L))
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int batchDelete(List<String> list, String property, Class<T> clazz) {
        // 说明：根据Example条件批量删除（property：属性，根据属性删除存在的数据）
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    public int updateAll(T entity) {
        // 说明：根据主键更新实体“全部”字段，null值会被更新（entity如果没有传入值，数据库原始值也会被更新为null）
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        // 说明：根据主键更新属性不为null的值
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        // 说明：根据Example条件进行查询;重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
        return mapper.selectByExample(example);
    }
}
