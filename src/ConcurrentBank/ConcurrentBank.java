package ConcurrentBank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentBank {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final List<BankAccount> accounts = new ArrayList<>();
    private final Object lock = new Object();

    public BankAccount createAccount(int i) {
        synchronized (lock) {
            BankAccount account = new BankAccount(i);
            accounts.add(account);
            return account;
        }
    }

    public synchronized void transfer(BankAccount account2, BankAccount account1, int i) {
        if (account2.getBalance() < i) {
            System.out.println("Недостаточно средств для перевода");
            return;
        }

        account2.withdraw(i);
        account1.deposit(i);

        System.out.println("Перевод: " + i + " со счёта " +
                accounts.indexOf(account2) + " на счёт " +
                accounts.indexOf(account1));

    }

    public synchronized String getTotalBalance() {
        return ((Integer) accounts.stream().mapToInt(BankAccount::getBalance).sum())
                .toString();
    }
}
