package com.lpit.myssh.pageModel;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {
	private long total =0L;
	@SuppressWarnings("rawtypes")
	private List rows =new ArrayList();
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}
	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	

}
