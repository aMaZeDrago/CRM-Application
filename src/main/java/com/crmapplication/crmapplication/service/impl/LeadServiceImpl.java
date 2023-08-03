package com.crmapplication.crmapplication.service.impl;

import com.crmapplication.crmapplication.entity.Lead;
import com.crmapplication.crmapplication.exception.ResourceNotFoundException;
import com.crmapplication.crmapplication.repositories.LeadRepository;
import com.crmapplication.crmapplication.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepo;

    public Page<Lead> getLeadsByStatusPaginated(String leadSource, Pageable pageable) {
        return leadRepo.findByleadSource(leadSource, pageable);
    }


    @Override
    public Lead saveLead(Lead lead) {

        Lead savelead =leadRepo.save(lead);

        return savelead;
    }

    @Override
    public Lead getLeadById(long id) {
        Lead lead = leadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lead with id:" + id + " not found"));
        return lead;
    }

    @Override
    public void deleteOneLead(long id) {
        Lead lead = leadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lead with id:{} not found"));
        leadRepo.deleteById(id);

    }

    @Override
    public List<Lead> listAll() {

        List<Lead> leads = leadRepo.findAll();
        return leads;
    }

}
