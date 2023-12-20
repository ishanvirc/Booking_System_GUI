package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account account = reader.readAccount();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
        try {
            Account account = reader.readAccount();
            assertEquals("Jasmine", account.getName());
            assertTrue(account.getAppointments().isEmpty());
            assertEquals(100, account.getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
        try {
            Appointment testAppointment = new Appointment();
            testAppointment.setMyBarber("Jo");
            testAppointment.setCostInt(10);
            testAppointment.setMyTimeSlot(new TimeSlot("Monday", 5, 45));
            testAppointment.setLength(5);
            Account account = reader.readAccount();
            assertEquals("Jasmine", account.getName());
            List<Appointment> appointments = account.getAppointments();
            assertEquals(1, appointments.size());
            checkAccount("Jasmine", appointments, 100, account);
            checkAppointment(testAppointment, appointments.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBarber() {
        Barber ishan = null;
        try {
            JsonReader reader = new JsonReader("./data/testWriterBarber.json");
            ishan = reader.readBarber();
            assertEquals("ishan", ishan.getBarberName());
            List<Service> services = ishan.getBarberServices();
            List<TimeSlot> timeSlots = ishan.getBarberTimeSlots();
            assertEquals(3, services.size());
            assertEquals(3, timeSlots.size());
            checkService(new Service("HairCut", 60, 40), services.get(0));
            checkService(new Service("Beard", 25, 15), services.get(1));
            checkService(new Service("EyeBrows", 10, 5), services.get(2));
            checkTimeSlot(new TimeSlot("Monday", 9, 0), timeSlots.get(0));
            checkTimeSlot(new TimeSlot("Wednesday", 11, 6), timeSlots.get(1));
            checkTimeSlot(new TimeSlot("Friday", 12, 11), timeSlots.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
