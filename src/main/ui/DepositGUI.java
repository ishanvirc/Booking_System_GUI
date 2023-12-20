package ui;

import model.Account;
import model.Barber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a deposit portal
public class DepositGUI implements ActionListener {

    private Account myAccount;
    private JFrame frame;
    private JPanel panel;
    private JTextField userText;
    private JLabel label;
    private JButton done;


    // REQUIRES: a non-null account
    // EFFECTS:
    public DepositGUI(Account account) {
        // frame
        myAccount = account;
        frame = new JFrame();
        frame.setTitle("BarberShop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,200);
        // Label
        label = new JLabel("Enter Amount to Deposit: $");
        label.setBounds(10,20,80,25);
        // Text Field
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        // button
        done = new JButton("Deposit");
        done.addActionListener(this);
        // panel
        panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.add(label);
        panel.add(userText);
        panel.add(done);
        frame.setVisible(true);
    }

    // Adds user input to balance, if exception thrown, open a new login frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = userText.getText();
            double amount = Double.valueOf(input);
            myAccount.deposit(amount);
            new MainMenuGUI(myAccount, new Barber(""));
            frame.dispose();
        } catch (Exception ae) {
            new LoginGUI();
            frame.dispose();
        }
    }
}
