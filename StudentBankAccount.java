package com.atmbanksimulator;

// ===== 📚🌐BankAccount (Domain / Service / Business Logic) =====

// BankAccount class:
// - Stores instance variables for account number, password, and balance
// - Provides methods to withdraw, deposit, check balance, etc.
public class BankAccount {
    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;

    private int loginAttempts = 0;
    private int maxLoginAttemps = 3;

    //No constructor needed.
    private String accountType = "Standard";

    public BankAccount() {}
    public BankAccount(String a, String p, int b) {
        accNumber = a;
        accPasswd = p;
        balance = b;
    }

    public void switchPasswd(String passwd){
        accPasswd = passwd;
    }

    // Withdraw money from this account.
    // Returns true if successful, or false if the amount is negative or exceeds the current balance.
    public boolean withdraw( int amount ) {
        if (amount < 0 || balance < amount) {
            return false;
        } else {
            balance = balance - amount;  // subtract amount from balance
            return true;
        }
    }

    // deposit the amount of money into this account.
    // Return true if successful,or false if the amount is negative
    public boolean deposit( int amount ) {
        if (amount < 0) {
            return false;
        } else {
            balance = balance + amount;  // add amount to balance
            return true;
        }
    }

    // Getter for the account balance
    // Returns the current balance of this account
    public int getBalance() {
        return balance;
    }

    // Getter for the account number
    public String getAccNumber() {
        return accNumber;
    }
    // Getter for the account password
    public String getaccPasswd() {
        return accPasswd;
    }

    public String getAccType(){return accountType;}

    public int getLoginAttempts(){return loginAttempts;}
    public int getMaxLoginAttemps(){return maxLoginAttemps;}
    public void addLoginAttempts(){
        System.out.println("Adding to login attempts");
        loginAttempts = loginAttempts + 1;
        System.out.println(loginAttempts);
    }

}
