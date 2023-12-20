package ui;

import exceptions.InvalidIDException;
import model.Account;
import model.Barber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a login portal.
public class LoginGUI implements ActionListener {

    // fields
    private JFrame frame;
    private JPanel panel;
    private JTextField userText;
    private JLabel label;
    private JButton login;

    // EFFECTS: constructs a login portal, assigns given name to the account
    public LoginGUI() {

        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,200);
        // Label
        label = new JLabel("Name: ");
        label.setBounds(10,20,80,25);
        // Text Field
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        // button
        login = new JButton("Login");
        login.addActionListener(this);
        // panel
        panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.add(label);
        panel.add(userText);
        panel.add(login);
        frame.setVisible(true);
    }

    // EFFECTS: opens MainMenuGUI with the account of the given name. if name is null, opens another login portal
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = userText.getText();
        try {
            new MainMenuGUI(new Account(input), new Barber(""));
            frame.dispose();
        } catch (InvalidIDException ex) {
            new LoginGUI();
            frame.dispose();
        }
    }
}
