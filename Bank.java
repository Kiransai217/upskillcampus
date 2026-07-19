import java.util.ArrayList;
import java.io.*;
public class Bank {
    private ArrayList<Account> accounts =new ArrayList<>();

    public void register(Account account){
        accounts.add(account);
    }

    public Account login(String accNo, String password){
        for(Account acc: accounts){
            if(acc.getAccountNumber().equals(accNo) && acc.getPassword().equals(password)){
                return acc;
            }
        }
        return null;
    }
    public Account findAccount(String accNo){
        for(Account acc: accounts){
            if(acc.getAccountNumber().equals(accNo)){
                return acc;
            }
        }
        return null;
    }

    public boolean transferMoney(String fromAcc, String toAcc, double amount){
        Account sender = findAccount(fromAcc);
        Account receiver = findAccount(toAcc);

        if(sender == null|| receiver == null){
            return false;
        }

        if(sender.withdraw(amount)){
            receiver.deposit(amount);
            return true;
        }
        return false;
    }

    public void saveAccounts() {
    try {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("accounts.txt"));

        for(Account acc : accounts) {
            bw.write(
                    acc.getAccountNumber() + "," +
                    acc.getName() + "," +
                    acc.getPassword() + "," +
                    acc.getBalance()
            );
            bw.newLine();
        }

        bw.close();
    } catch(Exception e) {
        System.out.println("Error Saving Accounts");
    }
}

public void loadAccounts() {
    try {
        File file = new File("accounts.txt");

        if(!file.exists()) {
            return;
        }

        BufferedReader br = new BufferedReader(
                new FileReader(file));

        String line;

        while((line = br.readLine()) != null) {

            String[] data = line.split(",");

            String accNo = data[0];
            String name = data[1];
            String password = data[2];
            double balance =
                    Double.parseDouble(data[3]);

            accounts.add(
                    new Account(
                            accNo,
                            name,
                            password,
                            balance
                    )
            );
        }

        br.close();

    } catch(Exception e) {
        System.out.println("Error Loading Accounts");
    }
}

}
