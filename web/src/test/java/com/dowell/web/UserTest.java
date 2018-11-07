package com.dowell.web;

import com.dowell.WebApplicationTests;
import com.dowell.dal.entity.UserEntity;
import com.dowell.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author nanbo
 * @description 用户测试类
 * @create 2018-09-29
 **/
@Slf4j
public class UserTest extends WebApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void inserTest() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testName");
        userEntity.setPassword("123456");
        userEntity.setMobile("15301888888");
        userEntity.setStatus(userEntity.STATUS_VALID);
        userEntity.setCreateTime(new Date());

        int result = userService.save(userEntity);
        System.out.println("新增结果："+result);
    }

    @Test
    public void deleteTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(92L);

        //根据对象中传入的Id删除
        int result = userService.delete(userEntity);

        //可以直接传入key
        int count = userService.delete(96L);
        System.out.println(count);
    }

    @Test
    public void deleteUsersTest(){
        //测试批量删除(删除条件存在的数据)
        String ids = "97,99,100";
        boolean isOk = userService.deleteUsers(ids);
        System.out.println(isOk);
    }

    @Test
    public void updateTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(98L);
        userEntity.setUsername("nanbo");
        userEntity.setPassword("12345678");
        userEntity.setMobile("15301888612");
        userEntity.setStatus(userEntity.STATUS_VALID);
        userEntity.setModifyTime(new Date());
        int result = userService.updateNotNull(userEntity);
        userEntity.setUserId(97L);
        userEntity.setCreateTime(new Date());
        int result1 = userService.updateAll(userEntity);
        System.out.println(result);
    }

    @Test
    public void infoTest(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(4L);
        UserEntity entity = userService.selectByKey(userEntity);

        UserEntity userEntity1 = userService.selectByKey(93L);
        log.info("测试用户信息" + entity);

	   /* NonNullExample nonNullExample = new NonNullExample();
	    nonNullExample.setId(1L);
	    nonNullExample.setCreateAt(new Date());
	    log.info("测试 lombok:" + nonNullExample);*/
    }

    @Test
    public void queryFreezeUsersTest() {
        String mobile = "13119150962";
        String username = "15301888612";

        Integer sum1 = userService.sum1(mobile);

        Integer sum2 = userService.sum1(mobile);

        Integer total = sum1 - sum2;
        System.out.println(total);

        //List<UserEntity> list = userService.queryFreezeUsers(mobile, username);
        //for (UserEntity userEntity : list){
        //    if (userEntity.getStatus().equals("0")){
        //        System.out.println("改用已被冻结");
        //    }
        //}
        //
        //System.out.println(list);
    }
}
