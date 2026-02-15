package bank.model;

public class Account {
	private long accountNo;
    private String name;
    private String password;
    private double balance;

    public Account() {}

    public Account(long accountNo, String name, String password, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

