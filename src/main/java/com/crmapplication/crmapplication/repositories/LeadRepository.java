package com.crmapplication.crmapplication.repositories;


import com.crmapplication.crmapplication.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
