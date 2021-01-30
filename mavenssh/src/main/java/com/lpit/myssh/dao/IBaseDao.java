package com.lpit.myssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	
	public Serializable save(T o);
	
	public void delete(T o);
	
	public void update(T o);
	
	public void saveOrUpdate(T o);
	
	public T get(Class<T> c, Serializable id);
	
	public T get(String hql);
	
	public T get(String hql, Object[] parms);
	
	public T get(String hql, Map<String, Object> parms);
	
	public List<T> find(String hql, Map<String, Object> parms);
	
	public List<T> find(String hql, Map<String, Object> parms, int page, int rows);
	
	public List<T> find(String hql, int page, int rows);
	
	public List<T> find(String hql);
	
	public long count(String hql);
	
	public long count(String hql ,Map<String, Object> parms);

}
