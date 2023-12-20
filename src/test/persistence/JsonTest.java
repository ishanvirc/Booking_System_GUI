package persistence;

import model.Account;
import model.Appointment;
import model.Service;
import model.TimeSlot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAccount(String name, List<Appointment> appointments, int balance, Account account) {
        assertEquals(name, account.getName());
        assertEquals(appointments, account.getAppointments());
        assertEquals(balance, account.getBalance());
    }

    protected void checkAppointment(Appointment expectedAppointment, Appointment actualAppointment) {
        assertEquals(expectedAppointment.getMyBarber(), actualAppointment.getMyBarber());
        assertEquals(expectedAppointment.getMyDay(), actualAppointment.getMyDay());
        assertEquals(expectedAppointment.getCost(), actualAppointment.getCost());
        assertEquals(expectedAppointment.getLength(), actualAppointment.getLength());
        assertEquals(expectedAppointment.getMyHour(), actualAppointment.getMyHour());
        assertEquals(expectedAppointment.getMyTimeSlotID(), actualAppointment.getMyTimeSlotID());
    }

    protected void checkService(Service expectedService, Service actualService) {
        assertEquals(expectedService.getServiceName(), actualService.getServiceName());
        assertEquals(expectedService.getServiceCost(), actualService.getServiceCost());
        assertEquals(expectedService.getTime(), actualService.getTime());
    }

    protected void checkTimeSlot (TimeSlot expectedTimeSlot, TimeSlot actualTimeSlot) {
        assertEquals(expectedTimeSlot.getDay(), actualTimeSlot.getDay());
        assertEquals(expectedTimeSlot.getHour(), actualTimeSlot.getHour());
        assertEquals(expectedTimeSlot.getId(), actualTimeSlot.getId());
    }
}
