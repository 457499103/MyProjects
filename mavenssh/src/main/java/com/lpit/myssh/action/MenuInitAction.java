package com.lpit.myssh.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import com.lpit.myssh.entity.Menu;
import com.lpit.myssh.service.IMenuService;
import com.lpit.myssh.service.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="menuAction")
@AllowedMethods(value = {("getTree"),("getAllTreeNode"),("dataGrid")})
public class MenuInitAction extends BaseAction implements ModelDriven<Menu>{

	
	private Menu menu = new Menu();
	
	
	
	@Autowired
	private IMenuService menuService;
	
	@Override
	public Menu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
	
	public void getTree() {
		
		super.writejson(menuService.getTree(menu.getId()));
		
	}
	
	public void getAllTreeNode() {
		super.writejson(menuService.getAllTreeNode());
	}
	
	

}
