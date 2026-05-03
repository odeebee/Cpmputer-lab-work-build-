package com.atmbanksimulator;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// 🧍Think of MVC like a human body:
// - View is the face and senses: it shows things and receives input.
// - Controller is the nerves: it carries signals to the brain and triggers actions.
// - UIModel is the brain: it holds state and logic, and queries domain services.
// - Bank / BankAccount are the real "money world" rules.
// Together, they simulate how an ATM thinks, reacts, and handles money.

public class Main extends Application {

    public static void main( String args[] ) {launch(args);}

    public void start(Stage window) throws IOException {

        // Create a Bank object add two bank accounts for test
        Bank bank = new Bank();
//        bank.addBankAccount("10001", "11111", 100);
//        bank.addBankAccount("10002", "22222", 50);
//        bank.addStudentBankAccount("20002","11111",300,200);
//        bank.addPrimeBankAccount("90009","12345",1000,20000,500);
//        bank.addSavingsBankAccount("12345","12345",500,4);

        bank.showAccounts();

        //UIModel-View-Controller structure setup
        // Create the UIModel, View and Controller objects and link them together
        UIModel UIModel = new UIModel(bank);   // the UIModel needs the Bank object to 'talk to' the bank
        View  view  = new View();
        Controller controller  = new Controller();

        // Link them together so they can talk to each other
        view.controller = controller;
        controller.UIModel = UIModel;
        UIModel.view = view;

        // start up the GUI (view), and then tell the UIModel to initialise itself
        view.start(window);
        UIModel.initialise();

//        List<String> lines = Files.readAllLines(Paths.get("/home/odb/Downloads/Computer-industry-lab-work-main(1)/Computer-industry-lab-work-main/test.txt"));
//        for (String line : lines) {
//            System.out.println(line);
//        }

        localFileToAccounts(bank);

        System.out.println(Bank.accounts[2]);
    }

    public void localFileToAccounts(Bank b) throws IOException {
        System.out.println(" ");
        //Read each line of local file then convert to account type then append to array. Call when application launched.
//        List<String> lines = Files.readAllLines(Paths.get("test.txt"));
//        String words[] = lines.get(0).split(" ");
//        b.addBankAccount(words[1],words[2],Integer.parseInt(words[3]));
        //Do this but iterative
        Path path = Paths.get("test.txt");
        List<String> lines = Files.readAllLines(path);
        for(String line : lines){
            String words[] = line.split(" ");
            switch (words[0]){
                case("Standard"):
                    b.addBankAccount(words[1],words[2],Integer.parseInt(words[3]));
                    break;
                case("Student"):
                    b.addStudentBankAccount(words[1],words[2],Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    break;
                case("Prime"):
                    b.addPrimeBankAccount(words[1],words[2],Integer.parseInt(words[3]),Integer.parseInt(words[4]),Integer.parseInt(words[5]));
                    break;
                case("Savings"):
                    b.addSavingsBankAccount(words[1],words[2],Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    break;
            }
        }

    }

    public static void updateLocalFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        for(BankAccount account : Bank.accounts){
            if(account == null){
                break;
            }
            String temp = "";
            //String temp = account.getAccNumber() + " " + account.getaccPasswd() + " " + account.getAccType();
            switch (account.getAccType()){
                //Standard, Student, Prime, Savings
                case("Standard"):
                    temp = account.getAccType() + " " + account.getAccNumber() + " " + account.getaccPasswd() + " " + account.getBalance();
                    writer.write(temp);
                    writer.newLine();
                    break;
                case("Student"):
                    if(account instanceof StudentBankAccount){
                        StudentBankAccount s = (StudentBankAccount) account;
                        temp = s.getAccType() + " " + s.getAccNumber() + " " + s.getaccPasswd() + " " + s.getBalance() + " " + s.getWithdrawLimit();
                        writer.write(temp);
                        writer.newLine();
                    }
                    break;
                case("Prime"):
                    if(account instanceof PrimeBankAccount){
                        PrimeBankAccount p = (PrimeBankAccount) account;
                        temp = p.getAccType() + " " + p.getAccNumber() + " " + p.getaccPasswd() + " " + p.getBalance() + " " + p.getWithdrawLimit() + " " + p.getRemainingOverdraft();
                        writer.write(temp);
                        writer.newLine();
                    }
                    break;
                case("Savings"):
                    if(account instanceof SavingsBankAccount){
                        SavingsBankAccount s = (SavingsBankAccount) account;
                        temp = s.getAccType() + " " + s.getAccNumber() + " " + s.getaccPasswd() + " " + s.getBalance() + " " + s.getIntrestRate();
                        writer.write(temp);
                        writer.newLine();
                    }
            }
        }
        writer.flush();
        writer.close();
    }
}
