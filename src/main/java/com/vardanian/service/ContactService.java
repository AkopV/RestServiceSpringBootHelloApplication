package com.vardanian.service;

import com.vardanian.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    long count();

    void save(List<Contact> contacts);

    void deleteAllContacts();
}

