package com.spring.instagram.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/email")
public class EmailService {

    private final ContactList contactList;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    public EmailService(ContactList contactList){
        this.contactList = contactList;
    }



    @GetMapping
    public String SendMail() {
        log.info("contact list", this.contactList.getContacts());
        return "OK";
    }
}
