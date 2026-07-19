import java.util.ArrayList;
public class Account {
    private String accountNumber;
    private String name;
    private String password;
    private double balance;

    public Account(String accountNumber,String name, String password, double balance){
        this.accountNumber = accountNumber;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public double getBalance(){
        return balance;
    }

    private ArrayList<Transaction> transactions =new ArrayList<>();

    public void deposit(double amount){
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
            return true;
        }
        return false;
    }

    public void showTransactions(){
        System.out.println("\nTransaction History");

        for(Transaction t: transactions){
            System.out.println(t);
        }
    }

    public void showDetails() {
        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Name : " + name);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + balance);
    }

    public boolean changePassword(String oldPass, String newPass) {
        if(password.equals(oldPass)) {
            password = newPass;
            return true;
        }
        return false;
    }
}
