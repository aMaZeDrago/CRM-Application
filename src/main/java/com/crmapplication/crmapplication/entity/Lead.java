package com.crmapplication.crmapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leads")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name",nullable = false, length =45 )
    private String firstName;

    @Column(name = "last_name",nullable = false, length =45 )
    private String lastName;

    @Column(name = "email", length =128, nullable = false, unique= true)
    private String email;

    @Column(name = "mobile", length =10, nullable = false, unique= true)
    private long mobile;

    @Column(name = "lead_source")
    private String leadSource;

}
