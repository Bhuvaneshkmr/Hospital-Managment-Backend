package com.stg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Bill;
import com.stg.entity.Gender;
import com.stg.entity.Hospital;
import com.stg.entity.Patient;
import com.stg.entity.Qualification;
import com.stg.entity.Receptionist;
import com.stg.entity.Speciality;
import com.stg.exception.HealthException;

import com.stg.service.HospitalService;

/**
 * @author bhuvaneshkumarg
 *
 */
@RestController
@RequestMapping(value = "Hospital")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping(value = "CreateDoctor/{doctorName}/{doctorPassword}/{speciality}/{doctorAge}/{doctorGender}/{doctorPhoneNumber}/{doctorQualification}/{yearOfPractice}/{joiningDate}/{hospitalId}")
	public ResponseEntity<String> createDoctor(@PathVariable String doctorName, @PathVariable String doctorPassword,
			@PathVariable String speciality, @PathVariable int doctorAge, @PathVariable Gender doctorGender,
			@PathVariable long doctorPhoneNumber, @PathVariable Qualification doctorQualification,
			@PathVariable int yearOfPractice,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate joiningDate, @PathVariable int hospitalId)
			throws HealthException {

		String message = hospitalService.createDoctor(doctorName, doctorPassword, speciality, doctorAge, doctorGender,
				doctorPhoneNumber, doctorQualification, yearOfPractice, joiningDate, hospitalId);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@PostMapping(value = "CreateReceptionist/{id}")
	public ResponseEntity<String> createReceptionist(@RequestBody Receptionist receptionist, @PathVariable int id)
			throws HealthException {

		String message = hospitalService.createReceptionist(receptionist, id);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@PostMapping(value = "createAdmin")
	public ResponseEntity<String> createAdmin(@RequestBody Hospital hospital) throws HealthException {

		String message = hospitalService.createAdmin(hospital);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@DeleteMapping(value = "DeleteDoctor/{doctorId}")
	public ResponseEntity<String> deleteDoctor(@PathVariable int doctorId) throws HealthException {

		String message = hospitalService.deleteDoctor(doctorId);

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@DeleteMapping(value = "DeleteReceptionist/{receptionistId}")
	public ResponseEntity<String> deleteReceptionist(@PathVariable int receptionistId) throws HealthException {
		String message = hospitalService.deleteReceptionist(receptionistId);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping(value = "readPatientByReceptionist/{receptionistId}")
	public ResponseEntity<List<Patient>> readPatientByReceptionist(@PathVariable int receptionistId)
			throws HealthException {

		List<Patient> patients = hospitalService.readPatientByReceptionist(receptionistId);

		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);

	}

	@GetMapping(value = "adminLogin/{userName}")
	public ResponseEntity<Hospital> adminLogin(@PathVariable String userName) throws HealthException {

		Hospital hospital = hospitalService.adminLogin(userName);

		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);

	}
	
	@GetMapping(value = "adminLoginByPhoneNumber/{phoneNo}")
	public ResponseEntity<Hospital> adminLoginByPhoneNumber(@PathVariable long phoneNo) throws HealthException {

		Hospital hospital = hospitalService.adminLoginByPhoneNumber(phoneNo);

		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);

	}
	

	@GetMapping(value = "adminById/{adminId}")
	public ResponseEntity<Hospital> adminById(@PathVariable int adminId) throws HealthException {

		Hospital hospital = hospitalService.adminById(adminId);

		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);

	}

	@GetMapping(value = "readReceptionist")
	public ResponseEntity<List<Receptionist>> readReceptionist() throws HealthException {

		List<Receptionist> receptionists = hospitalService.readAllReceptionist();

		return new ResponseEntity<List<Receptionist>>(receptionists, HttpStatus.OK);

	}

	@GetMapping(value = "listOfPatientByReceptionist/{receptionistId}")
	public ResponseEntity<List<Patient>> listOfPatientByReceptionist(@PathVariable int receptionistId)
			throws HealthException {

		List<Patient> list = hospitalService.listOfPatientByReceptionist(receptionistId);

		return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);

	}

	@GetMapping(value = "receptionistCount")
	public ResponseEntity<Long> receptionistCount() throws HealthException {

		long receptionistCount = hospitalService.receptionistCount();

		return new ResponseEntity<Long>(receptionistCount, HttpStatus.OK);

	}

	@GetMapping(value = "totalBillPercentageForAdmin")
	public ResponseEntity<Double> totalBillPercentageForAdmin() throws HealthException {

		double totalBillPercentageForAdmin = hospitalService.totalBillPercentageForAdmin();

		return new ResponseEntity<Double>(totalBillPercentageForAdmin, HttpStatus.OK);

	}

	@GetMapping(value = "totalBill")
	public ResponseEntity<List<Bill>> totalBill() throws HealthException {

		List<Bill> totalBill = hospitalService.totalBill();

		return new ResponseEntity<List<Bill>>(totalBill, HttpStatus.OK);

	}
	
	@PutMapping(value = "updatePassword/{adminId}/{newPassword}/{oldPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable int adminId,@PathVariable String newPassword ,@PathVariable String oldPassword) throws HealthException{
		
		String message =  hospitalService.updatePassword(adminId, newPassword, oldPassword);
		
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "mobileNoExist/{phoneNo}")
	public ResponseEntity<Boolean> mobileNoExist(@PathVariable long phoneNo) throws HealthException {

		Boolean  res = hospitalService.mobileNoExist(phoneNo);

		return new ResponseEntity<Boolean>(res, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "mobileNoExistforDoctor/{phoneNo}")
	public ResponseEntity<Boolean> mobileNoExistforDoctor(@PathVariable long phoneNo) throws HealthException {

		Boolean  res = hospitalService.mobileNoExistforDoctor(phoneNo);

		return new ResponseEntity<Boolean>(res, HttpStatus.OK);

	}


}
