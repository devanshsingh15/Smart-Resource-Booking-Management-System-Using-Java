package com.abes.rms.daoImp;

import java.util.ArrayList;

import com.abes.rms.dao.CartDAO;
import com.abes.rms.dto.*;
import com.abes.rms.util.CollectionsUtil;

public class CartImpDAO implements CartDAO {
	
	@Override
	public ArrayList<Booking> getCart(RegularUser user) {	
		for(RegularUser user1 : CollectionsUtil.cart.keySet()) {
			if(user1.getId().equals(user.getId())) {
				ArrayList<Booking> temp = CollectionsUtil.cart.get(user1);
				return temp;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Booking> getCartBooking(RegularUser user) {
		return CollectionsUtil.cart.get(user);
	}

	@Override
	public boolean updateRoom(RegularUser user, ArrayList<Booking> temp) {
		CollectionsUtil.cart.put(user, temp);
		return true;
	}

	
}




