package com.driver;

import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        try {
            String accountNumber = AccountNumberGenerator(digits, sum);
            return accountNumber;
        } catch (Exception e) {

        }

        return null;
    }

    public static String AccountNumberGenerator(int digits, int sum) throws Exception {
        Random random = new Random();
        int[] accountDigits = new int[digits];
        int remainingSum = sum;

        // Generate digits randomly while maintaining the sum
        for (int i = 0; i < digits; i++) {
            int maxPossibleDigit = Math.min(remainingSum, 9);

            if (maxPossibleDigit == 0 && i == digits - 1 && remainingSum > 0) {
                throw new Exception("Account Number can not be generated");
            }

            accountDigits[i] = random.nextInt(maxPossibleDigit + 1);
            remainingSum -= accountDigits[i];
        }

        // If there's still remaining sum, distribute it among the digits
        for (int i = digits - 1; remainingSum > 0; i--) {
            int maxPossibleDigit = Math.min(remainingSum, 9 - accountDigits[i]);

            if (maxPossibleDigit == 0 && i == 0 && remainingSum > 0) {
                throw new Exception("Account Number can not be generated");
            }

            accountDigits[i] += random.nextInt(maxPossibleDigit + 1);
            remainingSum -= accountDigits[i];
        }

        // Convert the account digits to a string
        StringBuilder accountNumberBuilder = new StringBuilder();
        for (int digit : accountDigits) {
            accountNumberBuilder.append(digit);
        }

        return accountNumberBuilder.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        double rem=this.balance-amount;
        if(rem<minBalance) throw new Exception("Insufficient Balance");
        else this.balance-=amount;
    }

}