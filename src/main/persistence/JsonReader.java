package persistence;


import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account readAccount() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads barber from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Barber readBarber() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBarber(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Account account = new Account(name);
        addAppointments(account, jsonObject);
        addBalance(account, jsonObject);
        return account;
    }

    // EFFECTS: parses account from JSON object and returns it
    private Barber parseBarber(JSONObject jsonObject) {
        String barberName = jsonObject.getString("barberName");
        Barber barber = new Barber(barberName);
        addServices(barber, jsonObject);
        addTimeSlots(barber, jsonObject);
        return barber;
    }

    // MODIFIES: account
    // EFFECTS: parses balance from JSON object and sets it to account balance.
    private void addBalance(Account account, JSONObject jsonObject) {
        int balance = jsonObject.getInt("balance");
        account.setBalance(balance);
    }

    // MODIFIES: account
    // EFFECTS: parses appointments from JSON object and adds them to account
    private void addAppointments(Account account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("appointments");
        for (Object json : jsonArray) {
            JSONObject nextAppointment = (JSONObject) json;
            addAppointment(account, nextAppointment);
        }
    }

    // MODIFIES: account
    // EFFECTS: parses appointment from JSON object and adds it to account
    private void addAppointment(Account account, JSONObject jsonObject) {
        String myBarber = jsonObject.getString("myBarber");
        String myDay = jsonObject.getString("myDay");
        int myHour = jsonObject.getInt("myHour");
        int myTimeSlotID = jsonObject.getInt("myTimeSlotID");
        int cost = jsonObject.getInt("cost");
        int length = jsonObject.getInt("length");
        Appointment appointment = new Appointment();
        appointment.setMyBarber(myBarber);
        appointment.setMyDay(myDay);
        appointment.setMyHour(myHour);
        appointment.setMyTimeSlotID(myTimeSlotID);
        appointment.setCostInt(cost);
        appointment.setLength(length);
        account.setAppointments(appointment);
    }

    // MODIFIES: barber
    // EFFECTS: parses services from JSON object and adds them to barber
    private void addServices(Barber barber, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("barberServices");
        for (Object json : jsonArray) {
            JSONObject nextService = (JSONObject) json;
            addService(barber, nextService);
        }
    }

    // MODIFIES: barber
    // EFFECTS: parses service from JSON object and adds them to barber
    private void addService(Barber barber, JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        int serviceCost = jsonObject.getInt("serviceCost");
        int time = jsonObject.getInt("time");
        Service barberService = new Service(serviceName, serviceCost, time);
        barber.addService(barberService);
    }

    // MODIFIES: barber
    // EFFECTS: parses time slots from JSON object and adds them to barber
    private void addTimeSlots(Barber barber, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("barberTimeSlots");
        for (Object json : jsonArray) {
            JSONObject nextTimeSlot = (JSONObject) json;
            addTimeSlot(barber, nextTimeSlot);
        }
    }

    // MODIFIES: barber
    // EFFECTS: parses a time slot from JSON object and adds them to barber
    private void addTimeSlot(Barber barber, JSONObject jsonObject) {
        String day = jsonObject.getString("day");
        int hour = jsonObject.getInt("hour");
        int id = jsonObject.getInt("id");
        TimeSlot barberTimeSlot = new TimeSlot(day, hour, id);
        barber.addTimeSlots(barberTimeSlot);
    }
}
