A simple backend API for a banking system. Alkansya API manages customer profiles, bank accounts, account lifecycle status, and common banking transactions such as deposits, withdrawals, online transfers, and bills payments.

## Features

- **Customer Management**: Register new customers, update customer details, retrieve customer profiles, and deactivate customer records when they are no longer active.
- **Customer Account Listing**: View all active bank accounts linked to a specific customer, making it easier to manage a customer's banking relationship in one place.
- **Bank Account Management**: Create bank accounts for existing customers, retrieve account details, and deactivate accounts while enforcing account status rules.
- **Account Type Support**: Handles deposit, debit, and credit account behavior with different balance rules depending on the account type.
- **Transaction Processing**: Supports deposits, withdrawals, online transfers, and bills payments through a centralized transaction flow.
- **Balance Updates**: Automatically updates account balances when transactions are processed, including debit balance deductions, deposit balance increases, and credit balance usage.
- **Transaction Validation**: Validates required transaction data, active account status, positive transaction amounts, target accounts for transfers, and biller details for bills payments.
- **Business Rule Enforcement**: Applies customer and account rules such as valid names, numeric mobile numbers, valid birth dates, minimum customer age, sufficient debit balance, available credit limit, and zero credit balance before deactivation.
- **Transaction History**: Retrieves transactions by account ID and supports sorting by selected transaction fields in ascending or descending order.
- **API Error Handling**: Returns meaningful HTTP error responses for missing records, invalid requests, inactive accounts, duplicate/no-op updates, insufficient funds, and business rule conflicts.
  
## Tech Stack

- **Java**: Backend logic written in Java.
- **Spring Boot**: For building the backend application.
- **Spring Data JPA**: Repository layer for customer, account, and transaction persistence.
- **MySQL**: Database for storing customer, account, and transaction information.
  
