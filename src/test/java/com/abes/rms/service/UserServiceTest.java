package com.abes.rms.service;

import com.abes.rms.dto.RegularUser;
import com.abes.rms.dto.Room;
import com.abes.rms.exception.InvalidRoomException;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class UserServiceTest {

    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    void testSignUpUser() {
    	boolean result = service.signUp("user1", "User1", "User1", "user1@gmail.com");
        assertTrue(result, "Signup failed.");
    }
    
    @Test
    void testDuplicateSignUp() {
    	service.signUp("user2", "User2", "User2", "user2@example.com");
    	boolean result = service.signUp("user2", "User2", "User2", "user2@gmail.com");
    	assertFalse(result, "duplicate user should not signup.");
    }
    
    @Test
    void testBookRoom() {
    	RegularUser user = new RegularUser("user2", "User2", "User2", "user2@gmail.com");
    	boolean result = service.bookRoom("202", user, 2);
    	assertTrue(result, "room should be booked.");
    }
    
    @Test
    void testCancelRoom() {
    	RegularUser user = new RegularUser("user3", "User3", "User3", "user3@gmail.com");
    	service.bookRoom("102", user, 2);
    	boolean result = service.cancelRoom("102", user, new RoomService().getRoom("102"));
    	assertTrue(result, "Room should be canceled");
    }
    
    @Test
    void testShowBookedRooms() {
    	RegularUser user = new RegularUser("user4", "User4", "User4", "user4@gmail.com");
    	service.bookRoom("102", user, 2);
    	ArrayList<Room> result = service.showBookedRooms(user);
    	assertEquals(1, result.size());
    }

    @Test
    void testAddToCart() throws InvalidRoomException {
    	RegularUser user = new RegularUser("user5", "User5", "User5", "user5@gmail.com");
    	boolean result = service.addToCart(user, "103", 2);
    	assertTrue(result, "room not added to cart.");
    }
    
    @Test
    void testVerifyUser() {
    	service.signUp("user6", "User6", "User6", "user6@gmail.com");
    	boolean result = service.verifyUser("user6", "User6");
    	assertTrue(result, "should verify.");
    	result = service.verifyUser("user2", "User1");
        assertFalse(result, "should not verify.");
    }

    @Test
    void testGetUser() {
        service.signUp("user7", "User7", "User7", "user7@gmail.com");
        RegularUser user = service.getUser("user7", "User7");
        assertNotNull(user, "A User should be returned.");
    }

    @Test
    void testGetUserByEmail() {
        service.signUp("user8", "User8", "User8", "user8@gmail.com");
        RegularUser user = service.getUserByEmail("user8", "user8@gmail.com");
        assertNotNull(user, "user should be returned.");
    }
   
}
