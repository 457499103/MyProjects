package com.lpit.myssh.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tb_user")
public class User {
	
	/**
	 * 
	 */

	
	@Id
	@Column(name="id" , unique=true, nullable=false, length=36)
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="pwd", length=32)
	private String pwd;
	@Column(name="creaetime")
	private Date creaetime;
	@Column(name="lastupdatetime")
	private Date lastupdatetime;
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
	
	
	
	public User() {
		super();
	}
	
	public User(String id, String name, String pwd, Date creaetime, Date lastupdatetime) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.creaetime = creaetime;
		this.lastupdatetime = lastupdatetime;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", creaetime=" + creaetime + ", lastupdatetime="
				+ lastupdatetime + "]";
	}
	
	
	
	
	
	
	

}
