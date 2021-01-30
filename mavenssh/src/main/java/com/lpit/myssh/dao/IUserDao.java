package com.lpit.myssh.dao;

//import java.io.Serializable;
import java.util.List;

import com.lpit.myssh.entity.User;



public interface IUserDao extends IBaseDao<User> {
	
//	    public Serializable save(User user);   
	
	    public int addUser(User user);
		
		public int deleteUserById(Integer id);
		
		public int updateUserById(Integer id);
		
		public User selectUserById(Integer id);
		
		public List<User> listAllUsers();

}
