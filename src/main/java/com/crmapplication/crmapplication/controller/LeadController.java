package com.crmapplication.crmapplication.controller;

import com.crmapplication.crmapplication.entity.Lead;
import com.crmapplication.crmapplication.service.impl.LeadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lead")
public class LeadController {

    Logger logger = LoggerFactory.getLogger(LeadController.class);

    @Autowired
    private LeadServiceImpl leadService;


    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody  Lead lead){
        logger.info("creating with with id:{}", lead.getId());
        Lead savelead = leadService.saveLead(lead);
        return  new ResponseEntity<>(savelead, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Lead>> getLeads(){
        logger.info("getting all leads");
        List<Lead> leads = leadService.listAll();
        return  new ResponseEntity<>(leads,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLead(@PathVariable long id){
        logger.info("getting lead with id:{}", id);
        Lead lead = leadService.getLeadById(id);
    return  new ResponseEntity<>(lead,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLead(@PathVariable  long id){
        logger.info("deleting lead with id:{}", id);
        leadService.getLeadById(id);
        return new ResponseEntity<>("Lead deleted successfully", HttpStatus.NO_CONTENT);
    }



}
