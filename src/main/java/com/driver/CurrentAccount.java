package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        String licenseId=this.tradeLicenseId;
        try {
          generateValidLicenseId(licenseId);

        } catch (Exception e) {

        }

    }
    public void generateValidLicenseId(String licenseId) throws Exception{
        if (licenseId.length() == 1) {
            throw new Exception("Valid License can not be generated");
        }

        char[] licenseChars = licenseId.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char c : licenseChars) {
            if (Character.isUpperCase(c)) {
                charList.add(c);
            }
        }

        if (charList.size() < 2) {
            throw new Exception("Valid License can not be generated");
        }

        Collections.shuffle(charList);
        char[] shuffledChars = new char[licenseChars.length];
        int index = 0;

        // Rearrange the characters
        for (int i = 0; i < shuffledChars.length; i++) {
            if (Character.isUpperCase(licenseChars[i])) {
                shuffledChars[i] = charList.get(index++);
            } else {
                shuffledChars[i] = licenseChars[i];
            }
        }

        String validLicenseId = new String(shuffledChars);

        // Check if the rearranged license ID is valid
        if (!isValidLicenseId(validLicenseId)) {
            throw new Exception("Valid License can not be generated");
        }
    }

    public  boolean isValidLicenseId(String licenseId) {
        for (int i = 1; i < licenseId.length(); i++) {
            if (licenseId.charAt(i) == licenseId.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

}
