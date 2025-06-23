package main.java.com.habil.model;

public class BankAccount
{
    private double balance;

    public BankAccount(double balance)
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void deposit(double amount)
    {
        if (amount <= 0)
        {
            System.out.println("Deposit Failed. Amount cannot be NIL/NEGATIVE: " + amount);
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException
    {
        if (amount > balance)
        {
            throw new InsufficientFundsException("Withdrawal Failed. Insufficient Funds: " + amount);
        }
    }
    
}
