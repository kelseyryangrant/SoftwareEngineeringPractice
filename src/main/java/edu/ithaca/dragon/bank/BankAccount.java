package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * When the amount is negative and the user attempts to withdraw, throw a Runtime exception
     * and print "ERROR: Your account is currently negative. No funds can be withdrawn.".
     * When the user attempts to withdraw more funds than they have in a positive account,
     * throw a Runtime exception and print "ERROR: You do not have enough funds to withdraw
     * that amount."
     */
    public void withdraw (double amount)  {
        if(amount < 0){
            throw new RuntimeException("ERROR: Your account is currently negative. No funds can be withdrawn.");
        }
        if(amount > balance){
            throw new RuntimeException("ERROR: You do not have enough funds to withdraw that amount.");
        }
        balance -= amount;

    }


    public static boolean isEmailValid(String email){

        //no @ symbol, @ symbol first, @ symbol last
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0 || email.indexOf('@') == email.length()-1){
            return false;
        }
        else {
            int index = 0;
            //part before domain
            while(!String.valueOf(email.charAt(index)).equals("@")){
                //if character is one of the acceptable special characters
                if ("-_.".contains(String.valueOf(email.charAt(index)))){
                    //if the next character is not a letter or number
                    if (!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".contains(String.valueOf(email.charAt(index+1)))){
                        return false;
                    }
                }
                //if the character is not a letter or number (aka a non-acceptable special character)
                else if(!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@".contains(String.valueOf(email.charAt(index)))){
                    return false;
                }
                index+=1;
            }
            int indexOfAt = index;
            index+=1;
            //part 2: post @ symbol
            while (index != email.length()-1){
                if(String.valueOf(email.charAt(index)).equals(".")){
                    //nothing between the @ and the dot.
                    if (index == indexOfAt+1){
                        return false;
                    }
                    //less than 2 characters after the @
                    if(index >= email.length()-2){
                        return false;
                    }
                }
                //if it is any special character
                else if(!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".contains(String.valueOf(email.charAt(index)))){
                    return false;
                }
                index+=1;
            }
            return true;
        }
    }
}
