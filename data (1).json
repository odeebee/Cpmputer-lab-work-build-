package com.atmbanksimulator;

import java.lang.invoke.LambdaMetafactory;

//Prime bank accounts have overdraft limits and higher withdraw limits
public class PrimeBankAccount extends BankAccount {
    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;
    private int dailyWithdrawLimit = 0;
    private int overDraftLimit = 0;
    private int overdraftAvailable = 0;

    private String accountType = "Prime";

    public PrimeBankAccount(){};
    public PrimeBankAccount(String a, String b, int c, int d, int e){
        accNumber = a;
        accPasswd = b;
        balance = c;
        dailyWithdrawLimit = d;
        overDraftLimit = e;
        overdraftAvailable = e;
    }

    public boolean triedToWithdrawOverLimit = false;

    @Override
    public String getAccNumber(){return accNumber;};

    @Override
    public String getaccPasswd(){return accPasswd;};

    @Override
    public int getBalance(){return balance;};

    @Override
    public String getAccType(){return accountType;}

    public int getWithdrawLimit(){return dailyWithdrawLimit;}

    public boolean getWithdrawBool(){return triedToWithdrawOverLimit;}

    public int getRemainingOverdraft(){return overdraftAvailable;}

    @Override
    public void switchPasswd(String passwd){
        accPasswd = passwd;
    }

    @Override
    public boolean withdraw( int amount ) {
        if (amount < 0 || balance + overdraftAvailable < amount) {
            return false;
        }
        else if(amount > dailyWithdrawLimit){
            triedToWithdrawOverLimit = true;
            return false;
        }
        if(amount <= 0 || balance + overdraftAvailable > amount){
            balance = balance - amount;
            overdraftAvailable = overdraftAvailable - amount;
            return true;
        }else {
            balance = balance - amount;  // subtract amount from balance
            return true;
        }
    }

    @Override
    public boolean deposit( int amount ) {
        if (amount < 0) {
            return false;
        } else {
            balance = balance + amount;  // add amount to balance
            return true;
        }
    }
}
