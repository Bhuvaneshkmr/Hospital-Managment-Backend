package com.stg.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bhuvaneshkumarg
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient  {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int patientId;

	@Column(name = "patient_name", length = 30, nullable = false)
	private String patientName;

	@Column(name = "patient_phone_no", nullable = false, unique = true)
	private long patientPhoneNo;

	@Column(name = "patient_address")
	private String patientAddress;

	@Column(name = "patient_age", nullable = false)
	private int patientAge;

	@Column(name = "patient_gender")
	@Enumerated(EnumType.STRING)
	private Gender patientGender;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn(name = "receptionist_fk", referencedColumnName = "receptionist_id")
	@JsonBackReference(value = "patientfk")
	private Receptionist receptionist;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,  mappedBy = "patient")
	private List<Appointment> appointment;
	
	
	
}
