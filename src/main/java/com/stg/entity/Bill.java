package com.stg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Bill {

	@Id
	@Column(name = "bill_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int billId;

	@Column(name = "patient_id", nullable = false)
	private int patientId;
	
	
	@Column(name = "patient_name", nullable = false)
	private String patientName;
	

	@Column(name = "doctor_id", nullable = false)
	private int doctorId;
	
	@Column(name = "doctor_name", nullable = false)
	private String doctorName;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@Column(name = "bill_date_and_time", nullable = false)
	private LocalDateTime billDateAndTime;

	@Column(name = "amount")
	private double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "amount_status")
	private AmountStatus amountStatus;

	@JsonBackReference(value = "bill")
	@OneToOne
	@JoinColumn(name = "appointment_fk", referencedColumnName = "appointment_no")
	private Appointment appointment;

}
