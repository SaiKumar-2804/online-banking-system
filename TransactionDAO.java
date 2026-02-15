package bank.dao;

import java.util.List;

import bank.exceptions.TransactionNotFoundException;
import bank.model.Transaction;

public interface TransactionDAO {
	 void save(Transaction transaction);

	    List<Transaction> findAll();

	    List<Transaction> findByAccountNo(long accountNo)
	            throws TransactionNotFoundException;

	    void deleteByAccountNo(long accountNo)
	            throws TransactionNotFoundException;
}
