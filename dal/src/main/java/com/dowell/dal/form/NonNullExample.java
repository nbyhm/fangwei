package com.dowell.dal.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author nanbo
 * @description 非零力
 * @create 2018-10-12
 **/
@Getter
@Setter
public class NonNullExample {
	private Long id;
	@NotNull
	private String name;
	private Date createAt;
}
