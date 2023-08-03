package com.crmapplication.crmapplication.controller;

import com.crmapplication.crmapplication.entity.Contact;
import com.crmapplication.crmapplication.entity.Lead;
import com.crmapplication.crmapplication.service.impl.ContactServiceimpl;
import com.crmapplication.crmapplication.service.impl.LeadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contact")
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactServiceimpl contactService;

    @Autowired
    private LeadServiceImpl leadService;

    @GetMapping
    public ResponseEntity<List<Contact>> listAllContacts() {
        logger.info("getting contacts");
        List<Contact> contacts = contactService.listAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable long id) {
        logger.info("getting contact with id:{}",id);
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @PostMapping("/convertLead/{id}")
    public ResponseEntity<List<Contact>> convertLead(@PathVariable("id") long id) {
        logger.info("getting lead with id:{}",id);
        Lead lead = leadService.getLeadById(id);

        Contact contact = new Contact();
        logger.info("converting lead to contact");
        contact.setFirstName(lead.getFirstName());
        contact.setLastName(lead.getLastName());
        contact.setEmail(lead.getEmail());
        contact.setLeadSource(lead.getLeadSource());
        contact.setMobile(lead.getMobile());
        contactService.saveContact(contact);

        leadService.deleteOneLead(id);

        List<Contact> contacts = contactService.listAll();
        return new ResponseEntity<>(contacts,HttpStatus.OK);

    }




}
