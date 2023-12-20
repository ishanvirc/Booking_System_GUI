package model;

import exceptions.InsufficientFundsException;
import exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for Account Class
class AccountTest {

    // test fields
    Account testAccount;

    @BeforeEach
    void runBefore () {
        testAccount = new Account("Ishan");
    }

    @Test
    void testSetBalance() {
        assertEquals(100, testAccount.getBalance());
        testAccount.setBalance(10);
        assertEquals(10, testAccount.getBalance());
    }

    @Test
    void testDepositExceptionNotThrown() {
        assertEquals(100, testAccount.getBalance());
        try {
            testAccount.deposit(10);
        } catch (NegativeAmountException e) {
            fail("NegativeAmount Exception thrown");
        }
        assertEquals(110, testAccount.getBalance());
    }

    @Test
    void testDepositExceptionThrown() {
        assertEquals(100, testAccount.getBalance());
        try {
            testAccount.deposit(-10);
        } catch (NegativeAmountException e) {
            // Expected
        }
        assertEquals(100, testAccount.getBalance());
    }

    @Test
    void testPayExceptionNotThrown() {
        assertEquals(100, testAccount.getBalance());
        try {
            testAccount.pay(50);
        } catch (InsufficientFundsException e) {
            fail("InsufficientFundsException thrown");
        }
        assertEquals(50, testAccount.getBalance());
    }

    @Test
    void testPayExceptionThrown() {
        assertEquals(100, testAccount.getBalance());
        try {
            testAccount.pay(150);
        } catch (InsufficientFundsException e) {
            // Expected
        }
        assertEquals(100, testAccount.getBalance());
    }

    @Test
    void testSetAppointment() {
        assertTrue(testAccount.getAppointments().isEmpty());

        Appointment testAppointment = new Appointment();
        Appointment testAppointment1 = new Appointment();

        testAccount.setAppointments(testAppointment);
        assertTrue(testAccount.getAppointments().contains(testAppointment));
        assertFalse(testAccount.getAppointments().contains(testAppointment1));

        testAccount.setAppointments(testAppointment1);
        assertTrue(testAccount.getAppointments().contains(testAppointment1));
    }
}