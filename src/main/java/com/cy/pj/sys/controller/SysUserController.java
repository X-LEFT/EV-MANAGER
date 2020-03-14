package com.cy.pj.sys.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.util.ShiroUtil;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("doGetLoginUser")
	public JsonResult doGetLoginUser() {
		return new JsonResult(ShiroUtil.getLoginUser());
	}
	
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
     }

	
	@RequestMapping("doLogin")
	public JsonResult doLogin(String username,String password,
			 boolean isRememberMe) {
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=
		new UsernamePasswordToken(username, password);
		if(isRememberMe)
		token.setRememberMe(true);
		subject.login(token);//提交给securityManager
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(sysUserService.findObjectById(id));
	}
	
	@RequestMapping("isExists")
	public JsonResult isExists(String columnName,String columnValue) {
		int rows=sysUserService.isExists(columnName, columnValue);
		return new JsonResult(rows);
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysUser entity,
			Integer[] roleIds) {
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysUser entity,
			Integer[] roleIds) {
		sysUserService.saveObject(entity, roleIds);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid) {
		sysUserService.validById(id, valid,ShiroUtil.getUsername());
		return new JsonResult("update ok");
	}
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
	}
	
}







