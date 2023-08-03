package com.crmapplication.crmapplication.repositories;


import com.crmapplication.crmapplication.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    Page<Lead> findByleadSource(String leadSource, Pageable pageable);

}
