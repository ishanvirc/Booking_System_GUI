package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Account account = new Account("Jasmine");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            Account account = new Account("Jasmine");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            account = reader.readAccount();
            assertEquals("Jasmine", account.getName());
            assertTrue(account.getAppointments().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            Account account = new Account("Jasmine");
            Appointment testAppointment = new Appointment();
            testAppointment.setMyBarber("Jo");
            testAppointment.setCostInt(10);
            testAppointment.setMyTimeSlot(new TimeSlot("Monday", 5, 45));
            testAppointment.setLength(5);
            account.setAppointments(testAppointment);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.write(account);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            account = reader.readAccount();
            assertEquals("Jasmine", account.getName());
            List<Appointment> appointments = account.getAppointments();
            assertEquals(1, appointments.size());
            checkAccount("Jasmine", appointments, 100, account);
            checkAppointment(testAppointment, appointments.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterBarber() {
        try {
            Barber ishan = new Barber("ishan");
            ishan.addService(new Service("HairCut", 60, 40));
            ishan.addService(new Service("Beard", 25, 15));
            ishan.addService(new Service("EyeBrows", 10, 5));
            ishan.addTimeSlots(new TimeSlot("Monday", 9, 0));
            ishan.addTimeSlots(new TimeSlot("Wednesday", 11, 6));
            ishan.addTimeSlots(new TimeSlot("Friday", 12, 11));
            JsonWriter writer = new JsonWriter("./data/testWriterBarber.json");
            writer.open();
            writer.write(ishan);
            writer.close();

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
            fail("Exception should not have been thrown");
        }
    }

}
