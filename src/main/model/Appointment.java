package model;

import org.json.JSONObject;
import persistence.Booker;

import java.util.List;

// Represents information needed by the user to know details about the Appointment
public class Appointment implements Booker {
    // fields
    private List<Service> myServices;
    private String myBarber;
    private String myDay;
    private int myHour;
    private int myTimeSlotID;
    private int cost;
    private int length;
    private EventLog eventLog = EventLog.getInstance();

    //Constructor:
    /* EFFECTS: creates a new appointment instance with:
                no services added to myServices
                no barber assigned to myBarber
                no slot assigned to myAppointment
                initial cost is $0
                logs event
     */
    public Appointment() {
        myServices = null;
        myBarber = null;
        myDay = null;
        myHour = 0;
        myTimeSlotID = 0;
        cost = 0;
        length = 0;
        eventLog.logEvent(new Event("Appointment Created!"));
    }

    //MODIFIES: this
    //EFFECTS:  assigns a set of services to myServices
    //          logs event
    public void addServicesToMyServices(List<Service> services) {
        myServices = services;
        eventLog.logEvent(new Event("Services selected: " + myServices.size()));
    }

    //MODIFIES: this
    //EFFECTS: assigns a barber to the myBarber field
    //          logs event
    public void setMyBarber(Barber myBarber) {
        this.myBarber = myBarber.getBarberName();
        eventLog.logEvent(new Event("Barber selected: " + this.myBarber));
    }

    //MODIFIES: this
    //EFFECTS: assigns a barber to the myBarber field
    //          logs event
    public void setMyBarber(String myBarber) {
        this.myBarber = myBarber;
        eventLog.logEvent(new Event("Barber selected: " + this.myBarber));
    }

    //MODIFIES: this
    //EFFECTS: assigns a TimeSlot to myAppointment field
    //          logs event
    public void setMyTimeSlot(TimeSlot myTimeSlot) {
        myDay = myTimeSlot.getDay();
        myHour = myTimeSlot.getHour();
        myTimeSlotID = myTimeSlot.getId();
        eventLog.logEvent(new Event("Time Slot selected: " + myDay + ", " + myHour + "am"));
    }

    public void setMyDay(String myDay) {
        this.myDay = myDay;
    }

    public void setMyHour(int myHour) {
        this.myHour = myHour;
    }

    public void setMyTimeSlotID(int myTimeSlotID) {
        this.myTimeSlotID = myTimeSlotID;
    }

    //MODIFIES: this
    //EFFECTS: add the cost of all services in myServices and assigns total to cost
    //          logs event
    public void setCost() {
        int sumCost = 0;
        for (Service s : myServices) {
            sumCost += s.getServiceCost();
        }
        cost = sumCost;
        eventLog.logEvent(new Event("Total cost calculated: $" + cost));
    }

    //MODIFIES: this
    //EFFECTS: assigns the given cost to the field cost
    //          logs event
    public void setCostInt(int cost) {
        this.cost = cost;
        eventLog.logEvent(new Event("Total cost calculated: $" + this.cost));
    }

    //MODIFIES: this
    //EFFECTS: add the time length of all services in myServices and assigns total to length
    //          logs event
    public void setLength() {
        int sumLength = 0;
        for (Service s : myServices) {
            sumLength += s.getTime();
        }
        length = sumLength;
        eventLog.logEvent(new Event("Duration calculated: " + length + "mins"));
    }

    //MODIFIES: this
    //EFFECTS: assigns the given length to the field length
    public void setLength(int length) {
        this.length = length;
    }

    public String getMyBarber() {
        return myBarber;
    }

    public List<Service> getMyServices() {
        return myServices;
    }

    public int getCost() {
        return cost;
    }

    public int getLength() {
        return length;
    }

    public String getMyDay() {
        return myDay;
    }

    public int getMyHour() {
        return myHour;
    }

    public int getMyTimeSlotID() {
        return myTimeSlotID;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("myServices", myServices);
        json.put("myDay", myDay);
        json.put("myHour", myHour);
        json.put("myTimeSlotID", myTimeSlotID);
        json.put("myBarber", myBarber);
        json.put("cost",cost);
        json.put("length", length);
        return json;
    }
}
