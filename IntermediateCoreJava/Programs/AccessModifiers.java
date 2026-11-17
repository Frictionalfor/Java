class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    protected void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        // Cannot directly modify balance (private in parent)
        // Would need a public/protected method
    }
}

public class AccessModifiers {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        System.out.println("Initial balance: " + account.getBalance());
        account.deposit(500);
        System.out.println("After deposit: " + account.getBalance());
    }
}
