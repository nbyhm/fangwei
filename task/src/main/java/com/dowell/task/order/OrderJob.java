package com.dowell.task.order;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author nanbo
 * @description
 * @create 2018-10-03
 **/
@Component
public class OrderJob {

	@Scheduled(fixedRate = 5000)
	public void scheduled(){
		System.out.println("===========>>>>使用fixedRate："+System.currentTimeMillis());
	}

	@Scheduled(fixedDelay = 5000)
	public void scheduled1(){
		System.out.println("===========>>>>使用fixedDelay："+System.currentTimeMillis());
	}

	@Scheduled(cron = "0/5 * * * * *")
	public void scheduled2(){
		System.out.println("===========>>>>使用cron："+System.currentTimeMillis());
	}

}
