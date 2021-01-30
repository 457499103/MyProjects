package com.lpit.myssh.action;


import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;



import com.lpit.myssh.entity.User;
import com.lpit.myssh.pageModel.Json;
import com.lpit.myssh.pageModel.Tuser;
import com.lpit.myssh.service.IUserService;
import com.opensymphony.xwork2.ModelDriven;


@ParentPackage("basePackage")
@Namespace("/")
@Action(value="userAction")
@AllowedMethods(value = {("test"),("test2"),("addUser"),("addReg"),("login"),("dataGrid"),("add"),("remove"),("update")})
public class UserAction extends BaseAction implements ModelDriven<Tuser> {
	
	Tuser user = new Tuser();
    
	@Override
	public Tuser getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	
	@Autowired
	private IUserService userService;
	
//	private String name;
//	private String pwd;
	
	
	
/*	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}*/
	
	

/*	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("进入我的默认Action!");
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		UserService userService = (UserService) ac.getBean("userService1");
		userService.test();
		return SUCCESS;
	}*/
	
//	@Action(value="userTestAction")
	
/*	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	*/

	public void test() {
		logger.info("进入我的第一个Action!");
	}
	
//	@Action(value="userTest2Action")
	public void test2() {
		logger.info("进入我的第二个Action!");
	}
	
	public void addUser() {
		
		User user = new User();
		
		user.setId(UUID.randomUUID().toString());
		user.setName("王翘楚的大哥");
		user.setPwd("123456");
		user.setCreaetime(new Date());
		
		userService.save(user);
		
		
		
		
	}
	
	public void addReg() {
		
		logger.info("进入我的用户注册Action!");
		
//		String name = ServletActionContext.getRequest().getParameter("name");
//		String pwd = ServletActionContext.getRequest().getParameter("pwd");
        //String json="";
//		Map<String , Object> m=new HashMap<>();
		Json j = new Json();
		try {
			logger.info(user.getName());
			logger.info(user.getPwd());
			Tuser u = new Tuser();
			BeanUtils.copyProperties(user, u);
			userService.add(u);
			//json= "{\"success\":true,\"message\":\"注册成功\"}";
//			m.put("success", true);
//			m.put("msg", "注册成功");
			
			j.setSuccess(true);
			j.setMsg("注册成功");
			
		} catch (Exception e) {
//			e.printStackTrace();
			//json= "{\"success\":true,\"message\":\"注册失败\"}";
//			m.put("success", false);
//			m.put("msg", e.getMessage());
		
			j.setMsg(e.getMessage());
		}
		
		
		
//		super.writejson(m);
		super.writejson(j);
		
		
		
/*		try {
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(m));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
public void login() {
		
		logger.info("进入我的用户登录Action!");
		

		Json j = new Json();
		

			Tuser u=userService.login(user);
			
			if (u!=null) {
				j.setSuccess(true);
				j.setMsg("登录成功");
			} else {
			j.setMsg("用户名或密码错误");
			logger.info(j.getMsg());
		}
		
		
		
		super.writejson(j);
		
		

	}

public void dataGrid() {
	logger.info(user.getName());
	super.writejson(userService.dataGrid(user));
}


public void add() {
	
	logger.info("进入我的用户添加Action!");

	Json j = new Json();
	try {

		Tuser u = userService.add(user);
        
		j.setObj(u);
		j.setSuccess(true);
		j.setMsg("添加成功");
		
	} catch (Exception e) {

		j.setMsg(e.getMessage());
	}
	
	
	

	super.writejson(j);

}

public void remove() {
	
	
	
	
	Json j = new Json();
	try {

		userService.remove(user.getIds());
		
		j.setSuccess(true);
		j.setMsg("删除成功");
		
	} catch (Exception e) {

		j.setMsg(e.getMessage());
	}
	
	
	

	super.writejson(j);
}

public void update() {
	
	logger.info("进入我的修改Action!");

	Json j = new Json();
	try {

		Tuser u = userService.update(user);
        
		j.setObj(u);
		j.setSuccess(true);
		j.setMsg("添加成功");
		
	} catch (Exception e) {

		j.setMsg(e.getMessage());
	}
	
	
	

	super.writejson(j);
	
}



public void updateinbatch() {
	
	Json j = new Json();
	try {

		userService.batchupdate(user.getIds());
		
		j.setSuccess(true);
		j.setMsg("添加成功");
		
	} catch (Exception e) {

		j.setMsg(e.getMessage());
	}
	
	
	

	super.writejson(j);
	
}

}
