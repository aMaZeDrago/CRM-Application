package com.crmapplication.crmapplication.service;

import com.crmapplication.crmapplication.entity.Contact;


import java.util.List;

public interface ContactService {
	
	public Contact saveContact(Contact contact);

	public List<Contact> listAll();

	public Contact getContactById(long id);

}
