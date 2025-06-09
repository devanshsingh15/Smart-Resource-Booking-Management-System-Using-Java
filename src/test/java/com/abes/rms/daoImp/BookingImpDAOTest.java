package com.abes.rms.daoImp;

import com.abes.rms.dto.*;
import com.abes.rms.util.CollectionsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookingImpDAOTest {

    BookingImpDAO dao;
    RegularUser user;
    Room room;

    @BeforeEach
    void setup() {
        dao = new BookingImpDAO();
        CollectionsUtil.bookRecord.clear();
        user = new RegularUser("User", "User1", "user", "user@example.com");
        room = new Room("104", "AC", 1000.0);
    }

    @Test
    void testAddBooking() {
        Booking booking = new Booking(user, room, 2.0, 2000.0);
        boolean result = dao.addBooking(booking);
        assertTrue(result);
        assertEquals(1, dao.getNoOfBookings());
    }

    @Test
    void testShowBookings() {
        Booking booking = new Booking(user, room, 1.0, 1000.0);
        CollectionsUtil.bookRecord.add(booking);

        ArrayList<Booking> bookings = dao.showBookings();
        assertEquals(1, bookings.size());
        assertEquals(user.getId(), bookings.get(0).getUser().getId());
    }

    @Test
    void testZeroBooking() {
        assertEquals(0, dao.getNoOfBookings());
    }

    @Test
    void testMultipleBooking() {
        CollectionsUtil.bookRecord.add(new Booking(user, room, 1.0, 1000.0));
        CollectionsUtil.bookRecord.add(new Booking(user, room, 2.0, 2000.0));
        assertEquals(2, dao.getNoOfBookings());
    }

    @Test
    void testZeroRevenue() {
        assertEquals(0.0, dao.calcRevenue(), 0.001);
    }

    @Test
    void testMultipleRevenue() {
        CollectionsUtil.bookRecord.add(new Booking(user, room, 1.0, 1000.0));
        CollectionsUtil.bookRecord.add(new Booking(user, room, 2.0, 2000.0));
        double revenue = dao.calcRevenue();
        assertEquals(3000.0, revenue, 0.001);
    }
}