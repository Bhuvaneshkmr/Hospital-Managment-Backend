package com.stg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSpeciality {
	
	@Id
	@Column(name = "speciality_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int specialityId;

	@Column(name = "speciality")
	private String speciality;
	
}
