package com.lpit.myssh.dao.impl;


import org.springframework.stereotype.Repository;

import com.lpit.myssh.dao.IMenuDao;
import com.lpit.myssh.entity.Menu;

@Repository("menuDao")
public class MenuDao extends BaseDao<Menu> implements IMenuDao {


}
