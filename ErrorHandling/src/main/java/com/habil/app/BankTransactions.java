package main.java.com.habil.app;

import main.java.com.habil.model.BankAccount;
import main.java.com.habil.model.InsufficientFundsException;

public class BankTransactions
{
    public static void main(String[] args)
    {
        BankAccount bankAccount = new BankAccount(50000.50);
        try
        {
            bankAccount.deposit(10);
            bankAccount.withdraw(100);
        }
        catch(InsufficientFundsException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            System.out.println("Transaction Complete");   
        }
    }
}
