package com.crmapplication.crmapplication.service.impl;

import com.crmapplication.crmapplication.entity.Contact;
import com.crmapplication.crmapplication.exception.ResourceNotFoundException;
import com.crmapplication.crmapplication.repositories.ContactRepository;
import com.crmapplication.crmapplication.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceimpl implements ContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        Contact savecontact = contactRepo.save(contact);
        return savecontact;
    }

    @Override
    public List<Contact> listAll() {
        List<Contact> contacts = contactRepo.findAll();
        return contacts;
    }

    @Override
    public Contact getContactById(long id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact with id:"+id+ " not found"));
        return contact;
    }
}
