package com.stg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Appointment;
import com.stg.entity.Availability;
import com.stg.entity.Doctor;
import com.stg.entity.Patient;
import com.stg.entity.Receptionist;
import com.stg.exception.HealthException;
import com.stg.service.DoctorService;

/**
 * @author bhuvaneshkumarg
 *
 */
@RestController
@RequestMapping(value = "doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping(value = "readdoctorbyid/{doctor_id}")
	public ResponseEntity<Doctor> readDoctor(@PathVariable int doctor_id) throws HealthException {

		Doctor doctor = null;

		doctor = doctorService.readDoctor(doctor_id);

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);

	}
	
	@GetMapping(value = "receptionloginByPhoneNumber/{PhoneNumber}")
	public ResponseEntity<Doctor> receptionloginByPhoneNumber(@PathVariable long PhoneNumber) throws HealthException{
		
		Doctor doctor =  doctorService.loginByDoctorPhoneNumber(PhoneNumber);
		
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		
	}

	@GetMapping(value = "readappointment/{doctorId}")
	public ResponseEntity<List<Appointment>> readAppointment(@PathVariable int doctorId) throws HealthException {

		List<Appointment> appointments = doctorService.readAppointment(doctorId);

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@GetMapping(value = "readappointmentbydate/{doctorId}/{appointmentDate}")
	public ResponseEntity<List<Appointment>> readAppointmentByDate(@PathVariable int doctorId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate appointmentDate) throws HealthException {

		List<Appointment> appointments = doctorService.readAppointmentByDate(appointmentDate, doctorId);

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}
	@GetMapping(value = "doctorlogin/{doctorName}")
	public ResponseEntity<Doctor> doctorlogin(@PathVariable String doctorName) throws HealthException{
		
		Doctor doctor =  doctorService.doctorlogin(doctorName);
		
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "doctorAppointment/{doctorId}")
	public ResponseEntity<List<Appointment>> doctorAppointment(@PathVariable int doctorId) throws HealthException{
		
		List<Appointment> appointments =  doctorService.doctorAppointment(doctorId);
		
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "allAppointment")
	public ResponseEntity<List<Appointment>> allAppointment() throws HealthException{
		
		List<Appointment> appointments = doctorService.allAppointment();
		
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	@PutMapping(value = "updateAvailability/{doctorId}/{availability}")
	public ResponseEntity<String> updateAvailability(@PathVariable int doctorId,@PathVariable Availability availability) throws HealthException{
		
		String message =  doctorService.updateAvailability(doctorId, availability);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "updateappointmentByDoctor/{appointmentNo}")
	public ResponseEntity<String> updateAppointment(@PathVariable int appointmentNo ,@RequestBody Appointment appointment) throws HealthException{
		
		String  message =  doctorService.updateAppointmentByDoctor(appointmentNo, appointment);
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "updatePassword/{doctorId}/{password}")
	public ResponseEntity<String> updatePassword(@PathVariable int doctorId,@PathVariable  String password) throws HealthException{
		
		String message =  doctorService.updatePassword(doctorId, password);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	
	@PutMapping(value = "updatePassword/{doctorId}/{Newpassword}/{oldPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable int doctorId , @PathVariable String Newpassword,@PathVariable String oldPassword) throws HealthException{
		
		String  message =  doctorService.updatePassword(doctorId, Newpassword,oldPassword);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	
	
	@GetMapping(value = "listOfPatientByDoctor/{doctorId}")
	public ResponseEntity<List<Patient>> listOfPatientByDoctor(@PathVariable int doctorId) throws HealthException{
		
		List<Patient> patients = doctorService.listOfPatientByDoctor(doctorId);
		
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value = "activeAppointmentsByDoctor/{doctorId}")
	public ResponseEntity<List<Appointment>> activeAppointmentsByDoctor(@PathVariable int doctorId) throws HealthException{
		
		List<Appointment> activeAppointmentsByDoctor = doctorService.activeAppointmentsByDoctor(doctorId);
		
		return new ResponseEntity<List<Appointment>>(activeAppointmentsByDoctor, HttpStatus.OK);
	}
	
	

	
}
