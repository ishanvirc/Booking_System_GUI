package ui;

import exceptions.InsufficientFundsException;
import exceptions.InvalidIDException;
import exceptions.InvalidServiceSelection;
import exceptions.NegativeAmountException;
import model.Account;
import model.Barber;
import model.Appointment;
import model.Service;
import model.TimeSlot;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// BarberShop Booking Application
// reference source: Teller App
//                   JsonSerializationDemo
public class BarberApp {
    // fields
    private static final String JSON_STORE = "./data/barberApp.json";
    private static final String JSON_STORE1 = "./data/barberJo.json";
    private static final String JSON_STORE2 = "./data/barberAymenn.json";
    private static final String JSON_STORE3 = "./data/barberClair.json";
    private static final String JSON_STORE4 = "./data/barberIshan.json";
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriterJo;
    private JsonWriter jsonWriterAymenn;
    private JsonWriter jsonWriterClair;
    private JsonWriter jsonWriterIshan;
    private JsonReader jsonReader;
    private JsonReader jsonReaderJo;
    private JsonReader jsonReaderAymenn;
    private JsonReader jsonReaderClair;
    private JsonReader jsonReaderIshan;
    protected Account myAccount;
    protected Barber jo;
    protected Barber aymenn;
    protected Barber clair;
    protected Barber ishan;
    protected Scanner input;
    protected final Service joHaircut = new Service("HairCut", 40, 35);
    protected final Service joBeard = new Service("Beard", 15, 15);
    protected final Service joEyeBrows = new Service("EyeBrows", 5, 10);
    protected final Service aymennHaircut = new Service("HairCut", 50, 45);
    protected final Service aymennBeard = new Service("Beard", 20, 10);
    protected final Service aymennEyeBrows = new Service("EyeBrows", 5, 5);
    protected final Service clairHaircut = new Service("HairCut", 20, 30);
    protected final Service clairBeard = new Service("Beard", 10, 10);
    protected final Service clairEyeBrows = new Service("EyeBrows", 5, 5);
    protected final Service ishanHaircut = new Service("HairCut", 60, 40);
    protected final Service ishanBeard = new Service("Beard", 25, 15);
    protected final Service ishanEyeBrows = new Service("EyeBrows", 10, 5);

    //EFFECTS: runs the booking application
    public BarberApp() {
        myAccount = new Account("Jasmine");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);

        jsonWriterJo = new JsonWriter(JSON_STORE1);
        jsonWriterAymenn = new JsonWriter(JSON_STORE2);
        jsonWriterClair = new JsonWriter(JSON_STORE3);
        jsonWriterIshan = new JsonWriter(JSON_STORE4);

        jsonReader = new JsonReader(JSON_STORE);

        jsonReaderJo = new JsonReader(JSON_STORE1);
        jsonReaderAymenn = new JsonReader(JSON_STORE2);
        jsonReaderClair = new JsonReader(JSON_STORE3);
        jsonReaderIshan = new JsonReader(JSON_STORE4);
        runBooker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBooker() {
        boolean keepGoing = true;
        String command = null;

        initJo();
        initAymenn();
        initClair();
        initIshan();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for using the BarberApp!");
    }

    public void addBarberTimeslot(Barber barber) {
        barber.addTimeSlots(new TimeSlot("Monday", 9, 0));
        barber.addTimeSlots(new TimeSlot("Wednesday", 10, 1));
        barber.addTimeSlots(new TimeSlot("Friday", 11, 2));
    }

    // EFFECTS: Initialize Barber Jo
    protected void initJo() {
        jo = new Barber("Jo");
        jo.addService(joHaircut);
        jo.addService(joBeard);
        jo.addService(joEyeBrows);
        addBarberTimeslot(jo);
    }

    // EFFECTS: Initialize Barber Aymenn
    protected void initAymenn() {
        aymenn = new Barber("Aymenn");
        aymenn.addService(aymennHaircut);
        aymenn.addService(aymennBeard);
        aymenn.addService(aymennEyeBrows);
        addBarberTimeslot(aymenn);
    }

    // EFFECTS: Initialize Barber Clair
    protected void initClair() {
        clair = new Barber("Clair");
        clair.addService(clairHaircut);
        clair.addService(clairBeard);
        clair.addService(clairEyeBrows);
        addBarberTimeslot(clair);
    }

    // EFFECTS: Initialize Barber Ishan
    protected void initIshan() {
        ishan = new Barber("ishan");
        ishan.addService(ishanHaircut);
        ishan.addService(ishanBeard);
        ishan.addService(ishanEyeBrows);
        addBarberTimeslot(ishan);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\nAccount Balance: $" + myAccount.getBalance());
        System.out.println("\t0 -> View My Appointments");
        System.out.println("\t00 -> Add Balance");
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Barber Jo");
        System.out.println("\t2 -> Barber Aymenn");
        System.out.println("\t3 -> Barber Clair");
        System.out.println("\t4 -> Barber Ishan");
        System.out.println("\nSave/Load:");
        System.out.println("\ts -> save appointments to file");
        System.out.println("\tl -> load appointments from file");
        System.out.println("\nOther:");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("0")) {
            viewMyAppointment();
        } else if (command.equals("00")) {
            addBalance();
        } else if (command.equals("1")) {
            barber(jo, joHaircut, joBeard, joEyeBrows);
        } else if (command.equals("2")) {
            barber(aymenn, aymennHaircut, aymennBeard, aymennEyeBrows);
        } else if (command.equals("3")) {
            barber(clair, clairHaircut, clairBeard, clairEyeBrows);
        } else if (command.equals("4")) {
            barber(ishan, ishanHaircut, ishanBeard, ishanEyeBrows);
        } else if (command.equals("s")) {
            saveAccount();
        } else if (command.equals("l")) {
            loadAccount();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: view user booked appointments
    private void viewMyAppointment() {
        System.out.println("My Appointments");
        for (Appointment appointment : myAccount.getAppointments()) {
            System.out.println("--------------------------------------------------");
            System.out.println("Day: " + appointment.getMyDay());
            System.out.println("Time: " + appointment.getMyHour() + ":00 hrs");
            System.out.println("Barber: " + appointment.getMyBarber());
            System.out.println("Cost: $" + appointment.getCost());
            System.out.println("Length: " + appointment.getLength() + "mins");
            System.out.println("See you then!");
        }
        System.out.println("--------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: books an appointment with a barber
    public void barber(Barber barber, Service haircut, Service beard, Service eyeBrows) {
        Appointment newAppointment = new Appointment(); //might give me problems
        newAppointment.setMyBarber(barber);
        TimeSlot slot = null; // initialize booking time
        try {
            slot = bookingTime(barber.getBarberTimeSlots());
            newAppointment.setMyTimeSlot(slot); // add the booking time to appointment
            newAppointment.addServicesToMyServices(bookingService(haircut, beard, eyeBrows));
            newAppointment.setLength();
            newAppointment.setCost();
            myAccount.pay(newAppointment.getCost()); // pay for appointment
            myAccount.setAppointments(newAppointment);
            barber.getBarberTimeSlots().remove(slot);
            System.out.println("--------------------------------------------------");
            System.out.println("Appointment Booked!");
            System.out.println("--------------------------------------------------");
        } catch (InvalidIDException e) {
            System.out.println("Invalid time slot selection, please try again");
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient balance :( ");
            System.out.println("Unable to book appointment");
        } catch (InvalidServiceSelection e) {
            System.out.println("Invalid service selection, please try again");
        }
    }

    // EFFECTS: returns a time slot to set the day and time of Appointment
    public TimeSlot bookingTime(List<TimeSlot> barberTimeSlots) throws InvalidIDException {
        System.out.println("Please select a day and time slot:");
        // provide user with options for time slots
        for (TimeSlot t : barberTimeSlots) {
            System.out.println("\t" + t.getId() + " -> Day: " + t.getDay() + ", Time: " + t.getHour() + ":00");
        }
        double id = input.nextDouble(); // take in user input
        int exceptionThrower = 0; // initialize exception thrower
        // if the id is valid, the neutralize the exception thrower by changing its value
        for (TimeSlot t : barberTimeSlots) {
            if (id == t.getId()) {
                exceptionThrower = 1;
            }
        }
        if (exceptionThrower == 0) {
            throw new InvalidIDException(); // if the value is not changed, throw the exception
        }
        // at this point the id is valid.
        TimeSlot tempSlot = null; // initialize time slot
        for (TimeSlot t : barberTimeSlots) {
            if (id == t.getId()) {
                tempSlot = t;  // find the time slot with the id and fill it in tempSlot
            }
        }
        return tempSlot; // return the time slot chosen by the user.
    }


    // EFFECTS: adds amount to Account Balance
    private void addBalance() {
        System.out.println("Enter Amount to deposit: $ ");
        double amount = input.nextDouble();
        try {
            myAccount.deposit(amount);
            System.out.println("--------------------------------------------------");
            System.out.println("Amount Deposited!");
            System.out.println("--------------------------------------------------");
        } catch (NegativeAmountException e) {
            System.out.println("Cannot deposit a negative amount to Account Balance");
        }
    }

    // EFFECTS: returns Set<Service> booked
    private List<Service> bookingService(Service hair, Service beard, Service brow) throws InvalidServiceSelection {
        String selection = "";
        List<Service> servicePackage = new ArrayList<>();
        while (!(selection.equals("c"))) {
            System.out.printf("\t1 -> Haircut (Price: $%d,Length: %dmins)%n", hair.getServiceCost(), hair.getTime());
            System.out.printf("\t2 -> Beard (Price: $%d,Length: %dmins)%n", beard.getServiceCost(), beard.getTime());
            System.out.printf("\t3 -> EyeBrows (Price: $%d,Length: %dmins)%n", brow.getServiceCost(), brow.getTime());
            System.out.println("\tc -> Confirm");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("1")) {
                servicePackage.add(hair);
            } else if (selection.equals("2")) {
                servicePackage.add(beard);
            } else if (selection.equals("3")) {
                servicePackage.add(brow);
            }
        }
        if (servicePackage.isEmpty()) {
            throw new InvalidServiceSelection();
        }
        return servicePackage;
    }

    // EFFECTS: saves the Account to file
    //          and saves the Barbers to file
    protected void saveAccount() {
        try {
            setJsonWriterAccount(jsonWriter, myAccount);
            setJsonWriterBarber(jsonWriterJo, jo);
            setJsonWriterBarber(jsonWriterAymenn, aymenn);
            setJsonWriterBarber(jsonWriterClair, clair);
            setJsonWriterBarber(jsonWriterIshan, ishan);
            System.out.println("Saved " + myAccount.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    protected void setJsonWriterAccount(JsonWriter jsonWriter, Account account) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(account);
        jsonWriter.close();
    }

    protected void setJsonWriterBarber(JsonWriter jsonWriter, Barber barber) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(barber);
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: loads Account from file
    //          and loads Barbers from file
    protected void loadAccount() {
        try {
            myAccount = jsonReader.readAccount();
            jo = jsonReaderJo.readBarber();
            aymenn = jsonReaderAymenn.readBarber();
            clair = jsonReaderClair.readBarber();
            ishan = jsonReaderIshan.readBarber();
            System.out.println("Loaded " + myAccount.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

