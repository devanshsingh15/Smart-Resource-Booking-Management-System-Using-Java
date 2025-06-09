package com.abes.rms.dao;

import java.util.ArrayList;  
import com.abes.rms.dto.*;


public interface CartDAO {
	public ArrayList<Booking> getCart(RegularUser user);
	public ArrayList<Booking> getCartBooking(RegularUser user);
	public boolean updateRoom(RegularUser user, ArrayList<Booking> temp); 
}

