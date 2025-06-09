package com.abes.rms.daoImp;

import org.junit.jupiter.api.*;
import com.abes.rms.util.CollectionsUtil;
import com.abes.rms.dao.ManagerDAO;
import com.abes.rms.dto.ResourceMan;
import java.util.ArrayList;

public class ManagerImpDAOTest {
    private ManagerDAO dao;

    @BeforeEach
    void setUp() {
        CollectionsUtil.managers = new ArrayList<>();
        dao = new ManagerImpDAO();
    }

    @Test
    void testAddUser() {
        boolean result = dao.addUser("man1", "Man1", "Manager1", "manager1@gmail.com");
        Assertions.assertTrue(result, "User not added.");
    }

    @Test
    void testAddDuplicateUser() {
        dao.addUser("man2", "Man2", "Manager2", "manager2@gmail.com");
        boolean result = dao.addUser("man2", "Man2", "Manager2", "manager2@gmail.com");
        Assertions.assertFalse(result, "Duplicate manager should not add.");
    }


    @Test
    void testGetUSer() {
        dao.addUser("man4", "Man4", "Manager4", "manager4@gmail.com");
        ResourceMan user = dao.getUser("man4", "Man1");
        Assertions.assertNull(user, "no user should return.");
    }

    @Test
    void testIsManagerPresent() {
        dao.addUser("man5", "Man5", "Manager5", "manager5@gmail.com");
        boolean result = dao.isManagerPresent("man5", "Man1");
        Assertions.assertFalse(result, "should not return manager with incorrect credentials.");
    }
}