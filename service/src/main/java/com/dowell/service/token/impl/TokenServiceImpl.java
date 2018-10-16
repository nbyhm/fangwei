package com.dowell.service.token.impl;

import com.dowell.dal.entity.TokenEntity;
import com.dowell.service.base.BaseService;
import com.dowell.service.token.TokenService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author nanbo
 * @description 用户Token
 * @create 2018-10-02
 **/
@Service("tokenService")
public class TokenServiceImpl extends BaseService<TokenEntity> implements TokenService {
	//12小时候过期
	private final static int EXPIRE = 3600 * 12;


	@Override
	public TokenEntity getUserToken(String token) {
		Example example = new Example(TokenEntity.class);
		example.createCriteria().andCondition("lower(token)=", token.toLowerCase());
		List<TokenEntity> list = this.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public TokenEntity createToken(Long userId) {
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//生成token
		String token = generateToken();

		//保存或更新用户token
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		tokenEntity.setUpdateTime(now);
		tokenEntity.setExpireTime(expireTime);

		TokenEntity tokenEntity1 = this.selectByKey(userId);
		if (tokenEntity1 == null){
			this.save(tokenEntity);
		}else {
			this.updateNotNull(tokenEntity);
		}
		return tokenEntity;
	}

	@Override
	public void expireToken(Long userId) {
		Date now = new Date();

		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setUpdateTime(now);
		tokenEntity.setExpireTime(now);
		this.updateNotNull(tokenEntity);
	}

	private String generateToken(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
