# Banking Transaction Management System

A Java console-based banking application that simulates basic banking operations using JDBC and MySQL.

The project focuses on correct handling of money-related operations, transaction safety, and clean separation of responsibilities between different layers of the application.

---

## Features

- Create customers
- Open bank accounts
- Deposit and withdraw money
- Transfer funds between accounts
- Balance enquiry
- Input validation for phone number and email
- Menu-driven console interface

---

## Technology Used

- Java (JDK 21)
- MySQL 8
- JDBC

---

## Transaction Handling

Fund transfers are handled using JDBC transaction control to ensure data consistency.

- Auto-commit is disabled before starting a transfer
- Withdraw and deposit are executed within the same transaction
- Changes are committed only if all operations succeed
- Rollback is performed if any step fails

This prevents partial updates and ensures balances remain consistent.

---

## Console Menu

1. Create Customer
2. Open Account
3. Deposit
4. Withdraw
5. Transfer
6. Check Balance
7. Exit

---

## Conclusion

This project demonstrates a clear approach to building a console-based banking system with reliable transaction handling and structured design.  
It emphasizes correctness, data consistency, and simplicity while simulating real-world banking operations.
