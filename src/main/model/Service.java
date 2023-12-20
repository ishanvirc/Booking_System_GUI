package model;


import org.json.JSONObject;
import persistence.Booker;

// Represents a Service that a barber provides with a name, cost, length of service
public class Service implements Booker {
    // fields
    private String serviceName;
    private int serviceCost; // in $
    private int time; // in minutes

    // Constructor:
/*
     REQUIRES: serviceName has a non-zero length
               serviceCost is >= 0
               length is >= 0
     EFFECTS: creates a service with a given serviceName,
                                with a given price,
                                with a given time length,
 */
    public Service(String serviceName, int serviceCost, int length) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        time = length;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public int getTime() {
        return time;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("serviceName", serviceName);
        json.put("serviceCost", serviceCost);
        json.put("time", time);
        return json;
    }
}
