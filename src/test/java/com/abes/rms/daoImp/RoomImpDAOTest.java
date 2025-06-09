package com.abes.rms.daoImp;

import com.abes.rms.dto.Room;
import com.abes.rms.util.CollectionsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomImpDAOTest {

    RoomImpDAO dao;
    Room room1;
    Room room2;

    @BeforeEach
    void setup() {
        dao = new RoomImpDAO();
        CollectionsUtil.rooms.clear();
        room1 = new Room("501", "AC", 500.0);

        room2 = new Room("502", "Non-AC", 800.0);
        room2.setAvailable(false);
    }

    @Test
    void testAddRoom() {
        boolean result = dao.addRoom(room1);
        assertTrue(result);
        assertEquals(1, CollectionsUtil.rooms.size());
    }

    @Test
    void testEditRoomCost() {
        dao.addRoom(room1);
        boolean result = dao.editRoomCost("501", 700.0);
        assertTrue(result);
        assertEquals(700.0, dao.getRoom("501").getCostPerHour());
    }

    @Test
    void testEditRoomType() {
        dao.addRoom(room1);
        boolean result = dao.editRoomType("501", "Non-AC");
        assertTrue(result);
        assertEquals("Non-AC", dao.getRoom("501").getType());
    }

    @Test
    void testGetRoom() {
        dao.addRoom(room1);
        Room fetched = dao.getRoom("501");
        assertNotNull(fetched);
        assertEquals("501", fetched.getId());
    }

    @Test
    void testGetAvailRooms() {
        dao.addRoom(room1); // available
        dao.addRoom(room2); // not available
        ArrayList<Room> available = dao.getAvailRooms();
        assertEquals(1, available.size());
        assertTrue(available.contains(room1));
    }

    @Test
    void testUpdateRoomStatus() {
        dao.addRoom(room1);
        boolean updated = dao.updateRoomStatus(room1, false);
        assertTrue(updated);
        assertFalse(dao.getRoom("501").isAvailable());
    }

    @Test
    void testDeleteRoom() {
        dao.addRoom(room1);
        boolean deleted = dao.deleteRoom(room1);
        assertTrue(deleted);
        assertFalse(CollectionsUtil.rooms.contains(room1));
    }

    @Test
    void testGetAllRooms() {
        dao.addRoom(room1);
        dao.addRoom(room2);
        ArrayList<Room> all = dao.getAllRooms();
        assertEquals(2, all.size());
    }

    @Test
    void testGetBookedRooms() {
        dao.addRoom(room1);
        dao.addRoom(room2);
        ArrayList<Room> booked = dao.getBookedRooms();
        assertEquals(1, booked.size());
        assertTrue(booked.contains(room2));
    }

    @Test
    void testIsValid() {
        dao.addRoom(room1);
        assertTrue(dao.isValid(room1));
    }

    @Test
    void testIsAvailable() {
        dao.addRoom(room1);
        assertTrue(dao.isavailable(room1));
    }
}