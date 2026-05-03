# Computer-Lab-Work-Final

SDK - Java 25 (26 may potentially work idk tho so dont mess ok! :))

ATM‑Bank‑Simulator
A Java‑based ATM simulation that models multiple bank account types, file‑based persistence, a GUI interface, and a state‑driven UI logic system.

📌 Overview
The ATM‑Bank‑Simulator is built around a set of account classes, a bank manager, a UI model, and a simple MVC‑style structure.
It supports:

Multiple account types (Savings, Student, Prime)

Login/logout

Deposits, withdrawals, overdrafts, and limits

Local file storage for account data

A GUI with button‑driven interaction

A state‑machine‑based UI controller

🧱 Class Structure
Core Classes
BankAccount

SavingsBankAccount

StudentBankAccount

PrimeBankAccount

Bank

Main

Launcher

Controller

View

UIModel

🏦 BankAccount
The foundational class for all account types.

Stores:
accNumber

accPasswd

balance

accountType

Provides:
Getter methods (getX())

withdraw(amount)

deposit(amount)

“The methods in the account are all to do with returning information about the BankAccount class as well as depositing and withdrawing money…”
(from the uploaded document)

💰 Account Types
SavingsBankAccount
Inherits from BankAccount

Adds: interestRate

Automatically increases balance monthly

StudentBankAccount
Inherits from BankAccount

Adds: dailyWithdrawLimit

Prevents withdrawals after the limit is reached

PrimeBankAccount
Inherits from BankAccount

Adds:

Higher daily withdrawal limit

Overdraft capability

🏛️ Bank
Manages all accounts and login state.

Contains:
maxAccounts

accounts[]

loggedInAccount

Key Methods:
makeXAccount() / addXAccount()

login() / logout()

withdraw() / deposit() / getBalance()

🚀 Launcher
A minimal class that simply starts the application by invoking Main.

🧩 Main
Responsible for initializing:

UIModel

View

Controller

File I/O
localFileToAccounts()  
Loads accounts from a local text file at startup.

updateLocalFile()  
Saves updated account data whenever a button is pressed.

“localFileToAccounts() gets called when the application starts…”
(from the uploaded document)

🎮 Controller
Handles all UI input.
Its single method, process(), interprets user actions and triggers the appropriate logic.
Also calls updateLocalFile() in Main.

🖥️ View
Responsible for:

Creating all GUI components

Updating the display via update()

Wiring button interactions

🧠 UIModel
The heart of the application — manages all logic and UI state transitions.

Stores:
Current UI state

User messages

Number pad input

Text output for the display area

Handles:
All button‑press logic

State transitions

Bank statement updates

Notable Methods:
processEnter()  
The largest function in the project. Handles state switching and triggers logic based on the current state.

processUnknownKey()  
Prevents crashes when unexpected input occurs.

“processEnter(): Easily the biggest function in the entire application…”
(from the uploaded document)

📄 Features Summary
Multiple account types with unique rules

Overdraft support (Prime accounts)

Daily withdrawal limits

Interest accumulation (Savings accounts)

File‑based persistence

GUI‑driven interaction

State‑machine UI logic
