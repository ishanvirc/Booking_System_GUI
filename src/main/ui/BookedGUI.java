package ui;

import exceptions.InvalidIDException;
import model.Account;
import model.Barber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a image to confirm user's appointment is booked
public class BookedGUI implements ActionListener {

    private Account account;
    private Barber barber;
    private JFrame frame;
    private JPanel bookedImage;
    private JPanel continueOption;
    private JButton next;
    private JLabel bookedLabel;
    private ImageIcon bookedIcon = new ImageIcon("resources/checkmark.jpg");

    // EFFECTS: constructs a BookedGUI and passes through the given account and barber.
    public BookedGUI(Account account, Barber barber) {
        this.account = account;
        this.barber = barber;
        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        bookedLabel = new JLabel(bookedIcon);
        next = new JButton("Continue");
        next.addActionListener(this);
        bookedImage = new JPanel(new GridLayout(0, 1));
        bookedImage.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.add(bookedImage, BorderLayout.NORTH);
        bookedImage.add(bookedLabel);
        continueOption = new JPanel(new GridLayout(0, 1));
        frame.add(continueOption, BorderLayout.CENTER);
        continueOption.add(next);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes this, and passes account and barber to Main Menu GUI.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new MainMenuGUI(account, barber);
            frame.dispose();
        } catch (InvalidIDException ex) {
            new LoginGUI();
            frame.dispose();
        }
    }
}
