package com.rv;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

    Map<String,Contact>  contactList = new ConcurrentHashMap<>();

    public void addContact(String firstName,String lastName,String contactNo){
        Contact contact = new Contact(firstName,lastName,contactNo);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactList.put(generateKey(contact), contact);

    }
    public Collection<Contact> getAllContact(){
        return contactList.values();
    }

    private String generateKey(Contact contact) {
        //key = firstName-lastName
        return String.format("%s-%s",contact.getFirstName(),contact.getLastName());
    }

    private void checkIfContactAlreadyExist(Contact contact) {
        if (contactList.containsKey(generateKey(contact)))
            throw new RuntimeException("Contact already exist");
    }

    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validateContactNumber();
    }
}
