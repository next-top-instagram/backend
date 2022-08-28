package com.spring.instagram.email;

import org.hibernate.annotations.Immutable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactListService implements ContactList{
    @Override
    public List<String> getContacts() {
        ArrayList<String> languages = new ArrayList<>();

        // Add elements to ArrayList
        languages.add("Java");
        languages.add("Python");
        languages.add("Swift");
       return languages;
    }

}
