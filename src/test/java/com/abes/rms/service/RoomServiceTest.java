package com.abes.rms.service;

import com.abes.rms.dto.Room;
import com.abes.rms.util.CollectionsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    RoomService service;
    Room room1;
    Room room2;

    @BeforeEach
    void setup() {
        service = new RoomService();
        CollectionsUtil.rooms.clear();

        room1 = new Room("501", "AC", 500.0); // Available
        room2 = new Room("502", "Non-AC", 800.0);
        room2.setAvailable(false); // Not available

        CollectionsUtil.rooms.add(room1);
        CollectionsUtil.rooms.add(room2);
    }

    @Test
    void testShowAvailRooms() {
        ArrayList<Room> availableRooms = service.showAvailRooms();
        assertEquals(1, availableRooms.size());
        assertTrue(availableRooms.contains(room1));
    }

    @Test
    void testShowAvailRoomsFail() {
        ArrayList<Room> availableRooms = service.showAvailRooms();
        assertEquals(1, availableRooms.size());
        assertFalse(availableRooms.contains(room2));
    }

    @Test
    void testGetRoom() {
        Room result = service.getRoom("501");
        assertNotNull(result);
        assertEquals("501", result.getId());
    }

    @Test
    void testGetRoomFail() {
        Room result = service.getRoom("602");
        assertNull(result);
    }
}