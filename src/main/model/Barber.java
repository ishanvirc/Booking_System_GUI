package model;

import org.json.JSONObject;
import persistence.Booker;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a barber with a name, a list of services offered, and time slots available.
public class Barber implements Booker {
    // fields
    private String barberName;
    private List<Service> barberServices;
    private List<TimeSlot> barberTimeSlots;
    private EventLog eventLog = EventLog.getInstance();

    // Constructor:
    // REQUIRES: barberName has a non-zero length
    // EFFECTS: creates a barber with the given name,
    //          empty list of services offered,
    //          empty list of timeSlots available
    //          not available since no timeSlots available
    public Barber(String barberName) {
        this.barberName = barberName;
        barberServices = new ArrayList<>();
        barberTimeSlots = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds Service to the list of services offered by the barber.
    public void addService(Service service) {
        barberServices.add(service);
    }

    //MODIFIES: this
    //EFFECTS: adds time slot to the list of TimeSlots.
    public void addTimeSlots(TimeSlot slots) {
        barberTimeSlots.add(slots);
    }

    public String getBarberName() {
        return barberName;
    }

    public List<Service> getBarberServices() {
        return barberServices;
    }

    public List<TimeSlot> getBarberTimeSlots() {
        return barberTimeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Barber barber = (Barber) o;
        return Objects.equals(barberName, barber.barberName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barberName);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("barberName", barberName);
        json.put("barberServices", barberServices);
        json.put("barberTimeSlots", barberTimeSlots);
        eventLog.logEvent(new Event("Barber saved: " + barberName));
        return json;
    }
}
