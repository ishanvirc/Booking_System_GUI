package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for Barber Class
public class BarberTest {

    // testBarbers
    Barber jo;
    Barber imposeterjo;
    Barber aymenn;
    Barber clair;

    @BeforeEach
    void runBefore() {
        jo = new Barber("Jo");
        imposeterjo = new Barber("Jo");
        aymenn = new Barber("Aymenn");
        clair = new Barber("Clair");
    }

    @Test
    void testName() {
        assertEquals("Jo", jo.getBarberName());
        assertEquals(imposeterjo, jo);
        assertNotEquals(null, jo);
        assertNotEquals(jo, aymenn);
        assertEquals("Aymenn", aymenn.getBarberName());
        assertEquals("Clair", clair.getBarberName());
    }

    @Test
    void testAddService() {
        assertTrue(jo.getBarberServices().isEmpty());
        Service joHaircut = new Service("Haircut", 40, 45);
        jo.addService(joHaircut);
        assertFalse(jo.getBarberServices().isEmpty());
        assertTrue(jo.getBarberServices().contains(joHaircut));
        jo.getBarberServices().remove(joHaircut);
        assertTrue(jo.getBarberServices().isEmpty());
        assertFalse(jo.getBarberServices().contains(joHaircut));

        Service aymennBeard = new Service("Beard", 20, 25);
        aymenn.addService(aymennBeard);
        assertTrue(aymenn.getBarberServices().contains(aymennBeard));

        Service clairEyeBrows = new Service("Eyebrows", 5, 5);
        clair.addService(clairEyeBrows);
        assertTrue(clair.getBarberServices().contains(clairEyeBrows));
    }

    @Test
    void testTimeSlots() {
        assertTrue(jo.getBarberTimeSlots().isEmpty());
        TimeSlot joTimeSlot = new TimeSlot("Friday",  8, 1);
        jo.addTimeSlots(joTimeSlot);
        assertFalse(jo.getBarberTimeSlots().isEmpty());
        assertTrue(jo.getBarberTimeSlots().contains(joTimeSlot));
        jo.getBarberTimeSlots().remove(joTimeSlot);
        assertTrue(jo.getBarberTimeSlots().isEmpty());
        assertFalse(jo.getBarberTimeSlots().contains(joTimeSlot));

        TimeSlot aymennTimeSlot = new TimeSlot("Wednesday",  15, 2);
        aymenn.addTimeSlots(aymennTimeSlot);
        assertTrue(aymenn.getBarberTimeSlots().contains(aymennTimeSlot));

        TimeSlot clairTimeSlot = new TimeSlot("Saturday", 20, 3);
        clair.addTimeSlots(clairTimeSlot);
        assertTrue(clair.getBarberTimeSlots().contains(clairTimeSlot));

    }
}
