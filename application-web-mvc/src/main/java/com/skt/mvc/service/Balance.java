package com.skt.mvc.service;

import lombok.Data;

@Data
public class Balance {
    private long balance;

    public static Balance newInstance(long amount) {
        Balance balance = new Balance();
        balance.setBalance(amount);
        return balance;
    }

    public Balance plusMoney(long amount) {
        Balance balance = new Balance();
        balance.setBalance(getBalance() + amount);
        return balance;
    }

    public Balance minusMoney(long amount) {
        Balance balance = new Balance();

        if (getBalance() - amount < 0) {
            throw new IllegalArgumentException("돈 부족함");
        }

        balance.setBalance(getBalance() - amount);
        return balance;
    }
}
