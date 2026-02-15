package bank.dao;

import java.util.List;

import bank.exceptions.AccountNotFoundException;
import bank.model.Account;

public interface AccountDAO {
	public void save(Account account);
    public List<Account> findAll();
    public Account findById(long accountNo) throws AccountNotFoundException;
    public void deleteById(long accountNo) throws AccountNotFoundException;
    public void update(Account account) throws AccountNotFoundException;
}

