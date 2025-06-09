package com.abes.rms.daoImp;
import static org.junit.jupiter.api.Assertions.*; 

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import com.abes.rms.dto.Admin;
import com.abes.rms.dto.RegularUser;
import com.abes.rms.util.CollectionsUtil;
public class AdminImpDAOTest {

    static AdminImpDAO adminDao;

    @BeforeAll
    public static void setUp() {
        adminDao = new AdminImpDAO();
    }

    @AfterEach
    public void SetupUser() {
        CollectionsUtil.users.clear();
        CollectionsUtil.users.add(new RegularUser("dev", "Dev1", "Devansh", "devanshdmp15@gmail.com"));
    }

    @Test
    public void testisAdminPresent() {
        boolean result = adminDao.isAdminPresent("admin", "Admin1");
        assertTrue(result);
    }

    @Test
    public void testIsAdminPresentFail() {
        boolean result = adminDao.isAdminPresent("admin", "admin2");
        assertFalse(result);
    }

    @Test
    public void testGetAdmin() {
        Admin admin = adminDao.getAdmin("admin", "Admin1");
        assertNotNull(admin);
        assertEquals("admin", admin.getId());
    }

    @Test
    public void testGetAdminFail() {
        Admin admin = adminDao.getAdmin("admin", "admin2");
        assertNull(admin);
    }

    @Test
    public void testShowUsers() {
        ArrayList<RegularUser> users = adminDao.showUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void testShowUsersFail() {
        CollectionsUtil.users.clear();
        ArrayList<RegularUser> users = adminDao.showUsers();
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }
}
