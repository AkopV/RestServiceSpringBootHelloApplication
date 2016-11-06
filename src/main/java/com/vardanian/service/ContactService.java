package com.vardanian.service;

import com.vardanian.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    long count();

    void save(List<Contact> contacts);

    void deleteAllContacts();
}

