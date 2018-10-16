package com.dowell.service.token;

import com.dowell.dal.entity.TokenEntity;
import com.dowell.service.IService;

/**
 * @author nanbo
 * @description 用户Token
 * @create 2018-10-02
 **/
public interface TokenService extends IService<TokenEntity> {

	/**
	 * 查询用户toke
	 * @param token
	 * @return
	 */
	TokenEntity getUserToken(String token);

	/**
	 * 生成token
	 * @param userId 用户ID
	 * @return 返回token信息
	 */
	TokenEntity createToken(Long userId);

	/**
	 * 设置token过期
	 * @param userId
	 */
	void expireToken(Long userId);
}
