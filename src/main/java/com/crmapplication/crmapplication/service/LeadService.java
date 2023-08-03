package com.crmapplication.crmapplication.service;

import com.crmapplication.crmapplication.entity.Lead;

import java.util.List;

public interface LeadService {

    public Lead saveLead(Lead lead);

    public Lead getLeadById(long id);

    public void deleteOneLead(long id);

    public List<Lead> listAll();
}
