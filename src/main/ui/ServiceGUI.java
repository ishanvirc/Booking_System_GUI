package ui;

import model.Account;
import model.Appointment;
import model.Barber;
import model.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents a GUI to book services
public class ServiceGUI implements ActionListener {

    // Fields
    private Barber barber;
    private Account account;
    private Appointment appointment;
    private JFrame frame;
    private JPanel barberPanel;
    private JPanel countPanel;
    private JButton getHaircut;
    private int haircutCount;
    private int beardCount;
    private int browCount;
    private JButton getBeard;
    private JButton getEyebrow;
    private JButton next;
    private JLabel label;
    private JLabel noOfHaircut;
    private JLabel noOfBeard;
    private JLabel noOfBrow;
    private JLabel empty1 = new JLabel(" ");

    private Service haircut;
    private Service beard;
    private Service eyeBrow;
    private List<Service> servicePackage;

    // REQUIRES: barber, account, and appointment cannot be not null
    // EFFECTS: Constructs a ServiceGUI
    public ServiceGUI(Barber barber, Account account, Appointment appointment) {
        this.barber = barber;
        this.account = account;
        this.appointment = appointment;
        haircut = barber.getBarberServices().get(0);
        beard = barber.getBarberServices().get(1);
        eyeBrow = barber.getBarberServices().get(2);
        servicePackage = new ArrayList<>();
        haircutCount = 0;
        beardCount = 0;
        browCount = 0;
        // Frame
        frameSetup();
        // Label
        labelSetup();
        // Button
        buttonSetup();
        // Panel
        panelSetup();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame
    private void frameSetup() {
        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
    }

    // MODIFIES: this
    // EFFECTS: initializes all JPanel
    private void panelSetup() {
        barberPanel = new JPanel(new GridLayout(0, 1));
        countPanel = new JPanel(new GridLayout(0, 1));
        frame.add(barberPanel, BorderLayout.CENTER);
        frame.add(countPanel, BorderLayout.LINE_END);
        barberPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        countPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        barberPanel.add(label);
        barberPanel.add(getHaircut);
        barberPanel.add(getBeard);
        barberPanel.add(getEyebrow);
        barberPanel.add(next);
        countPanel.add(empty1);
        countPanel.add(noOfHaircut);
        countPanel.add(noOfBeard);
        countPanel.add(noOfBrow);
        countPanel.add(empty1);
        countPanel.add(empty1);
    }

    // MODIFIES: this
    // EFFECTS: initialize JButtons
    private void buttonSetup() {
        getHaircut = new JButton("Haircut, Price: $" + haircut.getServiceCost() + ", " + haircut.getTime() + "mins");
        getBeard = new JButton("Beard, Price: $" + beard.getServiceCost() + ", " + beard.getTime() + "mins");
        getEyebrow = new JButton("Brow, Price: $" + eyeBrow.getServiceCost() + ", " + eyeBrow.getTime() + "mins");
        next = new JButton("Next");
        getHaircut.addActionListener(this);
        getBeard.addActionListener(this);
        getEyebrow.addActionListener(this);
        next.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initialize JLabel
    private void labelSetup() {
        label = new JLabel("Choose a Service:");
        noOfHaircut = new JLabel(String.valueOf(haircutCount));
        noOfBeard = new JLabel(String.valueOf(beardCount));
        noOfBrow = new JLabel(String.valueOf(browCount));
    }

    // MODIFIES: account
    // EFFECTS: adds selected services to account and makes changes to labels
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getHaircut)) {
//            totalCost += haircut.getServiceCost();
            haircutCount++;
            bookService(haircutCount, noOfHaircut, haircut);
        }
        if (e.getSource().equals(getBeard)) {
//            totalCost += beard.getServiceCost();
            beardCount++;
            bookService(beardCount, noOfBeard, beard);
        }
        if (e.getSource().equals(getEyebrow)) {
//            totalCost += eyeBrow.getServiceCost();
            browCount++;
            bookService(browCount, noOfBrow, eyeBrow);
        }
        if (e.getSource().equals(next)) {
            nextStep();
        }
    }

    // MODIFIES: this, account
    // EFFECTS: adds servicePackage to account, pays for services, opens MainMenuGUI and disposes current frame
    private void nextStep() {
        appointment.addServicesToMyServices(servicePackage);
        new SlotGUI(account, barber, appointment);
        frame.dispose();
    }

    // EFFECTS: adds selected service to account
    private void bookService(int count, JLabel noOfService, Service service) {
        noOfService.setText(String.valueOf(count));
        servicePackage.add(service);
    }
}
