package com.atmbanksimulator;

import java.lang.invoke.LambdaMetafactory;

public class StudentBankAccount extends BankAccount{
    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;
    private int dailyWithdrawLimit = 0;

    private String accountType = "Student";

    public StudentBankAccount(){};
    public StudentBankAccount(String a, String b, int c, int d){
        accNumber = a;
        accPasswd = b;
        balance = c;
        dailyWithdrawLimit = d;
    }

    public boolean triedToWithdrawOverLimit = false;

    @Override
    public void switchPasswd(String passwd){
        accPasswd = passwd;
    }

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

    @Override
    public boolean withdraw( int amount ) {
        if (amount < 0 || balance < amount) {
            return false;
        }
        else if(amount > dailyWithdrawLimit){
            triedToWithdrawOverLimit = true;
            return false;
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

;    ;}
