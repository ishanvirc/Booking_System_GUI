package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test for Appointment Class
public class AppointmentTest {

    // test fields
    Appointment testAppointment;

    @BeforeEach
    void runBefore() {
        testAppointment = new Appointment();
    }

    @Test
    void testConstructor() {
        assertEquals(null, testAppointment.getMyServices());
        assertEquals(null, testAppointment.getMyBarber());
        assertEquals(null, testAppointment.getMyDay());
        assertEquals(0, testAppointment.getMyHour());
        assertEquals(0, testAppointment.getMyTimeSlotID());
        assertEquals(0, testAppointment.getCost());
        assertEquals(0, testAppointment.getLength());
    }

    @Test
    void testAddService() {
        Service tstService = new Service("Haircut", 30, 10);
        List<Service> tstSet = new ArrayList<>();

        tstSet.add(tstService);
        testAppointment.addServicesToMyServices(tstSet);
        assertFalse(testAppointment.getMyServices().isEmpty());
        assertTrue(testAppointment.getMyServices().contains(tstService));

        Service tstService1 = new Service("Beard", 15, 15);
        tstSet.add(tstService1);
        assertTrue(testAppointment.getMyServices().contains(tstService1));

        tstSet.remove(tstService1);
        assertFalse(testAppointment.getMyServices().isEmpty());
        assertFalse(testAppointment.getMyServices().contains(tstService1));
    }

    @Test
    void testAddBarber() {
        Barber tstBarber = new Barber("Ishan");
        testAppointment.setMyBarber(tstBarber);
        assertEquals("Ishan", testAppointment.getMyBarber());
    }

    @Test
    void testAddAppointment() {
        TimeSlot tstSlot = new TimeSlot("Monday",  23, 1);
        testAppointment.setMyTimeSlot(tstSlot);
        assertEquals(1, testAppointment.getMyTimeSlotID());
        assertEquals("Monday", testAppointment.getMyDay());
        assertEquals(23, testAppointment.getMyHour());
    }

    @Test
    void testSetCost() {
        assertEquals(0, testAppointment.getCost());
        Service tstService = new Service("Haircut", 40, 10);
        Service tstService1 = new Service("Beard", 15, 15);
        Service tstService2 = new Service("Eyebrows", 5, 15);
        List<Service> tstSet = new ArrayList<>();
        tstSet.add(tstService);
        tstSet.add(tstService1);
        tstSet.add(tstService2);
        testAppointment.addServicesToMyServices(tstSet);
        testAppointment.setCost();
        assertEquals(60, testAppointment.getCost());
        testAppointment.setCostInt(30);
        assertEquals(30, testAppointment.getCost());
    }

    @Test
    void testSetLength() {
        assertEquals(0, testAppointment.getLength());
        Service tstService = new Service("Haircut", 40, 10);
        List<Service> tstSet = new ArrayList<>();
        Service tstService1 = new Service("Beard", 15, 15);
        Service tstService2 = new Service("Eyebrows", 5, 15);
        tstSet.add(tstService);
        tstSet.add(tstService1);
        tstSet.add(tstService2);
        testAppointment.addServicesToMyServices(tstSet);
        testAppointment.setLength();
        assertEquals(40, testAppointment.getLength());
        testAppointment.setLength(10);
        assertEquals(10, testAppointment.getLength());
    }
}