package ejercicios1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simulación de transferencias bancarias con sincronización.
 */
public class BankSimulation {
    static class BankAccount {
        private int balance;
        private final Lock lock = new ReentrantLock();

        public BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }

        public void transfer(BankAccount target, int amount) {
            lock.lock();
            try {
                if (balance >= amount) {
                    balance -= amount;
                    target.deposit(amount);
                    System.out.println("Transferidos " + amount + " de " + this + " a " + target);
                } else {
                    System.out.println("Fondos insuficientes en " + this);
                }
            } finally {
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                balance += amount;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "Cuenta[" + balance + "]";
        }
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(500);

        Thread t1 = new Thread(() -> account1.transfer(account2, 300));
        Thread t2 = new Thread(() -> account2.transfer(account1, 100));

        t1.start();
        t2.start();
    }
}
