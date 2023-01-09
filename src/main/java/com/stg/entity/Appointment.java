package com.stg.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhuvaneshkumarg
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

	@Id
	@Column(name = "appointment_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int appointmentNo;

	@Column(name = "patient_id", nullable = false)
	private int patientId;

	@Column(name = "doctor_id")
	private int doctorId;
	
	@Column(name = "patient_name", length = 30)
	private String patientName;

	@Column(name = "doctor_name", length = 30)
	private String doctorName;

	@Column(name = "reason", length = 20)
	private String reason;

	
	@Column(name = "appoinment_time")
//	@JsonFormat(pattern = "hh:mm")
	private LocalTime appoinmentTime;

	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "appointment_date")
	private LocalDate appointmentDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "appointment_status")
	private AppointmentStatus appointmentStatus;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "receptionist_fk", referencedColumnName = "receptionist_id")
	@JsonBackReference(value = "receptionist_fk")
	private Receptionist receptionist;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appointment")
	private Bill bill;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "patientfk", referencedColumnName = "patient_id")
	@JsonBackReference(value = "patient_id_fk")
	private Patient patient;

}
