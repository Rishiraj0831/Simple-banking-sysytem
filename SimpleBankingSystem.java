 import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public void setAccountHolder(String name) { this.accountHolder = name; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "ID: " + accountNumber + " | Name: " + accountHolder + " | Balance: $" + balance;
    }
}

public class SimpleBankingSystem {
    private static HashMap<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account (Insert)");
            System.out.println("2. Update Account Name");
            System.out.println("3. Delete Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. View All Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: createAccount(); break;
                case 2: updateAccount(); break;
                case 3: deleteAccount(); break;
                case 4: handleTransaction(true); break;
                case 5: handleTransaction(false); break;
                case 6: viewAccounts(); break;
                case 7: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Initial deposit: ");
        double deposit = scanner.nextDouble();
        
        accounts.put(id, new BankAccount(id, name, deposit));
        System.out.println("Account created successfully!");
    }

    private static void updateAccount() {
        System.out.print("Enter account number to update: ");
        String id = scanner.nextLine();
        if (accounts.containsKey(id)) {
            System.out.print("Enter new name: ");
            accounts.get(id).setAccountHolder(scanner.nextLine());
            System.out.println("Account updated!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter account number to delete: ");
        String id = scanner.nextLine();
        if (accounts.remove(id) != null) {
            System.out.println("Account closed.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void handleTransaction(boolean isDeposit) {
        System.out.print("Enter account number: ");
        String id = scanner.nextLine();
        BankAccount acc = accounts.get(id);
        if (acc != null) {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            if (isDeposit) acc.deposit(amount);
            else acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccounts() {
        if (accounts.isEmpty()) System.out.println("No accounts in the system.");
        else accounts.values().forEach(System.out::println);
    }

    public static HashMap<String, BankAccount> getAccounts() {
        return accounts;
    }

    public static void setAccounts(HashMap<String, BankAccount> accounts) {
        SimpleBankingSystem.accounts = accounts;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        SimpleBankingSystem.scanner = scanner;
    }
} 
