package com.vardanian.filter;

import com.vardanian.entity.Contact;
import com.vardanian.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContact {

    private static final Logger LOG = Logger.getLogger(FilterContact.class);

    private Pattern pattern;

    @Autowired
    ContactService contactService;

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(String nameFilter) {
        pattern = Pattern.compile(nameFilter);
    }

    public List<Contact> filterContact(Iterator<Contact> iterator) {
        List<Contact> contacts = new ArrayList<>();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (pattern.matcher(contact.getName()).matches()){
                contacts.add(contact);
            }
        }
        return contacts;
    }

    public List<Contact> filterContactByName(List<Contact> contacts, String name) {
        for (Contact filter : contacts) {
            if (checkWithRegExp(filter.getName(), name)){
                contacts.add(filter);
            }
        }
        return contacts;
    }

    public  boolean checkWithRegExp(String userNameString, String filter){
        Pattern p = Pattern.compile(filter);
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    public Contact getContactByName(List<Contact> contacts, String name) {
        Contact contact = null;
        for(Contact temo : contacts) {
            if(name.equals(temo.getName())){
                contact = temo;
            }
        }
        return contact;
    }
}
