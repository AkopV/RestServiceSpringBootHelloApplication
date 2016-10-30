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

    @RequestMapping(value = "/contacts", params = "nameFilter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contact>> getContactByName(@RequestParam("nameFilter") String nameFilter) {
        Utils utils = new Utils();
        List<Contact> contacts = new ArrayList<>();
        contacts = utils.recordDBContacts();
        FilterContact filterContact = new FilterContact();

        contactService.save(contacts);
        contacts = contactService.getAllContacts();

        if (contacts.isEmpty()){
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<List<Contact>>(filterContact.filterContactByName(contacts, nameFilter), HttpStatus.OK);
    }

    @RequestMapping(value = "/contacts", params = "nameFilter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Contact> getAllContacts(@RequestParam("nameFilter") String nameFilter) {
        Utils utils = new Utils();
        List<Contact> contacts = new ArrayList<>();
        contacts = utils.recordDBContacts();
        FilterContact filterContact = new FilterContact();

        contactService.save(contacts);
        contacts = contactService.getAllContacts();

        if (contacts.isEmpty()){
            return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<Contact>(filterContact.getContactByName(contacts, nameFilter), HttpStatus.OK);
    }
}
