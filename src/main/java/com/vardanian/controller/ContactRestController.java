package com.vardanian.controller;

import com.vardanian.entity.Contact;
import com.vardanian.filter.FilterContact;
import com.vardanian.service.ContactService;
import com.vardanian.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class ContactRestController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/hello/contacts", params = "nameFilter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contact>> getContactByName(@RequestParam("nameFilter") String nameFilter) {

        FilterContact filterContact = new FilterContact();
        List<Contact> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()){
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<List<Contact>>(filterContact.filterContactByName(contacts, nameFilter), HttpStatus.OK);
    }
}
