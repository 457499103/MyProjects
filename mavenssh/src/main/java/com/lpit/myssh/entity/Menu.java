package com.lpit.myssh.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="tb_menu")	
public class Menu implements Serializable{
	
	@Id
	@Column(name="id" , unique=true, nullable=false, length=36)
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	private Menu menu;
	@Column(name="mtext")
	private String text;
	@Column(name="micon")
	private String micon;
	@Column(name="url")
	private String url;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="menu")
	private Set<Menu> menus = new HashSet<Menu>(0);
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMicon() {
		return micon;
	}
	public void setMicon(String micon) {
		this.micon = micon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	public Menu() {
		super();
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Menu(String id, Menu menu, String text, String micon, String url, Set<Menu> menus) {
		super();
		this.id = id;
		this.menu = menu;
		this.text = text;
		this.micon = micon;
		this.url = url;
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", text=" + text + ", url=" + url + "]";
	}

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
