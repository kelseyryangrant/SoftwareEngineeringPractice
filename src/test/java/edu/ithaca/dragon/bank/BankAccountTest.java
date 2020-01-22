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
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //valid use of several "." characters
        assertTrue(BankAccount.isEmailValid("a.b.c@mail.com"));
        //invalid use of several "." characters
        assertFalse(BankAccount.isEmailValid("a.b..c@mail.com"));

        //valid use of "-" character
        assertTrue(BankAccount.isEmailValid("a-bc@mail.com"));
        //invalid use of "-" character
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));

        //valid use of numbers
        assertTrue(BankAccount.isEmailValid("abc123@mail.org"));
        //invalid use of numbers
        assertFalse(BankAccount.isEmailValid("abc@123.123"));

        //valid use of domain
        assertTrue(BankAccount.isEmailValid("abc123@mail.cc"));
        //invalid use of domain
        assertFalse(BankAccount.isEmailValid("abc123@mail.2"));
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