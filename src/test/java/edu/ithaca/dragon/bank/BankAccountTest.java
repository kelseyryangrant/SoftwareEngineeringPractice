package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void isAmountValidTest(){

        //equivalence class: negative
        assertFalse(BankAccount.isAmountValid(-0.01));
        assertFalse(BankAccount.isAmountValid(-10.00));
        assertFalse(BankAccount.isAmountValid(-0.10));

        //equivalence class: positive
        assertTrue(BankAccount.isAmountValid(0.01));
        assertTrue(BankAccount.isAmountValid(0.00));
        assertTrue(BankAccount.isAmountValid(0.10));
        assertTrue(BankAccount.isAmountValid(10));

        //equivalence class: valid decimal points
        assertTrue(BankAccount.isAmountValid(10.00));
        assertTrue(BankAccount.isAmountValid(10.000));
        assertTrue(BankAccount.isAmountValid(10.1));
        assertTrue(BankAccount.isAmountValid(10));

        //equivalence class: non-valid decimal points
        assertFalse(BankAccount.isAmountValid(10.001));
        assertFalse(BankAccount.isAmountValid(10.0001));
        assertFalse(BankAccount.isAmountValid(0.001));
        assertFalse(BankAccount.isAmountValid(0.0000001));

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
        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
        assertFalse(BankAccount.isEmailValid(""));

        //@ tests
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: one @
        assertFalse(BankAccount.isEmailValid("abcmail.com"));//border: no @
        assertFalse(BankAccount.isEmailValid("abc@@mail.com"));//border: 2 @
        assertFalse(BankAccount.isEmailValid("abc@@@mail.com"));

        //P R E F I X

        //".", "-", and "_" tests
        assertTrue(BankAccount.isEmailValid("a.b.c@mail.com"));//border: one next to @ & second character
        assertFalse(BankAccount.isEmailValid("a.bc.@mail.com"));//border: next to @
        //assertFalse(BankAccount.isEmailValid(".abc@mail.com"));//border: first character
        assertTrue(BankAccount.isEmailValid("a.bc@mail.com"));//border: second character
        assertFalse(BankAccount.isEmailValid("a..bc@mail.com"));//border: two next to each other
        assertFalse(BankAccount.isEmailValid("a...bc@mail.com"));

        assertTrue(BankAccount.isEmailValid("a-b-c@mail.com")); //border: one next to @ & second character
        assertFalse(BankAccount.isEmailValid("a-bc-@mail.com"));//border: next to @
        //assertFalse(BankAccount.isEmailValid("-abc@mail.com"));//border: first character
        assertTrue(BankAccount.isEmailValid("a-bc@mail.com"));//border: second character
        assertFalse(BankAccount.isEmailValid("a--bc@mail.com"));//border: two next to each other
        assertFalse(BankAccount.isEmailValid("a---bc@mail.com"));

        assertTrue(BankAccount.isEmailValid("a_b_c@mail.com"));//border: one next to @ & second character
        assertFalse(BankAccount.isEmailValid("a_bc_@mail.com"));//border: next to @
        //assertFalse(BankAccount.isEmailValid("_abc@mail.com"));//border: first character
        assertTrue(BankAccount.isEmailValid("a_bc@mail.com"));//border: second character
        assertFalse(BankAccount.isEmailValid("a__bc@mail.com"));//border: two next to eachother
        assertFalse(BankAccount.isEmailValid("a___bc@mail.com"));

        //disallowed special character tests
        //SPECIAL CHARACTERS: !#$%&'*+/=?^{|}~

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab#c@mail.com"));//border: presence of 1 "#"
        assertFalse(BankAccount.isEmailValid("ab##c@mail.com"));//two "#"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab!c@mail.com"));//border: presence of 1 "!"
        assertFalse(BankAccount.isEmailValid("ab!!c@mail.com"));//two "!"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab$c@mail.com"));//border: presence of 1 "$"
        assertFalse(BankAccount.isEmailValid("ab$$c@mail.com"));//two "$"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab%c@mail.com"));//border: presence of 1 "%"
        assertFalse(BankAccount.isEmailValid("ab%%c@mail.com"));//two "%"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab&c@mail.com"));//border: presence of 1 "&"
        assertFalse(BankAccount.isEmailValid("ab&&c@mail.com"));//two "&"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab'c@mail.com"));//border: presence of 1 "'"
        assertFalse(BankAccount.isEmailValid("ab''c@mail.com"));//two "'"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab+c@mail.com"));//border: presence of 1 "+"
        assertFalse(BankAccount.isEmailValid("ab++c@mail.com"));//two "+"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab/c@mail.com"));//border: presence of 1 "/"
        assertFalse(BankAccount.isEmailValid("ab//c@mail.com"));//two "/"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab=c@mail.com"));//border: presence of 1 "="
        assertFalse(BankAccount.isEmailValid("ab==c@mail.com"));//two "="

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab?c@mail.com"));//border: presence of 1 "?"
        assertFalse(BankAccount.isEmailValid("ab??c@mail.com"));//two "?"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab^c@mail.com"));//border: presence of 1 "^"
        assertFalse(BankAccount.isEmailValid("ab^^c@mail.com"));//two "^"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab{c@mail.com"));//border: presence of 1 "{"
        assertFalse(BankAccount.isEmailValid("ab{{c@mail.com"));//two "{"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab}c@mail.com"));//border: presence of 1 "}"
        assertFalse(BankAccount.isEmailValid("ab}}c@mail.com"));//two "}"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab|c@mail.com"));//border: presence of 1 "|"
        assertFalse(BankAccount.isEmailValid("ab||c@mail.com"));//two "|"

        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: no disallowed characters
        assertFalse(BankAccount.isEmailValid("ab~c@mail.com"));//border: presence of 1 "~"
        assertFalse(BankAccount.isEmailValid("ab~~c@mail.com"));//two "~"

        //S U F F I X

        //"." tests
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));//border: one period
        assertFalse(BankAccount.isEmailValid("abc@mailcom"));//border: no period
        assertFalse(BankAccount.isEmailValid("abc@mail..com"));//border: 2 periods
        assertFalse(BankAccount.isEmailValid("abc@mail...com"));

        assertFalse(BankAccount.isEmailValid("abc@.mailcom"));//border: first character
        assertTrue(BankAccount.isEmailValid("abc@m.ailcom"));//border: second character
        assertFalse(BankAccount.isEmailValid("abc@mailcom."));//border: last character
        assertFalse(BankAccount.isEmailValid("abc@mail.c"));//border: second to last character

        //disallowed special character tests
        //SPECIAL CHARACTER TESTS: !#$%&'*+/=?^{|}~

        assertTrue(BankAccount.isEmailValid("email@email.com"));//border: no special characters
        assertFalse(BankAccount.isEmailValid("email@email!.com"));
        assertFalse(BankAccount.isEmailValid("email@email#.com"));
        assertFalse(BankAccount.isEmailValid("email@email$.com"));
        assertFalse(BankAccount.isEmailValid("email@email%.com"));
        assertFalse(BankAccount.isEmailValid("email@email&.com"));
        assertFalse(BankAccount.isEmailValid("email@email'.com"));
        assertFalse(BankAccount.isEmailValid("email@email*.com"));
        assertFalse(BankAccount.isEmailValid("email@email+.com"));
        assertFalse(BankAccount.isEmailValid("email@email=.com"));
        assertFalse(BankAccount.isEmailValid("email@email?.com"));
        assertFalse(BankAccount.isEmailValid("email@email^.com"));
        assertFalse(BankAccount.isEmailValid("email@email{.com"));
        assertFalse(BankAccount.isEmailValid("email@email|.com"));
        assertFalse(BankAccount.isEmailValid("email@email}.com"));
        assertFalse(BankAccount.isEmailValid("email@email~.com"));



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