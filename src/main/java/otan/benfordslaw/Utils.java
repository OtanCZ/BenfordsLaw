package otan.benfordslaw;

import otan.benfordslaw.entities.Transaction;
import otan.benfordslaw.entities.User;

import java.io.*;
import java.util.ArrayList;

import static otan.benfordslaw.BenfordApplication.transactions;
import static otan.benfordslaw.BenfordApplication.users;

public class Utils {
    public ArrayList<Transaction> loadFile(File file) throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        br.readLine();
        String line = br.readLine();
        while (line != null) {
            String[] split = line.split(";");
            Transaction transaction = new Transaction(split[0], new User(split[1]), new User(split[2]), Float.parseFloat(split[3]), split[4]);
            if(!users.containsKey(transaction.getFrom().getName())) {
                users.put(transaction.getFrom().getName(), transaction.getFrom());
                users.get(transaction.getFrom().getName()).addToTransactions(firstDigit(transaction.getAmount()));
            } else {
                users.get(transaction.getFrom().getName()).addToTransactions(firstDigit(transaction.getAmount()));
                transaction.setFrom(users.get(transaction.getFrom().getName()));
            }
            if(!users.containsKey(transaction.getTo().getName())) {
                users.put(transaction.getTo().getName(), transaction.getTo());
                users.get(transaction.getTo().getName()).addToTransactions(firstDigit(transaction.getAmount()));
            } else {
                users.get(transaction.getTo().getName()).addToTransactions(firstDigit(transaction.getAmount()));
                transaction.setTo(users.get(transaction.getTo().getName()));
            }
            transactions.add(transaction);
            line = br.readLine();
        }
        return transactions;
    }

    public ArrayList<Transaction> getTransactionsBetween(String sender, String receiver) {
        ArrayList<Transaction> transactionsBetween = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getFrom().getName().equals(sender) && transaction.getTo().getName().equals(receiver)) {
                transactionsBetween.add(transaction);
            }
        }
        return transactionsBetween;
    }

    public ArrayList<Transaction> getTransactionsStartingWith(ArrayList<Transaction> list, int digit) {
        ArrayList<Transaction> transactionsStartingWith = new ArrayList<>();
        for (Transaction transaction : list) {
            if (firstDigit(transaction.getAmount()) == digit) {
                transactionsStartingWith.add(transaction);
            }
        }
        return transactionsStartingWith;
    }

    public float P(int d) {
        return (float) (Math.log(1 + (1.0 / d)) / Math.log(10));
    }

    public int firstDigit(float number) {
        return Integer.parseInt(String.valueOf(String.valueOf(number).charAt(0)));
    }
}
