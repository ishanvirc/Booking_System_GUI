package ui;

import exceptions.InsufficientFundsException;
import model.Account;
import model.Appointment;
import model.Barber;
import model.TimeSlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents a GUI to book services
public class SlotGUI implements ActionListener {

    // Fields
    private Account account;
    private Barber barber;
    private Appointment appointment;
    private JFrame frame;
    private JPanel slotPanel;
    private JButton slot1;
    private JButton slot2;
    private JButton slot3;
    private JLabel label;
    private JLabel errorMessage;
    private List<TimeSlot> availableSlots;

    // REQUIRES: barber, account, and appointment cannot be not null
    // EFFECTS: Constructs a Time slot selector GUI
    public SlotGUI(Account account, Barber barber, Appointment appointment) {
        this.barber = barber;
        this.account = account;
        this.appointment = appointment;
        availableSlots = this.barber.getBarberTimeSlots();
        frameLabelButtonSetup();
        panelSetup();
        frame.setVisible(true);
    }

    // EFFECTS: initialize JPanels
    private void panelSetup() {
        // Panel
        slotPanel = new JPanel(new GridLayout(0, 1));

        frame.add(slotPanel, BorderLayout.CENTER);
        slotPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        slotPanel.add(label);
        if (availableSlots.size() >= 1) {
            slot1.setText(availableSlots.get(0).getDay() + ", " + availableSlots.get(0).getHour() + "am");
            slotPanel.add(slot1);
        }
        if (availableSlots.size() >= 2) {
            slot2.setText(availableSlots.get(1).getDay() + ", " + availableSlots.get(1).getHour() + "am");
            slotPanel.add(slot2);
        }
        if (availableSlots.size() >= 3) {
            slot3.setText(availableSlots.get(2).getDay() + ", " + availableSlots.get(2).getHour() + "am");
            slotPanel.add(slot3);
        }
        slotPanel.add(errorMessage);
    }

    // EFFECTS: initialize JFrame, JButton, JLabel
    private void frameLabelButtonSetup() {
        // Frame
        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,1000);
        // Label
        label = new JLabel("Pick a Time:");
        errorMessage = new JLabel("");
        // Button
        slot1 = new JButton("");
        slot2 = new JButton("");
        slot3 = new JButton("");
        slot1.addActionListener(this);
        slot2.addActionListener(this);
        slot3.addActionListener(this);
    }

    // MODIFIES: this, account
    // EFFECTS: adds slot to appointment, adds appointment to account, opens bookedGUI and disposes current
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(slot1)) {
            bookAppointment(0);
        }
        if (e.getSource().equals(slot2)) {
            bookAppointment(1);
        }
        if (e.getSource().equals(slot3)) {
            bookAppointment(2);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds slot to appointment, adds appointment to account, opens bookedGUI and disposes current
    private void bookAppointment(int index) {
        try {
            appointment.setMyTimeSlot(availableSlots.get(index));
            appointment.setCost();
            appointment.setLength();
            account.pay(appointment.getCost());
            account.setAppointments(appointment);
            availableSlots.remove(availableSlots.get(index));
            new BookedGUI(account, barber);
            frame.dispose();
        } catch (InsufficientFundsException ex) {
            errorMessage.setText("Not enough funds to book selected services :(");
        }
    }
}
