package com.vardanian.service.impl;

import com.vardanian.entity.Contact;
import com.vardanian.repository.ContactRepository;
import com.vardanian.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
