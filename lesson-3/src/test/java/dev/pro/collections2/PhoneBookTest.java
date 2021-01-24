package dev.pro.collections2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void userCanAddPhoneNumberAndFindByFamily() {
        var phoneBook = new PhoneBook();

        phoneBook.add("Ivanov" , 89991112233l);
        phoneBook.add("Ivanov" , 89991112244l);

        var phones  = phoneBook.get("Ivanov");
        assertEquals(2, phones.length);
        assertTrue(Arrays.stream(phones).anyMatch(phone -> phone == 89991112233l));
        assertTrue(Arrays.stream(phones).anyMatch(phone -> phone == 89991112244l));
    }
}