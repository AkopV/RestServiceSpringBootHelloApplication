package com.vardanian.utils;

import com.vardanian.entity.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    public List<String> getNames(String fileName){
        List<String> names = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                names.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return names;
    }

    public List<Contact> recordDBContacts() {
        List<String> names = getNames("name.csv");
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 500; j++) {
                stringBuilder.append(names.get(random.nextInt(names.size())));
                contacts.add(new Contact(stringBuilder.toString()));
                stringBuilder.setLength(0);
            }
        }
        return contacts;
    }
}
