package com.lpit.myssh.service;

import java.util.List;

import com.lpit.myssh.pageModel.Tmenu;



public interface IMenuService<Menu> {
	
	public List<Tmenu> getTree(String id);
	
	public List<Tmenu> getAllTreeNode();

}
