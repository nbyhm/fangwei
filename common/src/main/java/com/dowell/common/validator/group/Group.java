package com.dowell.common.validator.group;

import javax.validation.GroupSequence;

/**
 * @author nanbo
 * @description 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @create 2018-10-02
 **/
@GroupSequence({AddGroup.class, UpdateGroup.class})
public class Group {

}
