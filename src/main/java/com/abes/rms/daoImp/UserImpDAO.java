package com.abes.rms.daoImp;

import com.abes.rms.dao.UserDAO;
import com.abes.rms.dto.RegularUser; 
import com.abes.rms.util.CollectionsUtil;

public class UserImpDAO implements UserDAO{
	
	@Override
	public boolean addUser(String id, String pass, String name, String email) {
		for (RegularUser u : CollectionsUtil.users) {
			if (u != null && u.getId().equals(id)) {
				return false;
			}
		}
		RegularUser user = new RegularUser(id, pass, name, email);;
		CollectionsUtil.users.add(user);
		return true;
	}
	
	@Override
	public boolean isUserPresent(String userID, String pass) {
		for(RegularUser user : CollectionsUtil.users) {
			if(user.getId().equals(userID) && user.getPass().equals(pass)) return true;
		}
		return false;
	}
	
	@Override
	public RegularUser getUser(String userID, String pass) {
		for(RegularUser user : CollectionsUtil.users) {
			if(user.getId().equals(userID) && user.getPass().equals(pass)) return user;
		}
		return null;
	}
	
	@Override
	public RegularUser getUserByEmail(String userID, String email) {
		for(RegularUser user : CollectionsUtil.users) {
			if(user.getId().equals(userID) && user.getEmail().equals(email)) return user;
		}
		return null;
	}

	@Override
	public boolean deleteUser(RegularUser user) {
		return CollectionsUtil.users.remove(user);
	}
}

