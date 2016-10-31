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

    public List<Contact> filterContactByName(List<Contact> contacts, String name) {
        List<Contact> filterContacts = new ArrayList<>();
        for (Contact filter : contacts) {
            if (!checkWithRegExp(filter.getName(), name)){
                filterContacts.add(filter);
            }
        }
        return filterContacts;
    }

    private  boolean checkWithRegExp(String userName, String filter){
        Pattern p = Pattern.compile(filter);
        Matcher m = p.matcher(userName);
        return m.matches();
    }
}
