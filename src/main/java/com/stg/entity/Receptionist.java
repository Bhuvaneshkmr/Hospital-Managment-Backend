package com.stg.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receptionist {

	@Id
	@Column(name = "receptionist_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int receptionistId;
	
	@Column(name = "receptionist_user_name", length = 30)
	private String receptionistUserName;
	
	@Column(name = "receptionist_gender")
	@Enumerated(EnumType.STRING)
	private Gender receptionistGender;
	
	@Column(name = "receptionist_password", length = 30)
	private String receptionistPassword;
	
	@Column(name = "receptionist_phone_no", nullable = false, unique = true)
	private long receptionistPhoneNo;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "joining_date")
	private LocalDate joiningDate;

	@JsonBackReference(value = "receptionistfk")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_fk", referencedColumnName = "id")
	private Hospital hospital;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receptionist")
	private List<Patient> patients;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receptionist")
	List<Appointment> appointments;

}
