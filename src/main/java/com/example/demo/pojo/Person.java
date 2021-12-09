package com.example.demo.pojo;

import java.util.List;

public class Person {

    private List<Wallet> wallets;

    public Person() {}

    public Person(List<Wallet> wallets) {
        this.setWallets(wallets);
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
