import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User {
    private String userId;
    private String pin;
    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }
    public String getUserId() {
        return userId;
    }
    public String getPin() {
        return pin;
    }
}
class Account {
    private double balance;
    public Account(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds!");
            return false;
        }
    }
    public void transfer(Account destinationAccount, double amount) {
        if (withdraw(amount)) {
            destinationAccount.deposit(amount);
        }
    }
}
class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description + ": $" + amount;
    }
}
class ATM {
    private User currentUser;
    private Account currentAccount;
    private List<Transaction> transactionHistory;

    public ATM(User user, Account account) {
        this.currentUser = user;
        this.currentAccount = account;
        this.transactionHistory = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (currentUser.getUserId().equals(userId) && currentUser.getPin().equals(pin)) {
            System.out.println("Authentication successful. Welcome!");
            displayMenu(scanner);
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }
    private void displayMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    performWithdraw(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }
    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
    private void performWithdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (currentAccount.withdraw(amount)) {
            Transaction withdrawal = new Transaction("Withdraw", amount);
            transactionHistory.add(withdrawal);
            System.out.println("Withdrawal successful. Remaining balance: $" + currentAccount.getBalance());
        }
    }
    private void performDeposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
        Transaction deposit = new Transaction("Deposit", amount);
        transactionHistory.add(deposit);
        System.out.println("Deposit successful. New balance: $" + currentAccount.getBalance());
    }
    private void performTransfer(Scanner scanner) {
        System.out.print("Enter the destination user ID: ");
        String destinationUserId = scanner.next();
        Account destinationAccount = new Account(0);

        System.out.print("Enter the amount to transfer: $");
        double amount = scanner.nextDouble();
        currentAccount.transfer(destinationAccount, amount);
        Transaction transfer = new Transaction("Transfer to " + destinationUserId, amount);
        transactionHistory.add(transfer);
        System.out.println("Transfer successful. Remaining balance: $" + currentAccount.getBalance());
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        User user = new User("ss1234ss", "1234");
        Account account = new Account(1000.0);
        ATM atm = new ATM(user, account);
        atm.start();
    }
}
