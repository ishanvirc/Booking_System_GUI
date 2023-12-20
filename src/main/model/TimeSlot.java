package model;

import org.json.JSONObject;
import persistence.Booker;


// Represents a time slot that can be booked by the user to make an appointment with a barber
public class TimeSlot implements Booker {
    private String day;
    private int hour; // in 24 hr clock
    private int id;

    // Constructor
    /* REQUIRES: d is one of (Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)
                 0 <= h <= 23
     */
    // EFFECTS: creates a time slot with a given day d and time of appointment h
    public TimeSlot(String d, int h, int id) {
        day = d;
        hour = h;
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getId() {
        return id;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("hour", hour);
        json.put("id", id);
        return json;
    }
}

