package com.dowell.dal.mapper;

import com.dowell.dal.model.AdminUsers;
import com.dowell.dal.vo.AdminUsersVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author nanbo
 * @description 系统管理员
 * @createTime 2018-5-10
 */
public interface AdminUsersMapper extends Mapper<AdminUsers> {

    List<AdminUsersVO> selectAllAdmin(@Param("id") Integer id);

    /**
     * admin登录
     * @param username
     * @param password
     * @return
     */
    AdminUsers login(@Param("username") String username,@Param("password") String password);

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

}
