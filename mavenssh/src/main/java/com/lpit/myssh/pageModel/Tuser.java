package com.lpit.myssh.pageModel;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class Tuser implements Serializable {
	
	
	private int page;
	private int rows;
	
	private String ids;
	
	
	
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	private String sort;
	private String order;
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	private String id;
	
	private String name;

	private String pwd;

	private Date creaetime;

	private Date lastupdatetime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creatdatatimestart;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creatdatatimeend;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastupdatedatatimestart;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastupdatedatatimeend;
	
	
	
	

	public Date getCreatdatatimestart() {
		return creatdatatimestart;
	}
	public void setCreatdatatimestart(Date creatdatatimestart) {
		this.creatdatatimestart = creatdatatimestart;
	}
	public Date getCreatdatatimeend() {
		return creatdatatimeend;
	}
	public void setCreatdatatimeend(Date creatdatatimeend) {
		this.creatdatatimeend = creatdatatimeend;
	}
	public Date getLastupdatedatatimestart() {
		return lastupdatedatatimestart;
	}
	public void setLastupdatedatatimestart(Date lastupdatedatatimestart) {
		this.lastupdatedatatimestart = lastupdatedatatimestart;
	}
	public Date getLastupdatedatatimeend() {
		return lastupdatedatatimeend;
	}
	public void setLastupdatedatatimeend(Date lastupdatedatatimeend) {
		this.lastupdatedatatimeend = lastupdatedatatimeend;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreaetime() {
		return creaetime;
	}
	public void setCreaetime(Date creaetime) {
		this.creaetime = creaetime;
	}
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

}
