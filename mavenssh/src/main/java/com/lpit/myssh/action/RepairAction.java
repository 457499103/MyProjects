package com.lpit.myssh.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import com.lpit.myssh.service.IRepairService;



@Action(value="repairAction")
@AllowedMethods(value = {("init")})
public class RepairAction extends BaseAction {
	
	@Autowired
	private IRepairService  repairService;
	
	public void init() {
		
		repairService.repair();
		
	}
	
	

}
