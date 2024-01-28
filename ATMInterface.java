import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount1 class to represent the user's bank account
class BankAccount {
    private double balance;
    private final int pin;

    public BankAccount(double initialBalance, int pin) {
        balance = initialBalance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public int getPin() {
        return pin;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// ATM1 class to represent the ATM machine
class ATM extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JPasswordField pinField;
//    private JTextArea outputArea;

    public ATM(BankAccount account) {
        this.account = account;

        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Changed layout to accommodate 3 buttons

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinField = new JPasswordField(4);
        inputPanel.add(pinLabel);
        inputPanel.add(pinField);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(8);
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        JButton checkBalanceButton = new JButton("Check Balance"); // Added new button
        checkBalanceButton.addActionListener(this);

        depositButton.setPreferredSize(new Dimension(120, 20));
        withdrawButton.setPreferredSize(new Dimension(120, 20));
        checkBalanceButton.setPreferredSize(new Dimension(120, 20));
        
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(checkBalanceButton); // Added new button

//        outputArea = new JTextArea(5, 10);
//        outputArea.setEditable(false);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER); // Modified to include the button panel
//        panel.add(outputArea, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null); // Center the frame on the screen
        
    }

    @Override
    
    public void actionPerformed(ActionEvent e) {
        char[] pinChars = pinField.getPassword();
        int enteredPin = Integer.parseInt(new String(pinChars));

        if (enteredPin != account.getPin()) {
            JOptionPane.showMessageDialog(this, "Invalid PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (e.getActionCommand().equals("Deposit")) {
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            account.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposit successful.\nNew balance: $" + account.getBalance(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("Withdraw")) {
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!account.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Withdrawal successful.\nNew balance: $" + account.getBalance(), "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Check Balance")) {
            JOptionPane.showMessageDialog(this, "Current balance: $" + account.getBalance(), "Balance", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0, 1234); // Change 1234 to your desired PIN
        ATM atm = new ATM(userAccount);
        atm.setVisible(true);
    }
}

