package com.vardanian.component;


import com.vardanian.entity.Contact;
import com.vardanian.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class ContactCommandLineRunner implements CommandLineRunner {

    @Autowired
    ContactService contactService;

    @Override
    public void run(String... args) throws Exception {
        for (Contact contact : contactService.getAllContacts()) {
            System.out.println(contact.toString());
        }
    }
}