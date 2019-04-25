package com.dowell.aspect;

import com.dowell.annotation.UserLog;
import com.dowell.dal.entity.UserLogEntity;
import com.dowell.service.user.UserLogService;
import com.dowell.util.HttpContextUtils;
import com.dowell.util.IPUtils;
import com.github.vindell.ip2region.spring.boot.IP2regionTemplate;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nutz.plugins.ip2region.DataBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author nanbo
 * @description 用户日志，切面处理
 * @create 2018-10-05
 **/
@Aspect
@Component
public class UserLogAspect {
	private Logger logger = LoggerFactory.getLogger(UserLogAspect.class);

	@Autowired
	private UserLogService userLogService;

	@Autowired
	IP2regionTemplate template;

	@Pointcut("@annotation(com.dowell.annotation.UserLog)")
	public void logPointCut(){

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		UserLogEntity userLogEntity = new UserLogEntity();
		UserLog userLog = method.getAnnotation(UserLog.class);
		if(userLog != null){
			//注解上的描述
			userLogEntity.setOperation(userLog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		userLogEntity.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args);
			userLogEntity.setParams(params);
		}catch (Exception e){

		}

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

		String ip = IPUtils.getIpAddr(request);
		//设置IP地址
		userLogEntity.setIp(ip);

		//用户名
		//String username = ((UserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		//LoginUser loginUser = method.getAnnotation(LoginUser.class);
		//userLogEntity.setUsername(loginUser.toString());

		userLogEntity.setTime(time);
		userLogEntity.setCreateTime(new Date());
		//ip2region:可以根据他获取一个具体ip的信息，国家、具体地址、网络服务商（jar包获取不到ip2region.db文件）
		//userLogEntity.setLocation(AddressUtils.getCityInfo(ip));

		//通过spring-boot-starter-ip2region获取地址信息
		try {
			DataBlock dataBlock = template.binarySearch(IPUtils.getIpAddr(request));
			userLogEntity.setLocation(dataBlock.getRegion());
		} catch (IOException e) {
			logger.info("spring-boot-starter-ip2region获取地址信息异常：{}", e);
		}
		//保存系统日志
		userLogService.save(userLogEntity);
	}
}
