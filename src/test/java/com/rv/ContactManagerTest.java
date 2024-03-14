package com.rv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.List;


public class ContactManagerTest {

    private ContactManager contactManager;


    @BeforeEach
    void setUp() {
        System.out.println("Inside setup or before each");
        contactManager = new ContactManager();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Inside tear down or after each");
        contactManager = null;
    }

    @Test
    @DisplayName("Test case for creating contact successfully.........")
    public void shouldCreateContact() {
        contactManager.addContact("Ravi", "Prakash", "0123456789");
        Assertions.assertEquals(1, contactManager.getAllContact().size());
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
    }

    @Test
    @DisplayName("Test case for Not creating contact with first name.......")
    public void validateFirstNameEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Prakash", "0123456789");
        });
    }

    @Test
    @DisplayName("Test case for not creating contact with the last name....")
    public void validateLastNameEmpty() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("Ravi", null, "0123456789"));
    }

    @Test
    @DisplayName("Test case for not creating contact with contact number....")
    public void validateContactNumber() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("Ravi", "Prakash", null));
    }

    @Test
    @DisplayName("Test case for contact size not equals to 10..")
    public void validateContactNumberSize() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("Ravi", "Prakash", "01234567891"));
    }

    @Test
    @DisplayName("Test case for contact not starting with zero..")
    public void validateContactNotStartingWithZero() {
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("Ravi", "Prakash", "1234567890"));
    }

    @Test
    @DisplayName("Test for existing contact..->")
    public void validateContactAlreadyExist() {
        contactManager.addContact("Ravi", "Prakash", "0123456789");
        Assertions.assertThrows(RuntimeException.class,
                () -> contactManager.addContact("Ravi", "Prakash", "0123456789"),
                "Contact already exist");
    }


    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123789456", "0987654321", "0192837465"})
    @DisplayName("Test case for validating bulk of contact number")
    public void testTestContactNumberFormatUsingValueSource(String contact) {
        contactManager.addContact("Jhon", "Doe", contact);
        Assertions.assertEquals(1, contactManager.getAllContact().size());
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"0123456789", "0123789456", "0987654321", "0192837465"})
    @DisplayName("CSV source case - phone number should match with the required format")
    public void shouldTestContactNumberUsingCsvSource(String contact) {
        contactManager.addContact("John", "Doe", contact);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    @ParameterizedTest
    @DisplayName("CSV file source - phone number should match the required format")
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestContactNumberUsingCsvFileSource(String contact) {
        contactManager.addContact("John", "Doe", contact);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    //method source
    public static List<String> contactNumberSource() {
        return  List.of("0123456789", "0123789456", "0987654321", "0192837465");
    }
    @ParameterizedTest
    @DisplayName("Method source - contact number should match the required format")
    @MethodSource("contactNumberSource")
    public void shouldTestContactNumberUsingMethodSource(String contact){
        contactManager.addContact("John","Doe",contact);
            Assertions.assertEquals(1,contactManager.getAllContact().size());
            Assertions.assertFalse(contactManager.getAllContact().isEmpty());
    }
}
