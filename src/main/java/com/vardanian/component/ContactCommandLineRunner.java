package com.vardanian.component;

import com.vardanian.service.ContactService;
import com.vardanian.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContactCommandLineRunner implements CommandLineRunner {

    ContactService contactService;

    Utils utils = Utils.getInstance();

    @Autowired
    public ContactCommandLineRunner(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.contactService.save(utils.recordDBContacts());
    }
}