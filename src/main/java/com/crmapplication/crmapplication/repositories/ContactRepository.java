package com.crmapplication.crmapplication.repositories;

import com.crmapplication.crmapplication.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
