package model;

import exceptions.InsufficientFundsException;
import exceptions.NegativeAmountException;
import org.json.JSONObject;
import persistence.Booker;

import java.util.ArrayList;
import java.util.List;

// Represents a user Account
public class Account implements Booker {
    //fields
    private String name;
    private List<Appointment> appointments;
    private double balance;
    private EventLog eventLog = EventLog.getInstance();

    // Constructor:
    /*
     * REQUIRES: accountName has a non-zero length
     * EFFECTS: creates an account with a given accountName,
     *          empty list of appointments
     *           initial balance is set to $100
     */
    public Account(String accountName) {
        name = accountName;
        appointments = new ArrayList<>();
        balance = 100;
        eventLog.logEvent(new Event("Account created: " + name));
    }

    // MODIFIES: this
    // EFFECTS: set the account balance to the given amount
    public void setBalance(double amount) {
        balance = amount;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to account balance
    //          throws NegativeAmountException if amount is a negative integer.
    //          logs event
    public void deposit(double amount) throws NegativeAmountException {
        if (amount < 0) {
            throw new NegativeAmountException();
        }
        balance += amount;
        eventLog.logEvent(new Event("Money Deposited!, new account balance: " + balance));
    }

    // MODIFIES: this
    // EFFECTS: pay for Appointment
    //          throws InsufficientFundsException if amount to pay > account balance
    //          logs event
    public void pay(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
        eventLog.logEvent(new Event("Amount payed: " + amount));
    }

    // MODIFIES: this
    // EFFECTS: adds appointment to the list of appointments
    //          logs event
    public void setAppointments(Appointment appointment) {
        appointments.add(appointment);
        eventLog.logEvent(new Event("Appointment Confirmed!, added to Account"));
        eventLog.logEvent(new Event("-----------------------------------------"));
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        eventLog.logEvent(new Event("Appointments viewed"));
        return appointments;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("appointments", appointments);
        json.put("balance", balance);
        eventLog.logEvent(new Event("Account Saved!"));
        return json;
    }
}
