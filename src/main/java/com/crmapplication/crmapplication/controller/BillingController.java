package com.crmapplication.crmapplication.controller;

import com.crmapplication.crmapplication.entity.Contact;
import com.crmapplication.crmapplication.service.impl.ContactServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {

    @Autowired
    private ContactServiceimpl contactService;

    @RequestMapping("/generateBill")
    public ResponseEntity<Contact> viewBillingPage(@PathVariable("id") long id) {
        Contact contact = contactService.getContactById(id);

        return new ResponseEntity<>(contact, HttpStatus.OK);

    }


}
