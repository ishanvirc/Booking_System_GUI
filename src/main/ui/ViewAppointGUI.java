package ui;

import exceptions.InvalidIDException;
import model.Account;
import model.Appointment;
import model.Barber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents a GUI frame to view user appointments
public class ViewAppointGUI implements ActionListener {

    private Account account;
    private JFrame myAppointmentsFrame;
    private JPanel viewAppt;
    private JPanel nextBackButton;
    private JPanel exitPanel;
    private JLabel day;
    private JLabel time;
    private JLabel barber;
    private JLabel cost;
    private JLabel length;
    private JLabel endMessage;
    private JLabel generalLabel;
    private JLabel topBorder;
    private JLabel bottomBorder;
    private JButton view;
    private JButton next;
    private JButton back;
    private JButton exit;
    private int index;
    private List<Appointment> appointments;

    // REQUIRES: account to be non-null;
    // EFFECTS: constructs an appointment viewing GUI for the given account
    public ViewAppointGUI(Account account) {
        // account
        this.account = account;
        this.appointments = this.account.getAppointments();
        index = 0;
        // frame
        frameSetup();
        // Button
        buttonSetup();
        // Labels
        labelSetup();
        // Panel
        panelSetup();
        myAppointmentsFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame
    private void frameSetup() {
        myAppointmentsFrame = new JFrame();
        myAppointmentsFrame.setTitle("View My Appointments");
        myAppointmentsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myAppointmentsFrame.setSize(600,1000);
    }

    // MODIFIES: this
    // EFFECTS: initializes all JPanel
    private void panelSetup() {
        viewAppt = new JPanel(new GridLayout(0, 1));
        nextBackButton = new JPanel(new GridLayout(0, 2));
        exitPanel = new JPanel(new GridLayout(0, 1));
        myAppointmentsFrame.add(viewAppt, BorderLayout.CENTER);
        myAppointmentsFrame.add(nextBackButton, BorderLayout.PAGE_START);
        myAppointmentsFrame.add(exitPanel, BorderLayout.PAGE_END);
        viewAppt.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        nextBackButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        exitPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        viewAppt.add(generalLabel);
        viewAppt.add(topBorder);
        viewAppt.add(day);
        viewAppt.add(time);
        viewAppt.add(barber);
        viewAppt.add(cost);
        viewAppt.add(length);
        viewAppt.add(endMessage);
        viewAppt.add(bottomBorder);
        nextBackButton.add(back);
        nextBackButton.add(next);
        exitPanel.add(exit);
    }

    // MODIFIES: this
    // EFFECTS: initialize JLabel
    private void labelSetup() {
        generalLabel = new JLabel("My Appointments:");
        topBorder = new JLabel("----------------------------------------------------------------------");
        day = new JLabel("");
        time = new JLabel("");
        barber = new JLabel("");
        cost = new JLabel("");
        length = new JLabel("");
        bottomBorder = new JLabel("----------------------------------------------------------------------");
        endMessage = new JLabel("");
    }

    // MODIFIES: this
    // EFFECTS: initialize JButtons
    private void buttonSetup() {
        view = new JButton("View");
        view.addActionListener(this);
        next = new JButton("Next");
        next.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        exit = new JButton("Exit");
        exit.addActionListener(this);
    }

    // EFFECTS: on next, view next appointment, on back, view previous appointment, on exit dispose frame
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(next)) {
                if (index < appointments.size()) {
                    Appointment appointment = appointments.get(index);
                    displayAppointment(appointment);
                    index++;
                }
            }
            if (e.getSource().equals(back)) {
                if (index >= 0) {
                    index--;
                    Appointment appointment = appointments.get(index);
                    displayAppointment(appointment);
                }
            }
            if (e.getSource().equals(exit)) {
                exitViewAppt();
            }
        } catch (Exception ae) {
            exitViewAppt();
        }
    }

    // MODIFIES: this
    // EFFECTS: disposes current frame and opens a main menu frame and passes account
    private void exitViewAppt() {
        try {
            new MainMenuGUI(account, new Barber(""));
            myAppointmentsFrame.dispose();
        } catch (InvalidIDException e) {
            new LoginGUI();
            myAppointmentsFrame.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays information of given appointment.
    private void displayAppointment(Appointment appointment) {
        day.setText("Day: " + appointment.getMyDay());
        time.setText("Time: " + appointment.getMyHour() + ":00 am");
        barber.setText("Barber: " + appointment.getMyBarber());
        cost.setText("Cost: $" + appointment.getCost());
        length.setText("Length: " + appointment.getLength() + "mins");
        endMessage.setText("See you then!");
    }
}

