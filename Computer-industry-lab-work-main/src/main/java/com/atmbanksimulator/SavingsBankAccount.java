package com.atmbanksimulator;

public class SavingsBankAccount extends BankAccount{

    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;
    private int intrestRate = 0;

    private String accountType = "Savings";

    public SavingsBankAccount() {}
    public SavingsBankAccount(String a, String p, int b, int c) {
        accNumber = a;
        accPasswd = p;
        balance = b;
        intrestRate = c;
    }

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

    @Override
    public void switchPasswd(String passwd){
        accPasswd = passwd;
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

    public int getIntrestRate(){
        return intrestRate;
    }
    @Override
    public boolean transferTo(BankAccount target, int amount) {

        // 1. check invalid amount
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return false;
        }

        // 2. check enough balance
        if (this.balance < amount) {
            System.out.println("Not enough money");
            return false;
        }

        // 3. prevent sending to same account
        if (this == target) {
            System.out.println("Cannot transfer to same account");
            return false;
        }

        // 4. move money
        this.withdraw(amount);
        target.deposit(amount);

        System.out.println("Transfer successful");
        return true;
    }
}
