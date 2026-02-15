package bank.service;

import bank.dao.AccountDAO;
import bank.dao.AccountDAOImpl;
import bank.dao.TransactionDAO;
import bank.dao.TransactionDAOImpl;
import bank.exceptions.AccountNotFoundException;
import bank.exceptions.BankingException;
import bank.model.Account;
import bank.model.Transaction;

public class BankingService {

    private AccountDAO accountDAO = new AccountDAOImpl();
    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    // CREATE ACCOUNT
    public void createAccount(Account acc) {
        accountDAO.save(acc);
    }

    // LOGIN
    public Account login(long accNo)
            throws AccountNotFoundException {

        return accountDAO.findById(accNo);
    }

    // DEPOSIT
    public void deposit(long accNo, double amount)
            throws AccountNotFoundException {

        Account acc = accountDAO.findById(accNo);

        acc.setBalance(acc.getBalance() + amount);

        accountDAO.update(acc);

        transactionDAO.save(
                new Transaction(accNo, "DEPOSIT", amount));
    }

    // WITHDRAW
    public void withdraw(long accNo, double amount)
            throws AccountNotFoundException, BankingException {

        Account acc = accountDAO.findById(accNo);

        if (acc.getBalance() < amount) {
            throw new BankingException("Insufficient Balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        accountDAO.update(acc);

        transactionDAO.save(
                new Transaction(accNo, "WITHDRAW", amount));
    }
}
