package com.stg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Appointment;
import com.stg.entity.Bill;
import com.stg.entity.Patient;
import com.stg.exception.HealthException;
import com.stg.service.PatientService;

/**
 * @author bhuvaneshkumarg
 *
 */
@RestController
@RequestMapping(value = "patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping(value = "readpatient/{patientId}")
	public ResponseEntity<Patient> readByPatientId(@PathVariable int patientId) throws HealthException {
		Patient patient = null;

		patient = patientService.readByPatientId(patientId);

		return new ResponseEntity<Patient>(patient, HttpStatus.FOUND);

	}

	@GetMapping(value = "readappointment/{patientId}")
	public ResponseEntity<List<Appointment>> readAppointment(@PathVariable int patientId) throws HealthException {

		List<Appointment> appointment = patientService.patientAppointment(patientId);

		return new ResponseEntity<List<Appointment>>(appointment, HttpStatus.FOUND);

	}

	@GetMapping(value = "patientbill/{patientId}")
	public ResponseEntity<List<Bill>> patientBill(@PathVariable int patientId) throws HealthException {

		List<Bill> bill = patientService.patientBill(patientId);

		return new ResponseEntity<List<Bill>>(bill, HttpStatus.FOUND);

	}

	@PutMapping(value = "updatePatientPhoneNo/{patientId}/{patientPhoneNo}")
	public ResponseEntity<String> updatePatientPhoneNo(@PathVariable int patientId, @PathVariable long patientPhoneNo)
			throws HealthException {

		String message = patientService.updatePatientDetails(patientId, patientPhoneNo);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

}
