package org.jsp.UserApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class AadharCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name, image_url;
	@Column(nullable = false, unique = true)
	private long number;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false)
	private String address, city, state;
	@Column(nullable = false)
	private long pincode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	@JsonIgnore
	private User user;

}
