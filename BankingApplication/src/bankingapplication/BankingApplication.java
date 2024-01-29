
package bankingapplication;
import java.util.*;
/**
 *
 * @author Rifat
 */
class BankAccount {
    String name;
    private String accountNumber;
    String accountType;
    private String creationDate;
    private double balance;
    Scanner scanner = new Scanner(System.in);

    public BankAccount(String name, String accountNumber, String accountType, Double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.creationDate = java.time.LocalDateTime.now().toString();
        this.balance = balance;
    }

    public void displayAccountInfo() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Account Holder: " + name);
        System.out.println("Account Type: " + accountType);
        System.out.println("Creation Date: " + creationDate);
        System.out.println("Balance: $" + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    public void withdraw() {
        Map<String, Double> minKeep = new HashMap<>();//Store minimum balance to keep, before withdraing
        
        minKeep.put("Current", 200.0);
        minKeep.put("Savings", 150.0);
        minKeep.put("Salary", 100.0);
        
        
        
        Double keep = minKeep.get(accountType);
        Double maxWithdraw = balance - keep;
        
        System.out.print("Your current balance is $" + balance + "\nYou have to keep minimum $" + keep + " balance before withdrawing\nYou can withdraw maximum $" + maxWithdraw + '\n');
        
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = Double.parseDouble(scanner.nextLine());
        
        
        if(balance - withdrawAmount < keep){
            System.out.print("Insufficent funds.\n");
        }
        
        else{
            balance -= withdrawAmount;
            System.out.println("Withdrawn $" + withdrawAmount + ". New balance: $" + balance + '\n');
        }
        
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountType()
    {
        return accountType;
    }
    
    
}

class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();//store individual accounts
    
    
    public void createAccount() {
        
        Map<String, Double> minAmount = new HashMap<>();//store minimum required balance to open an account
        
        minAmount.put("Current", 1000.0);
        minAmount.put("Savings", 1500.0);
        minAmount.put("Salary", 500.0);
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Choose account type from the list below : " + '\n');
        System.out.print("1. Current" + '\n');
        System.out.print("2. Savings" + '\n');
        System.out.print("3. Salary" + '\n');
        System.out.print("Enter account type : " + '\n');
        String accountType = scanner.nextLine();
        
        
        boolean flag = true;
        
         
         Double minimum_amount = minAmount.get(accountType);
        
         System.out.print("To create a " + accountType + " account, you need to deposit at least $" + minimum_amount + '\n');
            
         System.out.print("Enter amount to deposit : ");
         Double amount = scanner.nextDouble();
            
         if(amount < minimum_amount){
             
            System.out.print("Sorry!!! To create a " + accountType + " account, you need to deposit at least $" + minimum_amount + '\n');
            
         }
         else{
             BankAccount account = new BankAccount(name, accountNumber, accountType, amount);
             accounts.add(account);
             System.out.println("Account created successfully.");
         }
            
        
        
        
    }

    public void displayAllAccounts() {
        for(BankAccount account : accounts) {
            account.displayAccountInfo();
        }
    }

    public void updateAccount(String accountNumber) {
        for(BankAccount account : accounts) {
            if(account.getAccountNumber().equals(accountNumber)){
                Scanner scanner = new Scanner(System.in);
                
                System.out.print("Enter new account holder's name: ");
                String newName = scanner.nextLine();
                
                System.out.print("Choose a new account type from the list below : " + '\n');
                System.out.print("1. Current" + '\n');
                System.out.print("2. Savings" + '\n');
                System.out.print("3. Salary" + '\n');
                System.out.print("Enter a new account type : " + '\n');
                String newAccountType = scanner.nextLine();
                
                account.name = newName;
                account.accountType = newAccountType;
                
                System.out.println("Account updated successfully.");
                
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void deleteAccount(String accountNumber) {
        for(BankAccount account : accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                accounts.remove(account);
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void searchAccount(String accountNumber) {
        for(BankAccount account : accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                account.displayAccountInfo();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}

public class BankingApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            
            
            System.out.println("\nMenu:");
            System.out.println("1. Create a new account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Update an account");
            System.out.println("4. Delete an account");
            System.out.println("5. Deposit an amount into your account");
            System.out.println("6. Withdraw an amount from your account");
            System.out.println("7. Search for account");
            System.out.println("8. Exit");

            System.out.print("Enter your choice : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    bank.createAccount();
                    break;
                    
                case "2":
                    bank.displayAllAccounts();
                    break;
                    
                case "3":
                    System.out.print("Enter the account number to update: ");
                    String accountNumberToUpdate = scanner.nextLine();
                    bank.updateAccount(accountNumberToUpdate);
                    break;
                    
                case "4":
                    System.out.print("Enter the account number to delete: ");
                    String accountNumberToDelete = scanner.nextLine();
                    bank.deleteAccount(accountNumberToDelete);
                    break;
                    
                case "5":
                    System.out.print("Enter the account number to deposit: ");
                    String accountNumberToDeposit = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    
                    for(BankAccount account : bank.getAccounts()){
                        if(account.getAccountNumber().equals(accountNumberToDeposit)){
                            account.deposit(depositAmount);
                            break;
                        }
                    }
                    break;
                    
                case "6":
                    System.out.print("Enter account number for withdrawal: ");
                    String accountNumberToWithdraw = scanner.nextLine();
                    
                    for(BankAccount account : bank.getAccounts()){
                        if(account.getAccountNumber().equals(accountNumberToWithdraw)){
                            account.withdraw();
                            break;
                        }
                    }
                    break;
                    
                case "7":
                    System.out.print("Enter the account number to search: ");
                    String accountNumberToSearch = scanner.nextLine();
                    bank.searchAccount(accountNumberToSearch);
                    break;
                    
                case "8":
                    System.out.println("Thanks for using our application,Goodbye!");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8.");
            }
        }
    }
    
}
