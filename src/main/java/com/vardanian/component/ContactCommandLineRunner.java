package com.vardanian.component;

import com.vardanian.entity.Contact;
import com.vardanian.service.ContactService;
import com.vardanian.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactCommandLineRunner implements CommandLineRunner {

    private ContactService contactService;

    private Utils utils = Utils.getInstance();

    @Autowired
    public ContactCommandLineRunner(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Contact> contacts = utils.recordDBContacts();
        this.contactService.save(contacts);
    }
}