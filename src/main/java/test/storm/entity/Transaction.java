package test.storm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by v.kapustin on 7/30/15.
 */
public class Transaction implements Serializable {

    private Long id;

    private String customer;

    private String account;

    private String bank;

    private double amount;

    private Date date;

    public Transaction() { }

    public Transaction(String customer, String account, String bank, double amount, Date date) {
        this.customer = customer;
        this.account = account;
        this.bank = bank;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringBuilder("Transaction [customer=").append(customer).append(", account=").append(account).append(", bank=").append(bank).append(", amount=").append(amount).append(", date=").append(date).append("]").toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
