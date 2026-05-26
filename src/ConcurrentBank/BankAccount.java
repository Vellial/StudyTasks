package ConcurrentBank;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private AtomicInteger balance;

    public BankAccount(int i) {
        this.balance = new AtomicInteger(i);
    }

    void deposit(int i) {
        balance.addAndGet(i);
    }

    void withdraw(int i) {
        balance.addAndGet(-i);
    }

    int getBalance() {
        return balance.get();
    }
}
