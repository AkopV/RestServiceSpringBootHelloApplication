package com.vardanian.controller;

import com.vardanian.entity.Contact;
import com.vardanian.repository.ContactRepository;
import com.vardanian.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactRestController {

    @Autowired
    ContactService contactService;

    @RequestMapping("/contacts")
    List<Contact> contacts () {
        return contactService.getAllContacts();
    }
}
