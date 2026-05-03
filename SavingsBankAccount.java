package com.atmbanksimulator;

// ===== 📚🌐Bank (Domain / Service / Business Logic) =====

// Bank class: a simple implementation of a bank, containing a list of bank accounts
// and has a currently logged-in account (loggedInAccount).
public class Bank {

    // ToDO: Optional extension:
    // Improve account management in the Bank class:
    // Replace Array with ArrayList for managing BankAccount objects.
    // Refactor addBankAccount and login methods to leverage ArrayList.

    // Instance variables storing bank information
    static int maxAccounts = 10;                       // Maximum number of accounts the bank can hold
    private int numAccounts = 0;                        // Current number of accounts in the bank
    static BankAccount[] accounts = new BankAccount[maxAccounts];  // Array to hold BankAccount objects
    public BankAccount loggedInAccount = null;         // Currently logged-in account ('null' if no one is logged in)

    // a method to create new BankAccount - this is known as a 'factory method' and is a more
    // flexible way to do it than just using the 'new' keyword directly.
    public BankAccount makeBankAccount(String accNumber, String accPasswd, int balance) {
        return new BankAccount(accNumber, accPasswd, balance);
    }

    // a method to add a new bank account to the bank - it returns true if it succeeds
    // or false if it fails (because the bank is 'full')
    public boolean addBankAccount(BankAccount a) {
        if (numAccounts < maxAccounts) {
            accounts[numAccounts] = a;
            numAccounts++ ;
            return true;
        } else {
            return false;
        }
    }

    public boolean addSavingsBankAccount(String accNumber, String accPasswd, int balance, int intrestRate){
        return addBankAccount(makeSavingsBankAccount(accNumber,accPasswd,balance,intrestRate));
    }

    public SavingsBankAccount makeSavingsBankAccount(String accNumber, String accPasswd, int balance, int intrestRate){
        return new SavingsBankAccount(accNumber,accPasswd,balance,intrestRate);
    }

    public boolean addPrimeBankAccount(String accNumber, String accPasswd, int balance, int dailyLimit, int overdraftLimit){
        return addBankAccount(makePrimeBankAccount(accNumber,accPasswd,balance,dailyLimit,overdraftLimit));
    }

    public PrimeBankAccount makePrimeBankAccount(String accNumber, String accPasswd, int balance, int dailyLimit, int overdraftLimit){
        return new PrimeBankAccount(accNumber, accPasswd,balance,dailyLimit,overdraftLimit);
    }

    public boolean addStudentBankAccount(String accNumber, String accPasswd, int balance, int dailyLimit){
        return addBankAccount(makeStudentBankAccount(accNumber,accPasswd,balance,dailyLimit));
    }

    public StudentBankAccount makeStudentBankAccount(String accNumber, String accPasswd, int balance, int dailyLimit){
        return new StudentBankAccount(accNumber,accPasswd,balance,dailyLimit);
    }

    // Variant of addBankAccount: creates a BankAccount and adds it in one step.
    // This is an example of method overloading: two methods can share the same name
    // if they have different parameter lists.
    public boolean addBankAccount(String accNumber, String accPasswd, int balance) {
        return addBankAccount(makeBankAccount(accNumber, accPasswd, balance));
    }

    // Check whether the given accountNumber and password match an existing BankAccount.
    // If successful, set 'loggedInAccount' to that account and return true.
    // Otherwise, set 'loggedInAccount' to null and return false.
    public boolean login(String accountNumber, String password) {
        logout(); // logout of any previous loggedInAccount

        // Search the accounts array to find a BankAccount with a matching accountNumber and password.
        // - If found, set 'loggedInAccount' to that account and return true.
        // - If not found, reset 'loggedInAccount' to null and return false.

        //Need to instead read this from a txt or a json file instead (Maybe dump the accounts array on startup)
        for (BankAccount b: accounts) {
            if(b == null){
                break;
            }
            if (b.getAccNumber().equals(accountNumber) && b.getaccPasswd().equals(password)) {
                // found the right account
                loggedInAccount = b;
                return true;
            }
        }
        // not found - return false
        loggedInAccount = null;
        return false;
    }

    // Log out of the currently logged-in account, if any
    public void logout() {
        if (loggedIn()) {
            loggedInAccount = null;
        }
    }

    // Check whether the bank currently has a logged-in account
    public boolean loggedIn() {
        if (loggedInAccount == null) {
            return false;
        } else {
            return true;
        }
    }

    // Attempt to deposit money into the currently logged-in account
    // by calling the deposit method of the BankAccount object
    public boolean deposit(int amount)
    {
        if (loggedIn()) {
            return loggedInAccount.deposit(amount);
        } else {
            return false;
        }
    }


    // Attempt to withdraw money from the currently logged-in account
    // by calling the withdraw method of the BankAccount object
    public boolean withdraw(int amount)
    {
        if (loggedIn()) {
            return loggedInAccount.withdraw(amount);
        } else {
            return false;
        }
    }

    // get the currently logged-in account balance
    // by calling the getBalance method of the BankAccount object
    public int getBalance()
    {
        if (loggedIn()) {
            return loggedInAccount.getBalance();
        } else {
            return -1; // use -1 as an indicator of an error
        }
    }

    public void changePasswd(String oldPasswd,String newPasswd,String user){
        loggedInAccount.switchPasswd(newPasswd);
    }

    public void showAccounts(){
        //System.out.print(accounts[2].getAccNumber());
    }

    public static boolean checkDupesAccNum(String accNum){
        for(BankAccount account : accounts){
            if(account == null){
                break;
            }
            if(account.getAccNumber().equals(accNum)){
                return false;
            }
            System.out.print(account.getAccNumber());
            System.out.print(" ");
            System.out.print(accNum);
            System.out.println("");
        }
        return true;
    }
}