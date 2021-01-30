package com.lpit.myssh.dao.impl;

//import java.io.Serializable;
import java.util.List;


//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lpit.myssh.dao.IUserDao;
import com.lpit.myssh.entity.User;


@Repository("userDao")
public class UserDao extends BaseDao<User>  implements IUserDao {
	
/*	@Autowired
	private SessionFactory sessionfactory;

	

	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}*/

	
	
/*	@Override
	public Serializable save(User user) {
		// TODO Auto-generated method stub
	
		return this.sessionfactory.getCurrentSession().save(user);
	}*/
	
	
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		
		
		return 0;
	}

	@Override
	public int deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}




}
