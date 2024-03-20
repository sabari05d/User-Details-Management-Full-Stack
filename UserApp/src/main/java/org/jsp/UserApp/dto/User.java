package org.jsp.UserApp.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name, password, image_url;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private int age;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Pancard pancard;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private AadharCard aadharcard;

}
