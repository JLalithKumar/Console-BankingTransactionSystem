import dao.AccountDAO;
import service.BankService;
import service.CustomerService;
import db.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerService customerService = new CustomerService();
        AccountDAO accountDAO = new AccountDAO();
        BankService bankService = new BankService();

        while (true) {

            System.out.println("\n=== Banking Transaction System ===");
            System.out.println("1. Create Customer");
            System.out.println("2. Open Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try (Connection con = DBConnection.getConnection()) {

                switch (choice) {

                    case 1 -> {
                        System.out.print("Name: ");
                        sc.nextLine();
                        String name = sc.nextLine();

                        System.out.print("Phone: ");
                        String phone = sc.next();

                        System.out.print("Email: ");
                        String email = sc.next();

                        int custId = customerService.createCustomer(name, phone, email);

                        if (custId != -1) {
                            System.out.println("Customer created with ID: " + custId);
                        }
                    }

                    case 2 -> {
                        System.out.print("Customer ID: ");
                        int custId = sc.nextInt();

                        System.out.print("Initial Balance: ");
                        double bal = sc.nextDouble();

                        String accNo = accountDAO.openAccount(custId, bal);
                        System.out.println("Account opened. Account Number: " + accNo);
                    }

                    case 3 -> {
                        System.out.print("Account Number: ");
                        String acc = sc.next();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();

                        boolean ok = accountDAO.deposit(acc, amt, con);
                        System.out.println(ok ? "Deposit successful" : "Deposit failed");
                    }

                    case 4 -> {
                        System.out.print("Account Number: ");
                        String acc = sc.next();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();

                        boolean ok = accountDAO.withdraw(acc, amt, con);
                        System.out.println(ok ? "Withdrawal successful" : "Insufficient balance");
                    }

                    case 5 -> {
                        System.out.print("From Account Number: ");
                        String from = sc.next();

                        System.out.print("To Account Number: ");
                        String to = sc.next();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();

                        bankService.transferMoney(from, to, amt);
                    }

                    case 6 -> {
                        System.out.print("Account Number: ");
                        String acc = sc.next();

                        double bal = accountDAO.getBalance(acc, con);
                        System.out.println("Current Balance: " + bal);
                    }

                    case 7 -> {
                        System.out.println("Thank you. Exiting...");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
