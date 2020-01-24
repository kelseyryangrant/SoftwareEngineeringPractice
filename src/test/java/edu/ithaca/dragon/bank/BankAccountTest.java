package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());


        // Equivalence Class: amount > balance
        // Border Case: No
        //overdraw positive account test
        BankAccount bankAccount1 = new BankAccount("abc@mail.com", 10);
        assertThrows(RuntimeException.class, ()-> bankAccount1.withdraw(40));

        //overdraw negative account test
        BankAccount bankAccount2 = new BankAccount("abcd@mail.com", -10);
        // Equivalence Class: amount > balance
        // Border Case: No
        assertThrows(RuntimeException.class, ()-> bankAccount2.withdraw(200));

        // Equivalence Class: amount < balance, amount positive
        // Border Case: No
        //successful withdraw test
        BankAccount bankAccount3 = new BankAccount("abcde@mail.com", 100);
        bankAccount3.withdraw(20);

        assertEquals(80, bankAccount3.getBalance());

        //missing:
            //all border cases
            //equivalence class of negative amount
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        // Equivalence Class: periods in prefix
        // Border Case: Yes, 1 away from @ sign
        //valid use of several "." characters
        assertTrue(BankAccount.isEmailValid("a.b.c@mail.com"));

        // Equivalence Class: periods in prefix
        // Border Case: Yes, . next to @
        //invalid use of several "." characters
        assertFalse(BankAccount.isEmailValid("a.b..c@mail.com"));

        //valid use of "-" character
        // Equivalence Class: dash in prefix
        // Border Case: No
        assertTrue(BankAccount.isEmailValid("a-bc@mail.com"));

        // Equivalence Class: dash in prefix
        // Border Case: Yes, next to @ sign
        //invalid use of "-" character
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));

        //valid use of numbers

        // Equivalence Class: No special characters
        // Border Case: No
        assertTrue(BankAccount.isEmailValid("abc123@mail.org"));
        //invalid use of numbers
        //assertFalse(BankAccount.isEmailValid("abc@123.123"));
        //commented the above test out because according to the email rules, it is technically valid

        // Equivalence Class: Correct length of domain
        // Border Case: Yes, minimum number of characters
        //valid use of domain
        assertTrue(BankAccount.isEmailValid("abc123@mail.cc"));

        // Equivalence Class: incorrect length of domain
        // Border Case: Yes, 1 less than necessary length
        //invalid use of domain
        assertFalse(BankAccount.isEmailValid("abc123@mail.2"));

        //missing:
            //borders for special characters being first in prefix
            // some equivalence classes in domain (had borders but both EC and borders are necessary)
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}