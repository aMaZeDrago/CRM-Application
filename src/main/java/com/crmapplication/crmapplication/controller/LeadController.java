package com.crmapplication.crmapplication.controller;

import com.crmapplication.crmapplication.entity.Lead;
import com.crmapplication.crmapplication.service.impl.LeadServiceImpl;
import com.crmapplication.crmapplication.util.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

 @GetMapping("/")
    public String getStarted(){
        return "Welcome to the App";
    }

    @GetMapping("/filtered-paginated")
    public ResponseEntity<PageResponse> getLeadsByleadSourcePaginated(
            @RequestParam(name = "status") String leadSource, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        logger.info("getting lead by leadSource = {}",leadSource);

        Page<Lead> leads = leadService.getLeadsByStatusPaginated(leadSource, PageRequest.of(page, size));
        List<Lead> content = leads.getContent();

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(leads.getNumber());
        pageResponse.setPageSize(leads.getSize());
        pageResponse.setTotalPages(leads.getTotalPages());
        pageResponse.setTotalElements(leads.getTotalElements());
        pageResponse.setLast(leads.isLast());
        return ResponseEntity.ok(pageResponse);
    }


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
