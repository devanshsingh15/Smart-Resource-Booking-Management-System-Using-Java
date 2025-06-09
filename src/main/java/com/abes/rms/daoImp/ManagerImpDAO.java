package com.abes.rms.daoImp;

import com.abes.rms.dao.ManagerDAO;
import com.abes.rms.dto.*; 
import com.abes.rms.util.CollectionsUtil;

public class ManagerImpDAO implements ManagerDAO{
	@Override
	public boolean addUser(String id, String pass, String name, String email) {
		for (ResourceMan u : CollectionsUtil.managers) {
			if (u != null && u.getId().equals(id)) {
				return false;
			}
		}
		ResourceMan manager = new ResourceMan(id, pass, name, email);;
		
		CollectionsUtil.managers.add(manager);
		return true;
	}

	@Override
	public ResourceMan getUser(String userID, String pass) {
		for(ResourceMan user : CollectionsUtil.managers) {
			if(user.getId().equals(userID) && user.getPass().equals(pass)) return user;
		}
		return null;
	}
	
	@Override
	public boolean isManagerPresent(String userID, String pass) {
		for(ResourceMan manager : CollectionsUtil.managers) {
			if(manager.getId().equals(userID) && manager.getPass().equals(pass)) return true;
		}
		return false;
	}
}
