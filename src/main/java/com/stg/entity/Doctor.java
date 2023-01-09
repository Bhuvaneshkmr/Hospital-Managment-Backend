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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

/**
 * @author bhuvaneshkumarg
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {

	@Id
	@Column(name = "doctor_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int doctorId;

	@Column(name = "doctor_name", length = 30, nullable = false)
	private String doctorName;
	
	@Column(name = "doctor_password", length = 30, nullable = false)
	private String doctorPassword;

//	@Enumerated(EnumType.STRING)
	@Column(name = "speciality")
	private String speciality;

	@Column(name = "doctor_salary")
	private double doctorSalary;
	
	@Column(name = "doctor_age")
	private int doctorAge;
	
	@Column(name = "doctor_phone_number", nullable = false , unique = true)
	private long doctorPhoneNumber;
	
	@Column(name = "doctor_gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender doctorGender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "doctor_qualification")
	private Qualification doctorQualification;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "availability")
	private Availability availability;
	
	@Column(name = "year_of_practice")
	private int yearOfPractice;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "joining_date")
	private LocalDate joiningDate;
	

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_fk", referencedColumnName = "id")
	@JsonBackReference(value = "hospitalfk")
	private Hospital hospital;
	
	
	

	

	

}
