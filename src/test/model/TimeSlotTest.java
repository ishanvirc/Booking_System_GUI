package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for TimeSlot class
public class TimeSlotTest {
    TimeSlot testSlot;

    @BeforeEach
    void runBefore () {
        testSlot = new TimeSlot("Monday",8, 4);
    }

    @Test
    void testDay() {
        assertEquals("Monday", testSlot.getDay());
    }

    @Test
    void testHour() {
        assertEquals(8, testSlot.getHour());
    }

    @Test
    void testID() {
        assertEquals(4, testSlot.getId());
    }

}
