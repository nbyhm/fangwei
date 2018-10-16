package com.dowell.web.user;

import com.dowell.annotation.Login;
import com.dowell.annotation.LoginUser;
import com.dowell.annotation.UserLog;
import com.dowell.common.domain.QueryParams;
import com.dowell.common.result.ResponseBo;
import com.dowell.common.validator.ValidatorUtils;
import com.dowell.dal.entity.UserEntity;
import com.dowell.dal.form.UserForm;
import com.dowell.service.excel.ExcelService;
import com.dowell.service.token.TokenService;
import com.dowell.service.user.UserService;
import com.dowell.web.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author nanbo
 * @description 用户管理
 * @create 2018-09-30
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "用户接口")
public class UserController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	ExcelService excelService;

	@UserLog("用户注册")
	@PostMapping("register")
	@ApiOperation("用户注册")
	public ResponseBo register(@RequestBody UserForm form){

		//表单效验
		ValidatorUtils.validateEntity(form);
		try {
			//检查用户手机号是否存在
			UserEntity user = userService.findByMobile(form.getMobile());
			if (user != null){
				return ResponseBo.warn("该手机号已被使用");
			}
			UserEntity userEntity = new UserEntity();
			userEntity.setMobile(form.getMobile());
			userEntity.setUsername(form.getMobile());
			userEntity.setPassword(DigestUtils.sha256Hex(form.getPassword()));
			userEntity.setCreateTime(new Date());
			int result = userService.save(userEntity);
			return ResponseBo.ok();
		} catch (Exception e) {
			log.error("注册失败", e);
			return ResponseBo.error("注册失败，请联系管理员");
		}

	}

	@PostMapping("login")
	@ApiOperation("用户登录")
	public ResponseBo login(@RequestBody UserForm form){
		//表单效验
		ValidatorUtils.validateEntity(form);
		Map<String, Object> map = userService.login(form);
		return ResponseBo.ok(map);
	}

	/**
	 * @ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上
	 * @param userId
	 * @return
	 */
	@Login
	@PostMapping("logout")
	@ApiOperation("退出")
	public ResponseBo logout(@ApiIgnore @RequestAttribute("userId") Long userId){
		//设置token过期
		tokenService.expireToken(userId);
		return ResponseBo.ok();
	}

	@Login
	@GetMapping("/user/info")
	@ApiOperation(value = "获取用户信息",response = UserEntity.class)
	public ResponseBo userInfo(@LoginUser UserEntity user){
		return ResponseBo.ok().put("user", user);
	}

	@Login
	@PostMapping("/user/userId")
	@ApiOperation("获取用户ID")
	public ResponseBo userInfo(@RequestAttribute("userId") Integer userId){
		UserEntity currentUser = getCurrentUser();
		return ResponseBo.ok().put("userId", userId);
	}

	@GetMapping("notToken")
	@ApiOperation("忽略Token验证测试")
	public ResponseBo notToken(){
		return ResponseBo.ok("无需token也能访问。。。");
	}

	@UserLog("获取用户列表")
	@Login
	@PostMapping("user/list")
	@ApiOperation("获取用户列表")
	public ResponseBo userList(QueryParams params, UserEntity userEntity){
		PageHelper.startPage(params.getPageNum(), params.getPageSize());
		List<UserEntity> list = userService.list(userEntity);
		PageInfo<UserEntity> pageInfo = new PageInfo<>(list);
		return ResponseBo.ok().put("page",pageInfo);
	}

	@Login
	@PostMapping("user/export")
	@ApiOperation("导出用户信息")
	public ResponseBo userExportExcel(){
		try {
			List<UserEntity> list = userService.selectAll();
			//ResponseBo csv = excelService.createCsv("用户表", list, UserEntity.class);
			return excelService.createExcelByPoiKit("用户表", list, UserEntity.class);
		} catch (Exception e) {
			log.error("导出用户信息Excel失败", e);
			return ResponseBo.error("导出Excel失败，请联系管理员！");
		}
	}

}
