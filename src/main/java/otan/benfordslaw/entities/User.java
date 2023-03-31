package otan.benfordslaw.entities;

import java.util.HashMap;

import static otan.benfordslaw.BenfordApplication.transactions;
import static otan.benfordslaw.BenfordApplication.utils;

public class User {
    private String name;
    private float sussyPercent;
    private HashMap<Integer, Integer> allTransactions;
    private int totalTransactions;

    public User(String name) {
        this.name = name;
        this.sussyPercent = 0;
        this.allTransactions = new HashMap<>();
        this.totalTransactions = 0;
    }

    public void calculateBenfordsLaw() {
        for (int numberTransaction : allTransactions.keySet()) {
            float benfordsLaw = utils.P(numberTransaction);
            float userTransactions = allTransactions.get(numberTransaction);
            float percent = (userTransactions / totalTransactions);
            if (percent > benfordsLaw) {
                sussyPercent += percent - benfordsLaw;
            }
        }
    }

    public void addToTransactions(int firstDigit) {
        if (allTransactions.containsKey(firstDigit)) {
            allTransactions.put(firstDigit, allTransactions.get(firstDigit) + 1);
            totalTransactions++;
        } else {
            allTransactions.put(firstDigit, 1);
            totalTransactions++;
        }
    }

    public HashMap<Integer, Integer> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(HashMap<Integer, Integer> allTransactions) {
        this.allTransactions = allTransactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSussyPercent() {
        return sussyPercent;
    }

    public void setSussyPercent(float sussyPercent) {
        this.sussyPercent = sussyPercent;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sussyPercent=" + sussyPercent +
                ", allTransactions=" + allTransactions +
                '}';
    }
}
