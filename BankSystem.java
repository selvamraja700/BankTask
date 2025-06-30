import java.util.*;

public class BankSystem {

    private Map<String, Account> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n====== Welcome to Simple Bank System ======");
            System.out.println("1. Create Account");
            System.out.println("2. Login to Account");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: {
                    System.out.println("Create account");
                    createAccount();
                    break;
                }
                case 2: {
                    System.out.println("Login");
                    login();
                    break;
                }
                case 3: {
                    System.out.println("Exiting Goodbye..");
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }
    }

    public void createAccount() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Set Password: ");
        String password = scanner.nextLine();
        String accNumber = "Ac" + new Random().nextInt(10000);
        Account newAcc = new Account(accNumber, name, password);
        accounts.put(accNumber, newAcc);
        System.out.println("Account created successfully! Your Account No is: " + accNumber);
    }

    public void login() {
        System.out.print("Enter your account number: ");
        String accNum = scanner.nextLine();

        Account acc = accounts.get(accNum);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if (acc.authenticate(pass)) {
            System.out.println("Welcome " + acc.getName() + "  ");
            accountMenu(acc);
        } else {
            System.out.println("Incorrect Password");
        }
    }

    private void accountMenu(Account acc) {
        while (true) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Check");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Enter amount to deposit: ");
                    double amt = scanner.nextDouble();
                    scanner.nextLine();
                    acc.deposit(amt);
                    break;
                }
                case 2: {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = scanner.nextDouble();
                    scanner.nextLine();
                    acc.withdraw(amt);
                    break;
                }
                case 3: {
                    System.out.println("Current Balance: " + acc.getBalance());
                    break;
                }
                case 4: {
                    acc.showTransactionHistory();
                    break;
                }
                case 5: {
                    System.out.println("Logged out");
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }
    }
}
