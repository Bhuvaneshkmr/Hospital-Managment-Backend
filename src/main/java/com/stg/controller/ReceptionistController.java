package com.stg.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.loader.custom.ConstructorResultColumnProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.AmountStatus;
import com.stg.entity.Appointment;
import com.stg.entity.AppointmentStatus;
import com.stg.entity.Bill;
import com.stg.entity.Doctor;
import com.stg.entity.Patient;
import com.stg.entity.Receptionist;
import com.stg.entity.Speciality;
import com.stg.entity.Symptoms;
import com.stg.exception.HealthException;
import com.stg.service.ReceptionistService;

/**
 * @author bhuvaneshkumarg
 *
 */
@RestController
@RequestMapping(value = "receptionist")
@CrossOrigin(origins = "*")
public class ReceptionistController {

	@Autowired
	private ReceptionistService receptionistService;

	@PostMapping(value = "CreatePatient/{receptionistId}")
	public ResponseEntity<String> createPatient(@RequestBody Patient patient, @PathVariable int receptionistId)
			throws HealthException {

		String message = receptionistService.createPatient(patient, receptionistId);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	

	@PostMapping(value = "GenerateBill/{appointmentNo}/{amountStatus}/{amount}/{appointmentStatus}")
	public ResponseEntity<String> generateBill(@PathVariable int appointmentNo, @PathVariable AmountStatus amountStatus,
			@PathVariable int amount, @PathVariable AppointmentStatus appointmentStatus) throws HealthException {

		String message = receptionistService.createBill(appointmentNo, amountStatus, amount, appointmentStatus);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "ReadPatient/{patientName}")
	public ResponseEntity<List<Patient>> readByPatientName(@PathVariable String patientName) throws HealthException {

		List<Patient> patients = receptionistService.readPatientByName(patientName);

		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}

	@GetMapping(value = "readByPatientApp/{patientName}")
	public ResponseEntity<List<Appointment>> readByPatient(String patientName) throws HealthException {

		List<Appointment> appointments = receptionistService.readByPatient(patientName);

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@GetMapping(value = "readDoctor/{doctorName}")
	public ResponseEntity<List<Doctor>> readDoctorByName(@PathVariable String doctorName) throws HealthException {

		List<Doctor> doctors = receptionistService.readDoctorByName(doctorName);

		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);

	}

	@PutMapping(value = "upateAppointmentDateAndTime/{appointmentNo}/{appointmentDate}/{appointmentTime}")
	public ResponseEntity<String> upateAppointmentDateAndTime(@PathVariable int appointmentNo,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate appointmentDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appointmentTime)
			throws HealthException {
		String message = receptionistService.upateAppointmentDateAndTime(appointmentNo, appointmentDate,
				appointmentTime);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PutMapping(value = "updateAppointmentStatus/{appointmentNo}/{appointmentStatus}")
	public ResponseEntity<String> updateAppointmentStatus(@PathVariable int appointmentNo,
			@PathVariable AppointmentStatus appointmentStatus) throws HealthException {

		String message = receptionistService.updateAppointmentStatus(appointmentNo, appointmentStatus);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "ReadAllPatient")
	public ResponseEntity<List<Patient>> readAllPatient() throws HealthException {

		List<Patient> patients = receptionistService.readAllPatient();

		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}

	@GetMapping(value = "ReadAllDoctor")
	public ResponseEntity<List<Doctor>> readAllDoctor() throws HealthException {

		List<Doctor> doctors = receptionistService.readAllDoctor();

//		
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}

	@GetMapping(value = "readAllAppointment")
	public ResponseEntity<List<Appointment>> readAllAppointment() throws HealthException {

		List<Appointment> appointments = receptionistService.allAppointment();

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@GetMapping(value = "readPatientByDoctor/{doctorId}")
	public ResponseEntity<List<Patient>> readPatientByDoctor(@PathVariable int doctorId) throws HealthException {

		List<Patient> patients = receptionistService.readPatientByDoctor(doctorId);

		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);

	}

	@PutMapping(value = "updateAppointmentDateTime/{appointmentNo}")
	public ResponseEntity<String> updateAppointmentDateTime(@PathVariable int appointmentNo,
			@RequestBody Appointment appointment) throws HealthException {

		String message = receptionistService.upateAppointmentDateTime(appointmentNo, appointment);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "getAppointment/{appointmentNo}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable int appointmentNo) throws HealthException {

		Appointment appointment = receptionistService.getAppointment(appointmentNo);

		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@PutMapping(value = "updateappointment/{appointmentNo}")
	public ResponseEntity<String> updateAppointment(@PathVariable int appointmentNo,
			@RequestBody Appointment appointment) throws HealthException {

		String message = receptionistService.updateAppointment(appointmentNo, appointment);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "receptionistById/{receptionistId}")
	public ResponseEntity<Receptionist> receptionistById(@PathVariable int receptionistId) throws HealthException {

		Receptionist receptionist = receptionistService.readReceptionistById(receptionistId);

		return new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);

	}

//public ResponseEntity<String> getAppointment(@PathVariable int appointmentNo) throws HealthException{
//		
//		Appointment appointment  =  receptionistService.getAppointment(appointmentNo);
//		return new ResponseEntity<String>(appointment, HttpStatus.OK);
//		
//	}

	@GetMapping(value = "receptionlogin/{receptionName}")
	public ResponseEntity<Receptionist> receptionlogin(@PathVariable String receptionName) throws HealthException {

		Receptionist receptionist = receptionistService.receptionlogin(receptionName);

		return new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);

	}

	@GetMapping(value = "receptionloginByPhoneNumber/{PhoneNumber}")
	public ResponseEntity<Receptionist> receptionloginByPhoneNumber(@PathVariable long PhoneNumber)
			throws HealthException {

		Receptionist receptionist = receptionistService.receptionloginByPhoneNumber(PhoneNumber);

		return new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);

	}

	@GetMapping(value = "allBill")
	public ResponseEntity<List<Bill>> allBill() throws HealthException {

		List<Bill> bills = receptionistService.allBill();

		return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);

	}

	@GetMapping(value = "appoint/{reason}/{speciality}/{appoinmentTime}/{appointmentDate}")
	public ResponseEntity<List<Doctor>> appoint(@PathVariable String reason, @PathVariable String speciality,
			@DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @PathVariable LocalTime appoinmentTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate appointmentDate) throws HealthException {

		List<Doctor> doctors = receptionistService.appoint(reason, speciality, appoinmentTime, appointmentDate);
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);

	}

	@PostMapping(value = "saveAppoint/{patientId}/{receptionistId}")
	public ResponseEntity<String> saveAppoint(@RequestBody Appointment appointment, @PathVariable int patientId,
			@PathVariable int receptionistId) throws HealthException {

		String message = receptionistService.saveAppoint(appointment, patientId, receptionistId);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "getPatientById/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) throws HealthException {

		Patient patient = receptionistService.getPatientById(patientId);

		return new ResponseEntity<Patient>(patient, HttpStatus.OK);

	}

	@PutMapping(value = "updatePassword/{receptionistId}/{Newpassword}/{oldPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable int receptionistId, @PathVariable String Newpassword,
			@PathVariable String oldPassword) throws HealthException {

		String message = receptionistService.updatePassword(receptionistId, Newpassword, oldPassword);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@GetMapping(value = "appointmentsForToday")
	public ResponseEntity<List<Appointment>> appointmentsForToday() throws HealthException {

		List<Appointment> appointments = receptionistService.appointmentsForToday();

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@GetMapping(value = "activeAppointments")
	public ResponseEntity<List<Appointment>> activeAppointments() throws HealthException {

		List<Appointment> appointments = receptionistService.activeAppointments();

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@GetMapping(value = "patientCount")
	public ResponseEntity<Long> patientCount() throws HealthException {

		long patientCount = receptionistService.patientCount();

		return new ResponseEntity<Long>(patientCount, HttpStatus.OK);

	}

	@GetMapping(value = "activeAppointmentsCount")
	public ResponseEntity<Long> activeAppointmentsCount() throws HealthException {

		long activeCount = receptionistService.activeAppointmentsCount();

		return new ResponseEntity<Long>(activeCount, HttpStatus.OK);

	}

	@GetMapping(value = "totalBillPercentage")
	public ResponseEntity<Double> totalBillPercentage() throws HealthException {

		double totalBillPercentage = receptionistService.totalBillPercentage();

		return new ResponseEntity<Double>(totalBillPercentage, HttpStatus.OK);

	}

	@GetMapping(value = "totalVistForTodatCount")
	public ResponseEntity<Integer> totalVistForTodatCount() throws HealthException {

		int totalVistForTodatCount = receptionistService.totalVistForTodatCount();

		return new ResponseEntity<Integer>(totalVistForTodatCount, HttpStatus.OK);

	}

	@GetMapping(value = "doctorsCount")
	public ResponseEntity<Long> doctorsCount() throws HealthException {

		long doctorsCount = receptionistService.doctorsCount();

		return new ResponseEntity<Long>(doctorsCount, HttpStatus.OK);

	}

	@GetMapping(value = "allAppointmentsCount")
	public ResponseEntity<Long> allAppointmentsCount() throws HealthException {

		long allAppointmentsCount = receptionistService.allAppointmentsCount();

		return new ResponseEntity<Long>(allAppointmentsCount, HttpStatus.OK);

	}

	@GetMapping(value = "billCount")
	public ResponseEntity<Long> billCount() throws HealthException {

		long billCount = receptionistService.billCount();

		return new ResponseEntity<Long>(billCount, HttpStatus.OK);

	}

	
	
	
	@GetMapping(value = "getSpeciality")
	public ResponseEntity<List<String>> getSpeciality() throws HealthException {

		List<String> Speciality = receptionistService.getSpeciality();

		return new ResponseEntity<List<String>>(Speciality, HttpStatus.OK);

	}

}
