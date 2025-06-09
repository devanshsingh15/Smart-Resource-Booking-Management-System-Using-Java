package com.abes.rms.service;

import static org.junit.jupiter.api.Assertions.*; 

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import com.abes.rms.dto.Admin;
import com.abes.rms.dto.Booking;
import com.abes.rms.dto.RegularUser;
import com.abes.rms.util.CollectionsUtil;

public class AdminServiceTest {

    static AdminService adminService;

    @BeforeAll
    public static void setup() {
        adminService = new AdminService();
    }

    @BeforeEach
    public void setupUser() {
        CollectionsUtil.users.clear();
        CollectionsUtil.users.add(new RegularUser("dev", "Dev1", "Devansh", "devanshdmp15@gmail.com"));
    }

    @Test
    public void testUserVerify() {
        assertTrue(adminService.verifyUser("admin", "Admin1"),"Admin verified ");
    }

    @Test
    public void testUserVerifyFail() {
        assertFalse(adminService.verifyUser("admin", "Admin2"),"Admin should fail due to wrong password");
    }

    @Test
    public void testGetUser() {
        Admin admin = adminService.getUser("admin", "Admin1");
        assertNotNull(admin, "admin should not be null");
        assertEquals("admin", admin.getId(), "Admin ID matched");
    }

    @Test
    public void testGetUserFail() {
        Admin admin = adminService.getUser("admin", "Admin2");
        assertNull(admin, "Either null or wrong id and password");
    }

    @Test
    public void testShowAllUsers() {
        ArrayList<RegularUser> users = adminService.showAllUsers();
        assertNotNull(users, "User list should not be null");
        assertFalse(users.isEmpty(), "User list should not be empty");
    }

    @Test
    public void testShowAllBookings() {
        ArrayList<Booking> bookings = adminService.showAllBookings();
        assertNotNull(bookings, "Bookings list should not be null");
    }

    @Test
    public void testDeleteUser() {
        RegularUser tempUser = new RegularUser("test123", "Test1", "Test User", "test@gmail.com");
        CollectionsUtil.users.add(tempUser);
        assertTrue(adminService.deleteUser(tempUser), "Present User should be deleted");
    }

    @Test
    public void testDeleteUserFail() {
        RegularUser user = new RegularUser("test2", "Test2", "Test2 User", "test2@gmail.com");
        assertFalse(adminService.deleteUser(user), "Absent User should not be deleted");
    }
}
