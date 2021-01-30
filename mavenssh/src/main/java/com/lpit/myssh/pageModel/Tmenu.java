package com.lpit.myssh.pageModel;

import java.io.Serializable;
import java.util.Map;
@SuppressWarnings("serial")
public class Tmenu implements Serializable{

    private String pid;
    private String ptext;

	
	
	
	
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPtext() {
		return ptext;
	}

	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	
	

	private String id;
	
	private String text;
	




	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



	private String micon;
	
//	private String url;
	
	private Map<String, Object> attributes;
	



	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}



	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getMicon() {
		return micon;
	}

	public void setMicon(String micon) {
		this.micon = micon;
	}

	@Override
	public String toString() {
		return "Tmenu [id=" + id + ", text=" + text + ", state=" + state + "]";
	}


/*	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}*/


	
	


}
