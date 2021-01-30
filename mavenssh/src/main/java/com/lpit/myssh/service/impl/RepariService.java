package com.lpit.myssh.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpit.myssh.dao.IMenuDao;
import com.lpit.myssh.dao.IUserDao;
import com.lpit.myssh.entity.Menu;
import com.lpit.myssh.entity.User;
import com.lpit.myssh.service.IRepairService;
import com.lpit.myssh.utils.PwdSecurityMD5Util;

@Service("repairService")
@Transactional
public class RepariService implements IRepairService {
	
	private static final Logger logger = Logger.getLogger(RepariService.class);
	
	@Autowired
	private IMenuDao menuDao;
	
	@Autowired
	private IUserDao userDao;
	
	

	public IMenuDao getMenuDao() {
		return menuDao;
	}



	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

    

	@Override
	public void repair() {
		// TODO Auto-generated method stub
		
		repairMenu();
		repairUser();
		
	}
	
	private void repairMenu() {
		
		logger.info("开始修复菜单功能...");
		
		Menu root= new Menu();
		root.setId("0");
		root.setText("首页");
		root.setUrl("/admin/dashboard.jsp");
		logger.info(root);
		menuDao.saveOrUpdate(root);
		
		Menu xtgl= new Menu();
		xtgl.setId("xtgl");
		xtgl.setMenu(root);
		xtgl.setText("系统管理");
		xtgl.setUrl("");
		logger.info(xtgl);
		menuDao.saveOrUpdate(xtgl);
		
		Menu yhgl= new Menu();
		yhgl.setId("yhgl");
		yhgl.setMenu(xtgl);
		yhgl.setText("用户管理");
		yhgl.setUrl("/admin/yhgl.jsp");
		menuDao.saveOrUpdate(yhgl);
		
		
		Menu jsgl= new Menu();
		jsgl.setId("jsgl");
		jsgl.setMenu(xtgl);
		jsgl.setText("角色管理");
		jsgl.setUrl("/admin/jsgl.jsp");
		menuDao.saveOrUpdate(jsgl);
		
		Menu qxgl= new Menu();
		qxgl.setId("qxgl");
		qxgl.setMenu(xtgl);
		qxgl.setText("权限管理");
		qxgl.setUrl("/admin/qxgl.jsp");
		menuDao.saveOrUpdate(qxgl);
		
		
		Menu cdgl= new Menu();
		cdgl.setId("cdgl");
		cdgl.setMenu(xtgl);
		cdgl.setText("菜单管理");
		cdgl.setUrl("/admin/cdgl.jsp");
		menuDao.saveOrUpdate(cdgl);
		
		Menu bugl= new Menu();
		bugl.setId("bugl");
		bugl.setMenu(xtgl);
		bugl.setText("BUG管理");
		bugl.setUrl("/admin/bugl.jsp");
		menuDao.saveOrUpdate(bugl);		
		
		
		logger.info("完成修复菜单功能...");
		
	}




	private void repairUser() {
		logger.info("开始修复用户功能...");
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		User u = userDao.get("from User u where u.name = :name and u.id !='0'", m);
		
		if(u!=null) {
			u.setName(UUID.randomUUID().toString());
		}
		
		User admin = new User();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(PwdSecurityMD5Util.md5("a_12345678"));
		admin.setLastupdatetime(new Date());
		logger.info(admin);
		userDao.saveOrUpdate(admin);
		
//		menuDao.save(root);
		
		logger.info("完成修复菜用户能...");
		
	}

}
