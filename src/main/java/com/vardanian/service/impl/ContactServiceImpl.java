package com.vardanian.service.impl;

import com.vardanian.entity.Contact;
import com.vardanian.repository.ContactRepository;
import com.vardanian.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public long count() {
        return contactRepository.count();
    }

    @Override
    public void save(List<Contact> contacts) {
        contactRepository.save(contacts);
    }

    @Override
    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }
}
