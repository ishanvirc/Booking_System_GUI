package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for Service Class
public class ServiceTest {

    // test fields
    Service haircut;
    Service beard;
    Service eyeBrows;

    @BeforeEach
    void runBefore () {
        haircut = new Service("Haircut", 40, 45);
        beard = new Service("Beard", 15, 10);
        eyeBrows = new Service("Eyebrows", 5, 5);
    }

    @Test
    void testName() {
        assertEquals("Haircut", haircut.getServiceName());
        assertEquals("Beard", beard.getServiceName());
        assertEquals("Eyebrows", eyeBrows.getServiceName());
    }

    @Test
    void testCost() {
        assertEquals(40, haircut.getServiceCost());
        assertEquals(15, beard.getServiceCost());
        assertEquals(5, eyeBrows.getServiceCost());
    }

    @Test
    void testLength() {
        assertEquals(45, haircut.getTime());
        assertEquals(10, beard.getTime());
        assertEquals(5, eyeBrows.getTime());
    }
}
