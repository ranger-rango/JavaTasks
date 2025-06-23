package main.java.com.habil.models;

public class PaymentProcessor
{
    @ImportantMethod(importance = "critical")
    public static void initiatePayment()
    {
        System.out.println("Initiating payment: ...");
        System.out.println("Accounts.checkBalance");
        System.out.println("if (sufficient) -> Accounts.SendMoney(ReceiverAccount)");
        System.out.println("else -> cancelPayment()");
    }

    @ImportantMethod(importance = "critical")
    public static void receivePayment()
    {
        System.out.println("Receiving Pyament: ...");
        System.out.println("");
    }

    public static void confirmPayment()
    {
        System.out.println("Confirming Payment: ...");
    }

    public static void cancelPayment()
    {
        System.out.println("Cancelling Payment: ...");
    }

    @ImportantMethod(importance = "critical")
    public static void logTransaction()
    {
        System.out.println("Logging payment transaction ...");
    }
}
