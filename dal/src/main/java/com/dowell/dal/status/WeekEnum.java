package com.dowell.dal.status;

/**
 * @author nanbo
 * @description 星期枚举，如果变量值仅在一个范围内变化用Enum类
 * @create 2018-10-02
 **/
public enum WeekEnum {
	//星期一
	MONDAY(1),
	//星期二
	TUESDAY(2),
	//星期三
	WEDNESDAY(3),
	//星期四
	THURSDAY(4),
	//星期五
	FRIDAY(5),
	//星期六
	SATURDAY(6),
	//星期日
	SUNDAY(7);
	public int week;

	WeekEnum(int week){
		this.week = week;
	}
}
