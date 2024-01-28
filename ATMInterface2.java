import java.util.Scanner;

class BankAccount{
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

public class ATMInterface {
    private BankAccount account;
    private Scanner scanner;

    public ATMInterface(BankAccount1 account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }
    private boolean verifyPIN(int enteredPin) {
        return enteredPin == account.getPin();
    }
    public void displayMenu() {
    	boolean validPIN = false;
    	
        while (!validPIN) {
    	
        System.out.println("Please enter your PIN:");
        int enteredPin = scanner.nextInt();
        if (verifyPIN(enteredPin)) {
            validPIN = true;
            System.out.println("PIN verified. What would you like to do?");
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
        }
    
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performAction(int choice) {
    
        switch (choice) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                if (account.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient balance. Withdrawal failed.");
                }
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposit successful. New balance: " + account.getBalance());
                break;
            case 3:
                System.out.println("Current balance: " + account.getBalance());
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
    

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0, 1234); // Change 1234 to your desired PIN
        ATMInterface atm = new ATMInterface(userAccount);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");
        boolean exit = false;
        while (!exit) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            atm.performAction(choice);

            atm.performAction(choice);
        }
    }
}
