package com.abes.rms.daoImp;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.abes.rms.util.CollectionsUtil;
import com.abes.rms.dto.RegularUser;

public class UserImpDAOTest {

    UserImpDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new UserImpDAO();
    }

    @Test
    public void testAddUser() {
    	CollectionsUtil.users.clear();
    	boolean result = dao.addUser("user1", "User1", "User1", "user1@gmail.com");
        assertTrue(result);
        assertEquals(1, CollectionsUtil.users.size());
    }

    @Test
    public void testDuplicateAddUser() {
        dao.addUser("user2", "User2", "User2", "user2@gmail.com");
        boolean result = dao.addUser("user2", "User2", "User2", "user2@gmail.com");
        assertFalse(result);
    }

    @Test
    public void testIsUserPresent() {
        dao.addUser("user3", "User3", "User3", "user3@gmail.com");
        assertTrue(dao.isUserPresent("user3", "User3"));
    }

    @Test
    public void testGetUser() {
        dao.addUser("user4", "User4", "User4", "user4@gmail.com");
        RegularUser user = dao.getUser("user4", "User4");
        assertNotNull(user, "getUser() should return a user.");
    }

    @Test
    public void testGetUserByEmail() {
        dao.addUser("user5", "User5", "User5", "user5@gmail.com");
        RegularUser user = dao.getUserByEmail("user5", "user5@gmail.com");
        assertNotNull(user, "getUserByEmail() must return a user5.");
    }

    @Test
    public void testDeleteUser() {
    	CollectionsUtil.users.clear();
        dao.addUser("user6", "User6", "User6", "user6@gmail.com");
        RegularUser user = dao.getUser("user6", "User6");
        assertTrue(dao.deleteUser(user), "deleteUser() is not deleting the user.");
        assertEquals(0, CollectionsUtil.users.size());
    }
}
