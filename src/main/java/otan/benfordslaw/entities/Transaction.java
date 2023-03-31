package otan.benfordslaw.entities;

import java.util.ArrayList;

import static otan.benfordslaw.BenfordApplication.transactions;

public class Transaction {
    private String id;
    private User from;
    private User to;
    private float amount;
    private String date;

    public Transaction(String id, User from, User to, float amount, String date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
