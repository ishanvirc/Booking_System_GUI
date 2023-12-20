package ui;

import exceptions.InvalidIDException;
import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a main menu GUI
public class MainMenuGUI implements ActionListener {

    private EventLog eventLog = EventLog.getInstance();
    // Frame
    private JFrame frame;
    // Panel
    private JPanel mainMenu;
    private JPanel barberSelection;
    private JPanel saveLoadPanel;
    // button (general)
    private JButton viewAppointment;
    private JButton addBalance;
    // button (barbers)
    private JButton barberJ;
    private JButton barberA;
    private JButton barberC;
    private JButton barberI;
    // button (save/load)
    private JButton save;
    private JButton load;
    // labels
    private JLabel label;
    private JLabel balance;
    private JLabel pickBarber;
    private JLabel saveLoad;
    // image
    private ImageIcon joIcon = new ImageIcon("resources/Giyuu.jpg");
    private ImageIcon ayIcon = new ImageIcon("resources/killua.jpg");
    private ImageIcon clIcon = new ImageIcon("resources/Clair.jpg");
    private ImageIcon isIcon = new ImageIcon("resources/Gojo.jpg");
    private Image joImg = joIcon.getImage();
    Image joScale = joImg.getScaledInstance(370, 220, Image.SCALE_SMOOTH);
    private ImageIcon joImage = new ImageIcon(joScale);
    private Image ayImg = ayIcon.getImage();
    Image ayScale = ayImg.getScaledInstance(370,220, Image.SCALE_SMOOTH);
    private ImageIcon ayImage = new ImageIcon(ayScale);
    private Image clImg = clIcon.getImage();
    Image clScale = clImg.getScaledInstance(370,220, Image.SCALE_SMOOTH);
    private ImageIcon clImage = new ImageIcon(clScale);
    private Image isImg = isIcon.getImage();
    Image isScale = isImg.getScaledInstance(370,220, Image.SCALE_SMOOTH);
    private ImageIcon isImage = new ImageIcon(isScale);
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
    private Account myAccount;
    private Barber jo;
    private Barber aymenn;
    private Barber clair;
    private Barber ishan;
    private final Service joHaircut = new Service("HairCut", 40, 35);
    private final Service joBeard = new Service("Beard", 15, 15);
    private final Service joEyeBrows = new Service("EyeBrows", 5, 10);
    private final Service aymennHaircut = new Service("HairCut", 50, 45);
    private final Service aymennBeard = new Service("Beard", 20, 10);
    private final Service aymennEyeBrows = new Service("EyeBrows", 5, 5);
    private final Service clairHaircut = new Service("HairCut", 20, 30);
    private final Service clairBeard = new Service("Beard", 10, 10);
    private final Service clairEyeBrows = new Service("EyeBrows", 5, 5);
    private final Service ishanHaircut = new Service("HairCut", 60, 40);
    private final Service ishanBeard = new Service("Beard", 25, 15);
    private final Service ishanEyeBrows = new Service("EyeBrows", 10, 5);

    //REQUIRES: a non-null account and barber
    //EFFECTS: Constructs a main menu for user and assigns the given account, also updates barber
    //         throws a runTimeException is account.getName is null.
    public MainMenuGUI(Account account, Barber barber) throws InvalidIDException {
        if (account.getName() == null) {
            throw new RuntimeException();
        }
        this.myAccount = account;
        initJSon();
        initJo();
        initAymenn();
        initClair();
        initIshan();
        updateBarber(barber);
        //Frame
        frameSetup();
        // Labels
        labelSetup();
        // Buttons
        buttonSetup();
        // Panel
        panelSetup();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame
    //          prints out event log upon close.
    private void frameSetup() {
        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next : eventLog) {
                    System.out.println(next.getDescription());
                }
                //THEN exit the program
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes all JPanel
    private void panelSetup() {
        mainMenu = new JPanel(new GridLayout(0, 1));
        barberSelection = new JPanel(new GridLayout(2, 2));
        barberSelection.setBackground(Color.black);
        saveLoadPanel = new JPanel(new GridLayout(0, 1));
        frame.add(mainMenu, BorderLayout.PAGE_START);
        frame.add(barberSelection, BorderLayout.CENTER);
        frame.add(saveLoadPanel, BorderLayout.PAGE_END);
        mainMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        barberSelection.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        saveLoadPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainMenu.add(label);
        mainMenu.add(viewAppointment);
        mainMenu.add(balance);
        mainMenu.add(addBalance);
        saveLoadPanel.add(saveLoad);
        saveLoadPanel.add(save);
        saveLoadPanel.add(load);
        mainMenu.add(pickBarber);
        barberSelection.add(barberI);
        barberSelection.add(barberA);
        barberSelection.add(barberC);
        barberSelection.add(barberJ);
    }

    // MODIFIES: this
    // EFFECTS: initialize JButtons
    private void buttonSetup() {
        viewAppointment = new JButton("Appointments");
        addBalance = new JButton("Add Balance");
        barberJ = new JButton(joImage);
        barberA = new JButton(ayImage);
        barberC = new JButton(clImage);
        barberI = new JButton(isImage);
        save = new JButton("Save");
        load = new JButton("Load");
        viewAppointment.addActionListener(this);
        barberJ.addActionListener(this);
        barberA.addActionListener(this);
        barberC.addActionListener(this);
        barberI.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        addBalance.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initialize JLabel
    private void labelSetup() {
        label = new JLabel("Hello, Mr." + myAccount.getName());
        balance = new JLabel("Account Balance: " + myAccount.getBalance());
        pickBarber = new JLabel("Choose your Barber:");
        saveLoad = new JLabel("Save/Load");
    }

   // Modifies: one-of Barber
    //EFFECTS: replaces one of the initialized barbers with given barber (if object equals)
    public void updateBarber(Barber barber) {
        if (jo.equals(barber)) {
            jo = barber;
        }
        if (aymenn.equals(barber)) {
            aymenn = barber;
        }
        if (clair.equals(barber)) {
            clair = barber;
        }
        if (ishan.equals(barber)) {
            ishan = barber;
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize JSonWriter and JsonReader
    public void initJSon() {
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

    // EFFECTS: opens and closes writer and writes given account to Json
    protected void setJsonWriterAccount(JsonWriter jsonWriter, Account account) throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(account);
        jsonWriter.close();
    }

    // EFFECTS: opens and closes writer and writes given barber to Json
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

    // EFFECTS: adds initial time slots to barbers
    public void addBarberTimeslot(Barber barber) {
        barber.addTimeSlots(new TimeSlot("Monday", 9, 0));
        barber.addTimeSlots(new TimeSlot("Wednesday", 10, 1));
        barber.addTimeSlots(new TimeSlot("Friday", 11, 2));
    }

    // EFFECTS: Initialize Barber Jo
    protected void initJo() {
        jo = new Barber("Giyu");
        jo.addService(joHaircut);
        jo.addService(joBeard);
        jo.addService(joEyeBrows);
        addBarberTimeslot(jo);
    }

    // EFFECTS: Initialize Barber Aymenn
    protected void initAymenn() {
        aymenn = new Barber("Killua");
        aymenn.addService(aymennHaircut);
        aymenn.addService(aymennBeard);
        aymenn.addService(aymennEyeBrows);
        addBarberTimeslot(aymenn);
    }

    // EFFECTS: Initialize Barber Clair
    protected void initClair() {
        clair = new Barber("Claire");
        clair.addService(clairHaircut);
        clair.addService(clairBeard);
        clair.addService(clairEyeBrows);
        addBarberTimeslot(clair);
    }

    // EFFECTS: Initialize Barber Ishan
    protected void initIshan() {
        ishan = new Barber("Gojo");
        ishan.addService(ishanHaircut);
        ishan.addService(ishanBeard);
        ishan.addService(ishanEyeBrows);
        addBarberTimeslot(ishan);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        Appointment newAppointment = new Appointment();
        if (e.getSource().equals(viewAppointment)) {
            new ViewAppointGUI(myAccount);
            frame.setVisible(false);
        }
        if (e.getSource().equals(addBalance)) {
            new DepositGUI(myAccount);
            frame.setVisible(false);
        }
        if (e.getSource().equals(barberJ)) {
            toBarber(newAppointment, jo);
        }
        if (e.getSource().equals(barberA)) {
            toBarber(newAppointment, aymenn);
        }
        if (e.getSource().equals(barberC)) {
            toBarber(newAppointment, clair);
        }
        if (e.getSource().equals(barberI)) {
            toBarber(newAppointment, ishan);
        }
        if (e.getSource().equals(save)) {
            saveAccount();
        }
        if (e.getSource().equals(load)) {
            loadAccount();
        }
    }

    // MODIFIES: closes this frame and opens another
    // EFFECTS: set barber to the appointment, close frame and open service selection.
    private void toBarber(Appointment newAppointment, Barber barber) {
        newAppointment.setMyBarber(barber);
        new ServiceGUI(barber, myAccount, newAppointment);
        frame.setVisible(false);
    }
}
