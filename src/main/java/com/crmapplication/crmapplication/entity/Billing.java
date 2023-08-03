package com.crmapplication.crmapplication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "billings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Billing {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@Column(name = "first_name", length =45, nullable = false )
		private String firstName;

		@Column(name = "last_name", length =45,nullable = false)
		private String lastName;

		@Column(name = "email", length =128, nullable = false, unique= true)
		private String email;

		@Column(name = "mobile", length =10, nullable = false, unique= true)
		private long mobile;

		@Column(name="product_name")
		private String productName;

		@Column(name="amount")
		private int amount;



		
}
