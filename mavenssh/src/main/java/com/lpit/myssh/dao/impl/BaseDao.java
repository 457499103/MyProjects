package com.lpit.myssh.dao.impl;

import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lpit.myssh.dao.IBaseDao;

@Repository("baseDao")
public class BaseDao<T> implements IBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getCurrentSession() {
		return this.sessionfactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return this.getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql) {
		// TODO Auto-generated method stub
		 Query q= this.getCurrentSession().createQuery(hql);
		 List<T> list = q.list();
		 if(list !=null && list.size()>0) {
			 return list.get(0);
		 }
		 return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Object[] parms) {
		Query q= this.getCurrentSession().createQuery(hql);
		
		if(parms !=null && parms.length>0) {
			for(int i=0; i<parms.length; i++) {
				q.setParameter(i, parms[i]);
			}
		}
		
		 List<T> list = q.list();
		 if(list !=null && list.size()>0) {
			 return list.get(0);
		 }
		
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, Map<String, Object> parms) {
		 Query q= this.getCurrentSession().createQuery(hql);
//		 String key="";
		 if(parms !=null && !parms.isEmpty()) {
			 
			 for (String o : parms.keySet()) {
				 q.setParameter(o, parms.get(o)); 
			 }
			 
			 
/*          Iterator<String> iterator = parms.keySet().iterator();
		 
		 while (iterator.hasNext()) {
			 
		     key =iterator.next();

		     q.setParameter(key, parms.get(key));
			
			 
		 }*/
		 }
		 
		 List<T> list = q.list();
		 if(list !=null && list.size()>0) {
			 return list.get(0);
		 }
		return null;
	}

	@Override
	public void delete(T o) {
		// TODO Auto-generated method stub
		
		this.getCurrentSession().delete(o);
		
	}

	@Override
	public void update(T o) {
		// TODO Auto-generated method stub
		this.getCurrentSession().update(o);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> parms) {
		// TODO Auto-generated method stub
		
		 Query q= this.getCurrentSession().createQuery(hql);

		 if(parms !=null && !parms.isEmpty()) {
			 
			 for (String o : parms.keySet()) {
				 q.setParameter(o, parms.get(o)); 
			 }
			 
		 }
		 
/*		 List<T> list = q.list();
		 if(list !=null && list.size()>0) {
			 return list;
		 }*/
	
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> parms, int page, int rows) {
		// TODO Auto-generated method stub
		
		Query q= this.getCurrentSession().createQuery(hql);
		
		 if(parms !=null && !parms.isEmpty()) {
			 
			 for (String o : parms.keySet()) {
				 q.setParameter(o, parms.get(o)); 
			 }
			 
		 }
		 
		return q.setFirstResult((page -1)*rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		
		Query q= this.getCurrentSession().createQuery(hql);
		

		return q.setFirstResult((page -1)*rows).setMaxResults(rows).list();
	}

	@Override
	public long count(String hql) {
		Query q= this.getCurrentSession().createQuery(hql);
		return (long) q.uniqueResult();
	}

	@Override
	public long count(String hql, Map<String, Object> parms) {
		// TODO Auto-generated method stub
		
		Query q= this.getCurrentSession().createQuery(hql);
		
		 if(parms !=null && !parms.isEmpty()) {
			 
			 for (String o : parms.keySet()) {
				 q.setParameter(o, parms.get(o)); 
			 }
			 
		 }
		return (long) q.uniqueResult();
	}

	@Override
	public void saveOrUpdate(T o) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(o);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		Query q= this.getCurrentSession().createQuery(hql);
		
		
		 List<T> list = q.list();
		 if(list !=null && list.size()>0) {
			 return list;
		 }
	
		return null;
	}

	@Override
	public T get(Class<T> c , Serializable id) {
		// TODO Auto-generated method stub
		return (T)this.getCurrentSession().get(c, id);
	}
	

	
	

}
