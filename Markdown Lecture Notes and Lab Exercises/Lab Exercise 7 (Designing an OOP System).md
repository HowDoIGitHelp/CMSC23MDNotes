# Lab Exercise 7 (Designing an OOP System)
Links to relevant notes and videos
- [OOPython Notes](https://hackmd.io/@RubAbella/BJQc35DNP)
- [OOPython Video](https://www.youtube.com/watch?v=Djjc2k1_6WE)
- [UML](https://hackmd.io/@RubAbella/BJBHus-YS)
- [Exceptions](https://hackmd.io/@RubAbella/rk9RvE5Ew)
- [Python Introduction Notes](https://hackmd.io/@RubAbella/Syz0e_k8B)

## Task

**Banking System**

**You are to design a banking system that supports the following features:**

Before you start writing code for the system. Create a UML diagram that represents the system.

- 3 kinds of bank accounts: 
  - payroll -  this account can withdraw funds. This account can receive funds from transfers but it cannot transfer.
  - debit - this account can withdraw and deposit. This account can transfer funds to any account. Each month the balance compoundingly increases based on an interest rate. This account has a required balance that must be kept every month. If this balance is not kept the account becomes inactive (It's up to you to set that required balance amount).
  - credit - this account can withdraw. Withdrawing increases the credit balance (the amount owed). This account has a credit limit. The credit balance cannot exceed this credit limit. It can deposit which basically deducts the deposited amount from the current credit balance. This account can also transfer funds to any account which also increases the credit balance. It also has an interest rate where the credit balance increases compundingly each month. (it's also up to you to set the credit limit)
- applying all account changes - every month this is invoked, this changes the debit balances and credit balances based on interest. It also changes account status (deactivating/activating) once this triggers. (Note: you do not actually need to implement this in such a way that the changes happens automatically every month. Assume that this method is invoked every month)

- fund transfer from one account to another (all kinds of accounts can be transferred to, Note: do not allow a debit accounts to transfer an amount greater than the balance or a credit account to transfer an amount that will make the credit balance exceed the credit limit)
- deactivate and activate accounts
- account withdrawal (do not allow withdrawals that exceed the balance and the credit limit as well)
- account deposit (payroll accounts can't deposit)
- show the balance report of an account
- show account information (name of the account owner, type of the account and active/inactive status of the account)

You can place all of the classes into a single python file and you can also separate them into their own python files (just make sure you're importing correctly). If you put them all into a single python file, submit the python file. If you separate them into multiple python files, package them all into a zipped folder and submit the zipped folder.

*Feel free to add your own extra methods and extra features to this system. This is an open ended design exercise, go ahead and be creative. For example, I have not specified how the system might react if there are invalid withdrawals or transfers, it's up to you to implement those (do you print a message? or raise an error?)There is no single correct class architecture for this system.* 

*I'm not actually expecting you to build perfectly a elegant architecture for this system, this exercise's purpose is to get you used to building OOP systems from scratch.* 