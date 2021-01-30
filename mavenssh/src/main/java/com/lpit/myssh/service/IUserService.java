package com.lpit.myssh.service;

import java.io.Serializable;

import com.lpit.myssh.entity.User;
import com.lpit.myssh.pageModel.DataGrid;
import com.lpit.myssh.pageModel.Tuser;

public interface IUserService {
	
	public void test();
	
	public Serializable save(User user);
	
	public Tuser add(Tuser user);
	
	public Tuser login(Tuser user);
	
	public DataGrid dataGrid(Tuser user);
	
	public void remove(String ids);
	
	public Tuser update(Tuser user);

}
