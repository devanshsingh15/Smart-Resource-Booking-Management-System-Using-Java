package com.abes.rms.service;

import static org.junit.jupiter.api.Assertions.*; 

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import com.abes.rms.dto.RegularUser;
import com.abes.rms.dto.ResourceMan;
import com.abes.rms.dto.Room;

public class ManagerServiceTest {
	ManagerService service;

    @BeforeEach
    void setup() {
        service = new ManagerService(); 
    }

    @Test
    void testManagerSignUp() {
        boolean result = service.signUp("man1", "Man1", "Manager1", "manager1@gmail.com");
        assertTrue(result, "Signup should succeed.");
    }
    
    @Test
    void testVerifyManager() {
    	service.signUp("man2", "Man2", "Manager2", "manager2@gmail.com");
    	boolean verified = service.verifyUser("man2", "Man2");
        assertTrue(verified, "User verification failed.");
    }

    @Test
    void testDuplicateManagerSignUp() {
        service.signUp("man3", "Man3", "Manager3", "manager3@email.com");
        boolean result = service.signUp("man3", "Man3", "Manager3", "manager3@email.com");
        assertFalse(result, "Duplicate signup should fail.");
    }

    @Test
    void testGetManager() {
    	 service.signUp("man4", "Man4", "Manager4", "manager4@email.com");
    	 ResourceMan result = service.getUser("man4", "Man4");
    	 assertNotNull(result, "getManager should return manager4");
    }
    
    @Test
    void testAddRoom() {
        Room room = new Room("1", "AC", 150.0);
        boolean result = service.addRoom(room);
        assertTrue(result, "Room not added.");
    }

    @Test
    void testAddDuplicateRoom() {
        Room room1 = new Room("2", "Non-AC", 100.0);
        Room room2 = new Room("2", "AC", 200.0);
        service.addRoom(room1);
        boolean result = service.addRoom(room2);
        assertFalse(result, "Duplicate room ID should not add");
    }

    @Test
    void testEditRoomType() {
        Room room = new Room("3", "Non-AC", 120.0);
        service.addRoom(room);
        boolean result = service.editRoom("3", "AC");
        assertTrue(result, "Room type not updated.");
    }

    @Test
    void testEditRoomCost() {
        Room room = new Room("4", "AC", 150.0);
        service.addRoom(room);
        boolean result = service.editRoom("4", 200.0);
        assertTrue(result, "Room costnot updated.");
    }

    @Test
    void testDeleteRoom() throws Exception {
        Room room = new Room("5", "Non-AC", 90.0);
        service.addRoom(room);
        boolean result = service.deleteRoom("5");
        assertTrue(result, "Room should be deleted.");
    }

    @Test
    void testShowAllRooms() {
        service.addRoom(new Room("6", "AC", 180.0));
        ArrayList<Room> rooms = service.showAllRooms();
        assertNotNull(rooms, "Should return all rooms.");
        assertEquals(6, rooms.size());
    }
    
    @Test
    void testShowAllBookedRooms() {
    	service.addRoom(new Room("7", "AC", 180.0));
    	RegularUser user1 = new RegularUser("user1", "User1", "User1", "user1@gmail.com");
    	new UserService().bookRoom("7", user1, 2);
    	ArrayList<Room> booked = new UserService().showBookedRooms(user1);
    	assertEquals(1, booked.size(), "Should return booked room.");
    }
}
