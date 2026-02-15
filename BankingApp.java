package bank.main;

import java.util.Scanner;

import bank.exceptions.AccountNotFoundException;
import bank.exceptions.BankingException;
import bank.model.Account;
import bank.service.BankingService;

public class BankingApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        BankingService service = new BankingService();

        while (true) {

            System.out.println("\n====== ONLINE BANKING SYSTEM ======");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {

                switch (choice) {

                case 1:
                    System.out.print("Enter Account No: ");
                    long accNo = sc.nextLong();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    System.out.print("Enter Password: ");
                    String password = sc.next();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    Account account =
                            new Account(accNo, name, password, balance);
      //              try {
                    service.createAccount(account);

                    System.out.println("✅ Account Created Successfully");
                    break; 
                  //  }catch(Exception e) {
                  //  	System.out.println("Account Creation failure");
                  //  }


                case 2:
                    System.out.print("Enter Account No: ");
                    long viewAcc = sc.nextLong();

                    Account acc =
                            service.login(viewAcc);

                    System.out.println("\n--- Account Details ---");
                    System.out.println("Account No : " + acc.getAccountNo());
                    System.out.println("Name       : " + acc.getName());
                    System.out.println("Balance    : " + acc.getBalance());
                    break;

                case 3:
                    System.out.print("Enter Account No: ");
                    long depAcc = sc.nextLong();

                    System.out.print("Enter Amount: ");
                    double depAmt = sc.nextDouble();

                    service.deposit(depAcc, depAmt);

                    System.out.println("✅ Deposit Successful");
                    break;

                case 4:
                    System.out.print("Enter Account No: ");
                    long witAcc = sc.nextLong();

                    System.out.print("Enter Amount: ");
                    double witAmt = sc.nextDouble();

                    service.withdraw(witAcc, witAmt);

                    System.out.println("✅ Withdraw Successful");
                    break;

                case 5:
                    System.out.println("Thank You for Using Banking System");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
                }

            } catch (AccountNotFoundException e) {
                System.out.println("❌ Error: " + e.getMessage());
            } catch (BankingException e) {
                System.out.println("❌ Banking Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

	}

}
