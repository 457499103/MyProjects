package com.lpit.myssh.service.impl;





import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpit.myssh.dao.IUserDao;
import com.lpit.myssh.entity.User;
import com.lpit.myssh.pageModel.DataGrid;
import com.lpit.myssh.pageModel.Tuser;
import com.lpit.myssh.service.IUserService;
import com.lpit.myssh.utils.PwdSecurityMD5Util;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	IUserDao userDao;
	
	
	
    public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	Logger logger = Logger.getLogger(getClass());
	@Override
	public void test() {
		// TODO Auto-generated method stub
//		System.out.println("ssss");
		
		logger.info("执行userService1");
		

	}
	@Override
	public Serializable save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}
	@Override
	public Tuser add(Tuser user) {
		// TODO Auto-generated method stub
		User t = new User();
		
//		t.setName(user.getName());
//		t.setPwd(user.getPwd());
		BeanUtils.copyProperties(user, t, new String[] {"pwd"});
		t.setId(UUID.randomUUID().toString());
		t.setPwd(PwdSecurityMD5Util.md5(user.getPwd()));
		t.setCreaetime(new Date());
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
        return user;
	}
	
	@Override
	public Tuser login(Tuser user) {
		// TODO Auto-generated method stub
		
//		User t= userDao.get("from User t where t.name='"+ user.getName() +"' and t.pwd = '"+PwdSecurityMD5Util.md5(user.getPwd())+"'" );
		
//		User t= userDao.get("from User t where t.name=? and t.pwd=?" , new Object[] {user.getName(),PwdSecurityMD5Util.md5(user.getPwd())});
		Map<String, Object> parms =new HashMap<String, Object>();
		parms.put("name", user.getName());
		parms.put("pwd", PwdSecurityMD5Util.md5(user.getPwd()));
		User t= userDao.get("from User t where t.name=:name and t.pwd=:pwd ",parms );
		logger.info(user.getName());
		logger.info(PwdSecurityMD5Util.md5(user.getPwd()));
		logger.info(t);
		if (t !=null) {
			return user;
		}
		
		return null;
	}

	@Override
	public DataGrid dataGrid(Tuser user) {
		DataGrid dg =new DataGrid();
		String hql= "from User u where 1=1";
		
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);
		
		
		
		String totalhql="select count(*)"+hql;
		
		hql = addSort(user, hql);
		
		List<User> l = userDao.find(hql,params, user.getPage(),user.getRows());
		List<Tuser> tl =new ArrayList<Tuser>();
		
		modelCovr(l, tl);
		dg.setTotal(userDao.count(totalhql,params));
		dg.setRows(tl);
		
		return dg;
	}
	private void modelCovr(List<User> l, List<Tuser> tl) {
		if (l!=null&& l.size()>0) {
			for(User t: l) {
				Tuser u =new Tuser();
				BeanUtils.copyProperties(t, u);
				tl.add(u);
			}
		}
	}
	private String addSort(Tuser user, String hql) {
		if(user.getSort()!=null) {
			hql += " order by "+user.getSort()+" "+user.getOrder();
		}
		return hql;
	}
	
	private String addWhere(Tuser user, String hql, Map<String, Object> params) {
		
		logger.info(user.getCreatdatatimestart());
		logger.info(user.getCreatdatatimeend());
		
		if(user.getName()!=null && !user.getName().trim().equals("")) {
			hql += " and u.name like :name ";
			params.put("name", "%%"+user.getName().trim()+"%%");
		}
		
		if(user.getCreatdatatimestart()!=null ) {
			hql +=" and u.creaetime >= :creatdatatimestart";
			params.put("creatdatatimestart", user.getCreatdatatimestart());
		}
		
		if(user.getCreatdatatimeend()!=null ) {
			hql +=" and u.creaetime <= :creatdatatimeend";
			params.put("creatdatatimeend", user.getCreatdatatimeend());
		}
		
		if(user.getLastupdatedatatimestart()!=null ) {
			hql +=" and u.lastupdatetime >= :lastupdatedatatimestart";
			params.put("lastupdatedatatimestart", user.getLastupdatedatatimestart());
		}
		
		if(user.getLastupdatedatatimeend()!=null ) {
			hql +=" and u.lastupdatetime <= :lastupdatedatatimeend";
			params.put("lastupdatedatatimeend", user.getLastupdatedatatimeend());
		}
		
		
		logger.info(hql);
		return hql;
	}
	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		for (String id: ids.split(",")) {
			
			User u= userDao.get(User.class, id);
			if (u!=null) {
				userDao.delete(u);
			}
		}
		
		
	}
	@Override
	public Tuser update(Tuser user) {
		// TODO Auto-generated method stub
		
		User t = new User();
		BeanUtils.copyProperties(user, t, new String[] {"pwd"});
		t.setPwd(PwdSecurityMD5Util.md5(user.getPwd()));
		
		userDao.saveOrUpdate(t);

		
		BeanUtils.copyProperties(t, user);
        return user;
	}


}
