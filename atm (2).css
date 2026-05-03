package com.atmbanksimulator;

// ===== 🧠 UIModel (Brain) =====

import static java.lang.Integer.parseInt;

// The UIModel represents all the actual content and functionality of the app
// For the ATM, it keeps track of the information shown in the display
// (the laMsg and two tfInput boxes), and the interaction with the bank, executes
// commands provided by the controller and tells the view to update when
// something changes
public class UIModel {
    View view; // Reference to the View (part of the MVC setup)
    private Bank bank; // The ATM communicates with this Bank

    // The ATM UIModel can be in one of three states:
    // 1. Waiting for an account number
    // 2. Waiting for a password
    // 3. Logged in (ready to process requests for the logged-in account)
    // We represent each state with a String constant.
    // The 'final' keyword ensures these values cannot be changed.tempAccount
    private String accType = "";
    private String[] tempAccount = new String[5]; //Array to hold temporary details for account creation

    private final String STATE_ACCOUNT_NO = "account_no";
    private final String STATE_PASSWORD = "password";
    private final String STATE_LOGGED_IN = "logged_in";
    private final String STATE_CHANGE_PASSWD = "change_passwd";
    private final String STATE_CHANGE_NEW_PASSWD = "change_new_passwd";
    private final String STATE_NEW_ACCOUNT = "create_new_account";
    private final String STATE_NEW_ACCOUNT_NO = "create_new_account_number";
    private final String STATE_NEW_ACCOUNT_PAS = "create_new_account_password";
    private final String STATE_NEW_ACCOUNT_TYP = "create_new_account_type";
    private final String STATE_NEW_ACCOUNT_BAL = "create_new_account_balance";
    private final String STATE_NEW_ACCOUNT_INT = "create_new_account_intrest";
    private final String STATE_NEW_ACCOUNT_LMT = "create_new_account_withdrawl_limit";
    private final String STATE_NEW_ACCOUNT_OVR = "create_new_account_overdraft_limit";

    // Variables representing the state and data of the ATM UIModel
    private String state = STATE_ACCOUNT_NO;    // Current state of the ATM
    private String accNumber = "";         // Account number being typed
    private String accPasswd = "";         // Password being typed

    // Variables shown on the View display
    private String message;                // Message label text
    private String numberPadInput;         // Current number displayed in the TextField (as a string)
    private String result;                 // Contents of the TextArea (may be multiple lines)

    // UIModel constructor: pass a Bank object that the ATM interacts with
    public UIModel(Bank bank) {
        this.bank = bank;
    }

    // Initialize the ATM UIModel: this method is called by Main when starting the app
    // - Set state to STATE_ACCOUNT_NO
    // - Clear the numberPadInput - numbers displayed in the TextField
    // - Display the welcome message and user instructions
    public void initialise() {
        setState(STATE_ACCOUNT_NO);
        numberPadInput = "";
        message = "Welcome to the ATM";
        result = "Enter your account number\nFollowed by \"Ent\"";
        update();
    }

    // Reset the ATM UIModel after an invalid action or logout:
    // - Set state to STATE_ACCOUNT_NO
    // - Clear the numberPadInput
    // - Display the provided message and user instructions
    private void reset(String msg) {
        setState(STATE_ACCOUNT_NO);
        numberPadInput = "";
        message = msg;
        result = "Enter your account number\nFollowed by \"Ent\"";
    }

    // Change the ATM state and print a debug message whenever the state changes
    private void setState(String newState)
    {
        if ( !state.equals(newState) )
        {
            String oldState = state;
            state = newState;
            System.out.println("UIModel::setState: changed state from "+ oldState + " to " + newState);
        }
    }

    // These process**** methods are called by the Controller
    // in response to specific button presses on the GUI.

    // Handle a number button press: append the digit to numberPadInput
    public void processNumber(String numberOnButton) {
        // Optional extension:
        // Improve feedback by showing what the number is being entered for based on the current state.
        // e.g.  if state is STATE_ACCOUNT_NO, display "Receiving Account Number, Beep 5 received"
        numberPadInput += numberOnButton;
        message = "Beep! " + numberOnButton + " received";
        update();
    }

    // Handle the Clear button: reset the current number stored in numberPadInput
    public void processClear() {
        // Optional extension:
        // Improve feedback by showing what was cleared depending on the current state.
        // e.g. if state is STATE_ACCOUNT_NO, display "Account Number cleared: 123"
        if (!numberPadInput.isEmpty()) {
            numberPadInput = "";
            message = "Input Cleared";
            update();
        }
    }

    // Handle the Enter button.
    // This is a more complex method: pressing Enter causes the ATM to change state,
    // progressing from STATE_ACCOUNT_NO → STATE_PASSWORD → STATE_LOGGED_IN,
    // and back to STATE_ACCOUNT_NO when logging out.
    public void processEnter()
    {
        // The action depends on the current ATM state
        switch ( state )
        {
            case STATE_NEW_ACCOUNT_OVR:
                if(parseInt(numberPadInput) > 1001){
                    result = "Input is more than your account is allowed\nplease enter another ammount";
                }else{
                    result = "Overdraft limit set and account created.\nPress enter to be redirected\nto the login screen.";
                    tempAccount[4] = numberPadInput;
                    createAccountFinal();
                }
                break;
            case STATE_NEW_ACCOUNT_LMT:
                if(accType.equals("prime")){
                    if(parseInt(numberPadInput) > 5001){
                        result = "This is more than you can have\nwith your current account\nplease enter another ammount";
                        numberPadInput = "";
                    }else{
                        tempAccount[3] = numberPadInput;
                        numberPadInput = "";
                        result = "Withdrawl limit set, please enter your overdraft limit\nMax: 1000";
                        setState(STATE_NEW_ACCOUNT_OVR);
                    }
                } else if (accType.equals("student")) {
                    if(parseInt(numberPadInput) > 501){
                        result = "This is more than you can have\nwith your current account\nplease enter another ammount";
                        numberPadInput="";

                    }else {
                        tempAccount[3] = numberPadInput;
                        result = "Withdrawl limit set.\nPress enter to finish account creation and\nbe redirected to the login screen.";
                        numberPadInput = "";
                        createAccountFinal();
                    }
                }
                break;
            case STATE_NEW_ACCOUNT_INT:
                if(numberPadInput != "" && parseInt(numberPadInput) < 101 ){
                    result = "Intrest rate set.\nPress enter to be redirected to the login screen.";
                    message = "Success!";
                    tempAccount[3] = numberPadInput;
                    numberPadInput = "";
                    message = "Success";
                    createAccountFinal();
                }
                break;
            case STATE_NEW_ACCOUNT_BAL:
                if(numberPadInput != ""){
                    tempAccount[2] = numberPadInput;
                    numberPadInput = "";
                }
                if(accType.equals("standard")){
                    result = "Account created,\npress enter to be redirected\nto the login screen";
                    message = "Success!";
                    createAccountFinal();
                } else if (accType.equals("saving")) {
                    result = "Balance has been set.\nPlease enter your intrest rate:\n1-100%";
                    setState(STATE_NEW_ACCOUNT_INT);
                } else if (accType.equals("prime")) {
                    result = "Balance has been set.\nPlease enter your daily withdrawl limit.\nMax: 5000";
                    setState(STATE_NEW_ACCOUNT_LMT);
                } else if (accType.equals("student")) {
                    result = "Balance has been set.\nPlease enter your daily withdrawl limit.\nMax: 500";
                    setState(STATE_NEW_ACCOUNT_LMT);
                }
                break;
            case STATE_NEW_ACCOUNT_TYP:
                //1 = Standard
                //2 = Saving
                //3 = Prime
                //4 = Student
                if(numberPadInput.equals("1")){
                    result = "Account type set to standard.\nPlease enter the balance for the account.";
                    accType = "standard";
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_BAL);
                } else if (numberPadInput.equals("2")) {
                    result = "Account type set to saving.\nPlease enter the balance for the account.";
                    accType = "saving";
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_BAL);
                } else if (numberPadInput.equals("3")) {
                    result = "Account type set to prime.\nPlease enter the balance for the account.";
                    accType = "prime";
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_BAL);
                } else if (numberPadInput.equals("4")) {
                    result = "Account type set to student.\nPlease enter the balance for the account.";
                    accType = "student";
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_BAL);
                }else{
                    result = "Please press\n1 - Standard, 2 - Saving,\n3 - Prime, 4 - Student.";
                }
                numberPadInput = "";
                break;
            case STATE_NEW_ACCOUNT_PAS:
                message = "Create a new account";
                result = "Account number accepted\nplease enter a 5 digit password\nand then press enter.";
                if(numberPadInput.length() < 5 || numberPadInput.length() > 5){
                    message = "Invalid input";
                    result = "Invalid password\nlength, please make sure\nthe passwords's\nlength is 5.";
                }
                if(numberPadInput.length() == 5){
                    // Password accepted, need to change state, display new data for acc type picking
                    // (Student, saving, prime, standard
                    result = "Password accepted.\nNow select an account type.\nPlease press\n1 - Standard, 2 - Saving,\n3 - Prime, 4 - Student.";
                    tempAccount[1] = numberPadInput;
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_TYP);
                }
                break;
            case STATE_NEW_ACCOUNT:
                message = "Create a new account";
                result = "To create a new account\nplease enter a account number below.";
                setState(STATE_NEW_ACCOUNT_NO);
                break;
            case STATE_NEW_ACCOUNT_NO:
                if(numberPadInput.length() < 5 || numberPadInput.length() > 5){
                    message = "Invalid input";
                    result = "Invalid account number\nlength, please make sure\nthe account number's\nlength is 5.";
                    numberPadInput = "";
                }
                if(numberPadInput.length() == 5 && Bank.checkDupesAccNum(numberPadInput)){
                    message = "Create a new account";
                    result = "Account number accepted\nplease enter a password\nand then press enter.";
                    tempAccount[0] = numberPadInput;
                    numberPadInput = "";
                    setState(STATE_NEW_ACCOUNT_PAS);

                } else if (numberPadInput.length() == 5 && Bank.checkDupesAccNum(numberPadInput) == false) {
                    message = "Invalid account number";
                    result = "Account number invalid\nplease enter another different one.";
                    numberPadInput = "";
                }
                break;
            case STATE_CHANGE_NEW_PASSWD:
                if(numberPadInput.equals("")){
                    message = "Invalid input";
                    reset(message);
                }else{
                    message = "Password changed";
                    result = "Your password has now\nbeen changed.";
                    bank.changePasswd(bank.loggedInAccount.getaccPasswd(),numberPadInput,bank.loggedInAccount.getAccNumber());
                    numberPadInput = "";
                }
                break;
            case STATE_CHANGE_PASSWD:
                if(numberPadInput.equals("")) {
                    message = "Invalid Password";
                    reset(message);
                }else{
                    message = "Password accepted";
                    if(numberPadInput.equals(bank.loggedInAccount.getaccPasswd())){
                        result = "Password matching,\nPlease enter your new password.";
                        numberPadInput = "";
                        setState(STATE_CHANGE_NEW_PASSWD);
                    }
                }
                break;
            case STATE_ACCOUNT_NO:
                // Waiting for a complete account number
                // If nothing was entered, reset with "Invalid Account Number"
                if (numberPadInput.equals("")) {
                    message = "Invalid Account Number";
                    reset(message);
                }
                else{
                    // Save the entered number as accNumber, clear numberPadInput,
                    // update the state to expect password, and provide instructions
                    accNumber = numberPadInput;
                    numberPadInput = "";
                    for(BankAccount b : Bank.accounts){
                        if(b == null){
                            break;
                        }
                        if(b.getAccNumber().equals(accNumber)){
                            if(b.getLoginAttempts() == 3){
                                result = "Account has been locked";
                            }else{
                                setState(STATE_PASSWORD);
                                message = "Account Number Accepted";
                                result = "Now enter your password\nFollowed by \"Ent\"";
                            }
                        }
                    }

                }
                break;

            case STATE_PASSWORD:
                    // Waiting for a password
                    // Save the typed number as accPasswd, clear numberPadInput,
                    // then contact the bank to attempt login
                accPasswd = numberPadInput;
                numberPadInput = "";
                if ( bank.login(accNumber, accPasswd) )
                {
                    // Successful login: change state to STATE_LOGGED_IN and provide instructions
                    setState(STATE_LOGGED_IN);
                    message = "Logged In";
                    result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw)";
                    if(bank.loggedInAccount instanceof StudentBankAccount){
                        StudentBankAccount s = (StudentBankAccount) bank.loggedInAccount;
                        result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw),\nYou are using a student bank account!\nYour daily withdraw limit is: " + s.getWithdrawLimit();
                    } else if (bank.loggedInAccount instanceof  PrimeBankAccount){
                        PrimeBankAccount p = (PrimeBankAccount) bank.loggedInAccount;
                        result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw),\nYou are using a prime bank account!\nYour daily withdraw limit is: " + p.getWithdrawLimit() + "\nYou current have £" + p.getRemainingOverdraft() + "\nof your overdraft remaining!";
                    } else if (bank.loggedInAccount instanceof  SavingsBankAccount) {
                        SavingsBankAccount s = (SavingsBankAccount) bank.loggedInAccount;
                        result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw),\nYou are using a savings bank account!\nYour current intrest rate is!: " + s.getIntrestRate() + "%";
                    }
                } else {
                    // Login failed: reset ATM and display error
                    message = "Login failed: Unknown Account/Password";
                    System.out.println("Searching for account "+accNumber);
                    for(BankAccount b : Bank.accounts){
                        if(b == null){
                            break;
                        }
                        System.out.println(b.getAccNumber());
                        if(b.getAccNumber().equals(accNumber)){
                            System.out.println("IM IN THE ACCOUNT "+accNumber);
                            if(b.getLoginAttempts() != 3){
                                b.addLoginAttempts();
                            }
                            break;
                        }
                    }
                    reset(message);
                }
                break;

            case STATE_LOGGED_IN:
            default:
                // Do nothing for other states (user is already logged in)
        }

        update(); // Refresh the GUI to show messages and input
    }

    /**
     * Parses a string into a valid transaction amount.
     * - If the string is empty, invalid, or consists only of zeros, returns 0.
     * - Otherwise, returns the integer value.
     *
     * Purpose:
     * Helper method for validating user-entered amounts in transactions (Deposit, Withdraw, etc.).
     *
     * Note: If you later add features like Transfer, this method can be reused.
     */
    private int parseValidAmount(String number) {
        if (number.isEmpty()) {
            return 0;
        }
        try {
            return parseInt(number);
        } catch (NumberFormatException e) {
            return 0; // Invalid input -> treated as 0
        }
    }

    // Handle the Balance button:
    // - If the user is logged in, retrieve the current balance and update messages/results accordingly
    // - Otherwise, reset the ATM and display an error message
    public void processBalance() {
        if (state.equals(STATE_LOGGED_IN) ) {
            numberPadInput = "";
            message = "Balance Available";
            result = "Your Balance is: " + bank.getBalance();
        } else {
            reset("You are not logged in");
        }
        update();
    }

    // Handle the Withdraw button:
    // If the user is logged in, attempt to withdraw the amount entered;
    // otherwise, reset the ATM and display an error message.
    // Reads the amount from numberPadInput, validates it, and updates messages/results accordingly.
    public void processWithdraw() {
        if (state.equals(STATE_LOGGED_IN)) {
            int amount = parseValidAmount(numberPadInput);
            if (amount > 0) {
                if(bank.withdraw( amount )){
                    message = "Withdraw Successful";
                    result = "Withdrawn: " + numberPadInput;
                }
                else{
                    message = "Withdraw Failed: Insufficient Funds";
                    result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw)";
                    if(bank.loggedInAccount instanceof StudentBankAccount){
                        StudentBankAccount s = (StudentBankAccount) bank.loggedInAccount;
                        if (s.getWithdrawBool()){
                            message = "Withdraw Failed: Over your withdraw limit!";
                            s.triedToWithdrawOverLimit = false;
                        }
                    }
                }
            }
            else{
                message = "Invalid Amount";
                result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw)";
            }
            numberPadInput = "";
        }
        else {
            reset("You are not logged in");
        }
        update();
    }

    // Handle the Deposit button:
    // - If the user is logged in, deposit the amount entered into the bank
    // - Reads the amount from numberPadInput, validates it, and updates messages/results accordingly
    // - Otherwise, reset the ATM and display an error message
    public void processDeposit() {
        if (state.equals(STATE_LOGGED_IN)) {
            int amount = parseValidAmount(numberPadInput);
            if (amount > 0) {
                bank.deposit( amount );
                message = "Deposit Successful";
                result = "Deposited: " + numberPadInput;
            }
            else {
                message = "Invaild Amount";
                result = "Now enter the amount\nThen press transaction\n(Dep = Deposit, W/D = Withdraw)";
            }
            numberPadInput = "";
        }
        else {
            reset("You are not logged in");
        }
        update();
    }

    // Handle the Finish button:
    // - If the user is logged in, log out
    // - Otherwise, reset the ATM and display an error message
    public void processFinish() {
        if (state.equals(STATE_LOGGED_IN) ) {
            reset("Thank you for using the Bank ATM");
            bank.logout();
        } else {
            reset("You are not logged in");
        }
        update();
    }

    // Handle unknown or invalid buttons for the current state:
    // - Reset the ATM and display an "Invalid Command" message
    public void processUnknownKey(String action) {
        reset("Invalid Command");
        update();
    }

    public void processPsswd(){
        if(bank.loggedIn()){
            message = "Change your password";
            result = "Please enter your current password \nto change it";
            setState(STATE_CHANGE_PASSWD);
        }else {
            reset("Login before changing password");
        }
        update();
    }

    public void processLogin(){
        reset("Login here");
        view.changeButtons();
    }

    public void createAccount(){
        setState(STATE_NEW_ACCOUNT);
        view.changeButtons();
        processEnter();
    }

    // Notify the View of changes by calling its update method
    private void update() {
        view.update(message,numberPadInput, result);
    }

    public void createAccountFinal(){
        if(accType.equals("standard")){
            bank.addBankAccount(tempAccount[0],tempAccount[1],parseInt(tempAccount[2]));
            numberPadInput = "";
            setState(STATE_ACCOUNT_NO);
            //processEnter();
            System.out.println("Created standard account");
        } else if (accType.equals("saving")) {
            bank.addSavingsBankAccount(tempAccount[0],tempAccount[1],parseInt(tempAccount[2]),parseInt(tempAccount[3]));
            numberPadInput = "";
            setState(STATE_ACCOUNT_NO);
            //processEnter();
            System.out.println("Created savings acccount");
        } else if (accType.equals("prime")) {
            bank.addPrimeBankAccount(tempAccount[0],tempAccount[1],parseInt(tempAccount[2]),parseInt(tempAccount[3]),parseInt(tempAccount[4]));
            numberPadInput = "";
            setState(STATE_ACCOUNT_NO);
            //processEnter();
            System.out.println("Created prime account");
        } else if (accType.equals("student")) {
            bank.addStudentBankAccount(tempAccount[0],tempAccount[1],parseInt(tempAccount[2]),parseInt(tempAccount[3]));
            numberPadInput = "";
            setState(STATE_ACCOUNT_NO);

        }
    }
}

