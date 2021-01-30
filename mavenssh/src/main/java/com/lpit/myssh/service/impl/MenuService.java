package com.lpit.myssh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.lpit.myssh.dao.IMenuDao;
import com.lpit.myssh.entity.Menu;
import com.lpit.myssh.pageModel.Tmenu;
import com.lpit.myssh.service.IMenuService;

@Service("menuService")
@Transactional
public class MenuService implements IMenuService<Menu> {
	
	private static final Logger logger = Logger.getLogger(MenuService.class);
	
	@Autowired
	private IMenuDao menuDao;
	

	@SuppressWarnings("unused")
	@Override
	public List<Tmenu> getTree(String id) {
		// TODO Auto-generated method stub
		logger.info("开始建菜单功书...");
		List<Tmenu> tl =new ArrayList<Tmenu>();
		
		String hql= null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Menu> l =new ArrayList<Menu>();
		if (id==null || id.equals("")) {
			hql="from Menu m where m.menu is null";
			 l = menuDao.find(hql);
		} else {
			hql="from Menu m where m.menu.id=:id";
			 l = menuDao.find(hql, params);
		}
		
		logger.info(hql);
//		List<Menu> l = menuDao.find(hql, params);
		
		if(l!=null && l.size()>0) {
			for (Menu t: l) {
				Tmenu m =new Tmenu();
				BeanUtils.copyProperties(t, m);
				Set<Menu> set = t.getMenus();
				if(set !=null && !set.isEmpty()) {
					m.setState("closed");
				}else {
					m.setState("open");
				}
				
				
				
				
				tl.add(m);
				
			}
		}
		logger.info(tl);
		return tl;
	}


	@Override
	public List<Tmenu> getAllTreeNode() {
		logger.info("开始建菜单功书...");
		List<Tmenu> tl =new ArrayList<Tmenu>();
		
		String hql= "from Menu m";
		List<Menu> l = menuDao.find(hql);

		
		if(l!=null && l.size()>0) {
			for (Menu t: l) {
				Tmenu m =new Tmenu();
				BeanUtils.copyProperties(t, m);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				Menu tm =t.getMenu();
				if(tm!=null) {
				m.setPid(tm.getId());
				}
//				m.setState("closed");
				tl.add(m);
				
			}
		}
		logger.info(tl);
		return tl;
	} 

}
