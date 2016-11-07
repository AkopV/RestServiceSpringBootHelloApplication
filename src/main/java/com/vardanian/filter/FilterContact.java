package com.vardanian.filter;

import com.vardanian.entity.Contact;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContact {

    private static final Logger LOG = Logger.getLogger(FilterContact.class);

    private Pattern pattern;

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(String nameFilter) {
        pattern = Pattern.compile(nameFilter);
    }

    public List<Contact> filterContactByName(List<Contact> contacts, String name) {
        LOG.info("Start filtering all data");
        List<Contact> filterContacts = new ArrayList<>();
        for (Contact filter : contacts) {
            Boolean expression = checkWithRegExp(filter.getName(), name);
            if (!expression) {
                filterContacts.add(filter);
            }
        }
        LOG.info("All data was filtered");
        return filterContacts;
    }

    private boolean checkWithRegExp(String userName, String filter) {
        setPattern(filter);
        Matcher m = pattern.matcher(userName);
        return m.matches();
    }
}
