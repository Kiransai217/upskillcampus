import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        Bank bank = new Bank();
        bank.loadAccounts();

        while(true){
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name:");
                    String name = sc.nextLine();

                    System.out.print("Create Password:");
                    String password = sc.nextLine();

                    System.out.print("Initial Deposit: ");
                    double balance = sc.nextDouble();

                    String accNo = "ACC" + (1000 + bank.hashCode()%1000 + (int)(Math.random()*1000));

                    Account account = new Account(accNo, name, password, balance);

                    bank.register(account);
                    bank.saveAccounts();

                    System.out.println("Account Created Successfully");
                    System.out.println("Account Number: " + accNo);

                    break;
                case 2:
                    sc.nextLine();

                    System.out.print("Account Number: ");
                    String loginAcc = sc.nextLine();

                    System.out.print("Password: ");
                    String loginPass = sc.nextLine();

                    Account user = bank.login(loginAcc, loginPass);

                    if(user != null){
                        System.out.println("\nLogin Successful");

                        boolean loggedIn = true;

                        while (loggedIn) {
                            System.out.println("\n1.Deposit");
                            System.out.println("2.Withdraw");
                            System.out.println("3.Check Balance");
                            System.out.println("4.Transaction History");
                            System.out.println("5.Transfer Funds");
                            System.out.println("6.Account Details");
                            System.out.println("7.Change Password");
                            System.out.println("8.Logout");

                            int option = sc.nextInt();

                            switch (option) {
                                case 1:
                                    System.out.print("Amount: ");
                                    double dep = sc.nextDouble();

                                    user.deposit(dep);
                                    bank.saveAccounts();

                                    System.out.println("Deposit Successful");

                                    break;
                                case 2:

                                    System.out.print("Amount: ");
                                    double wd = sc.nextDouble();

                                    if(user.withdraw(wd)){
                                        bank.saveAccounts();
                                        System.out.println("Withdrawal Successful");
                                    }                          
                                    else
                                        System.out.println("Insufficient Balance");

                                    break;
                                case 3:

                                    System.out.println("Balance = "+ user.getBalance());

                                    break;
                                case 4:

                                    user.showTransactions();

                                    break;
                                case 5:
                                    sc.nextLine();

                                    System.out.print("Receiver Account Number: ");
                                    String receiver = sc.nextLine();

                                    System.out.print("Amount: ");
                                    double amount = sc.nextDouble();

                                    boolean result = bank.transferMoney(user.getAccountNumber(),receiver,amount);

                                    if(result){
                                        bank.saveAccounts();
                                        System.out.println("Transfer Successful");
                                    }
                                    else
                                        System.out.println("Transfer Failed");
                                    break;
                                case 6:
                                    user.showDetails();
                                    break;
                                case 7:
                                    sc.nextLine();
                                    
                                    System.out.print("Enter Old Password: ");
                                    String oldPass = sc.nextLine();

                                    System.out.print("Enter New Password: ");
                                    String newPass = sc.nextLine();

                                    if(user.changePassword(oldPass, newPass)) {
                                        bank.saveAccounts();
                                        System.out.println("Password Changed Successfully");
                                    }
                                    else
                                         System.out.println("Incorrect Old Password");
                                    break;
                                case 8:

                                    loggedIn = false;
                                    break;
                            }
                        }
                    }else{
                        System.out.println("Invalid Account or Password");
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
