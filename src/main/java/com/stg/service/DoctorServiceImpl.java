package com.stg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.Appointment;
import com.stg.entity.AppointmentStatus;
import com.stg.entity.Availability;
import com.stg.entity.Doctor;
import com.stg.entity.Patient;
import com.stg.entity.Receptionist;
import com.stg.exception.HealthException;
import com.stg.repository.AppointmentRepository;
import com.stg.repository.DoctorRepository;
import com.stg.repository.PatientRepository;

/**
 * @author bhuvaneshkumarg
 *
 */
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Doctor readDoctor(int doctorId) throws HealthException {

		if (String.valueOf(doctorId).length() > 0) {
			Optional<Doctor> optional = doctorRepository.findById(doctorId);
			Doctor doctor = optional.get();
			return doctor;
		} else {
			throw new HealthException("Enter the Correct Id");
		}

	}

	@Override
	public List<Appointment> readAppointment(int doctorId) throws HealthException {

		List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);

		if (appointments.isEmpty()) {
			throw new HealthException("No Appointments");
		}

		return appointments;

	}

	@Override
	public List<Appointment> readAppointmentByDate(LocalDate appointmentDate, int doctorId) throws HealthException {

		if (!(String.valueOf(doctorId).length() > 0)) {
			throw new HealthException("Enter the Correct Doctor Id");
		}

		List<Appointment> appointments = appointmentRepository.findAll();

		List<Appointment> list = new ArrayList<Appointment>();

		if (appointments.isEmpty()) {
			throw new HealthException("No Appointments");
		}

		for (Appointment appointment : appointments) {

			if (appointment.getDoctorId() == doctorId
					&& appointment.getAppointmentDate().compareTo(appointmentDate) == 0) {
				list.add(appointment);
			}
		}

		if (list.isEmpty()) {
			throw new HealthException("No Appointments in This Date");
		}

		return list;
	}

	@Override
	public Doctor doctorlogin(String doctorName) throws HealthException {

		Doctor doctor1 = new Doctor();

		List<Doctor> doctor = doctorRepository.findByDoctorName(doctorName);

		if (doctor.isEmpty()) {
			throw new HealthException("No Doctor Found");
		}

		for (Doctor doctor2 : doctor) {

			if (doctor2.getDoctorName().equalsIgnoreCase(doctorName)) {
				doctor1 = doctor2;

			}
		}

		return doctor1;
	}

	@Override
	public List<Appointment> doctorAppointment(int doctorId) throws HealthException {

		List<Appointment> appointment = appointmentRepository.findByDoctorId(doctorId);

		if (appointment.isEmpty()) {
			throw new HealthException("No Appointment found");
		}

		return appointment;
	}

	@Override
	public List<Appointment> allAppointment() throws HealthException {

		List<Appointment> appointments = appointmentRepository.findAll();

		if (appointments.isEmpty()) {
			throw new HealthException("No Appointments Found");
		}
		return appointments;
	}

	@Override
	public String updateAvailability(int doctorId, Availability availability) throws HealthException {

		Doctor doctor = doctorRepository.findByDoctorId(doctorId);

		if (doctor == null) {
			throw new HealthException("No Doctor Found");
		}

		doctor.setAvailability(availability);
		doctorRepository.save(doctor);

		return "\"Updated Successfully\"";
	}

	@Override
	public String updateAppointmentByDoctor(int appointmentId, Appointment appointment) throws HealthException {

		Appointment appointment2 = appointmentRepository.findById(appointmentId).get();

		appointment2.setAppointmentStatus(appointment.getAppointmentStatus());

		appointmentRepository.save(appointment2);

		return "\"Updated Successfully\"";

	}

	@Override
	public String updatePassword(int doctorId, String password) throws HealthException {

		Doctor doctor = doctorRepository.findByDoctorId(doctorId);

		if (doctor == null) {
			throw new HealthException("No Doctor Found");
		}

		doctor.setDoctorPassword(password);

		doctorRepository.save(doctor);

		return "\"Updated Successfully\"";
	}

	@Override
	public String updatePassword(int doctorId, String Newpassword, String oldPassword) throws HealthException {

		Doctor doctor = doctorRepository.findByDoctorId(doctorId);

		if (doctor == null) {
			throw new HealthException("No Receptionist found");
		}

		if (doctor.getDoctorPassword().equals(oldPassword) && (!doctor.getDoctorPassword().equals(Newpassword))) {

			doctor.setDoctorPassword(Newpassword);

			doctorRepository.save(doctor);

			return "\"Updated Successfully\"";
		} else {
			throw new HealthException("Already Password Exists");
		}

	}

	@Override
	public List<Patient> listOfPatientByDoctor(int doctorId) throws HealthException {

//		List<Patient> patients =  patientRepository.findAll();
//		
//		
//		
//		
//		List<Patient> list = new ArrayList<Patient>();
//		
//		
//		for (Patient patient : patients) {
//			
//			for (Doctor doc : patient.getReceptionist().getHospital().getDoctors()) {
//				
//				if(doc.getDoctorId()==doctorId) {
//					list.add(patient);
//				}
//				
//			}
//		}

		List<Appointment> list = appointmentRepository.findAll();

		List<Patient> patients = new ArrayList<Patient>();

		for (Appointment appointment : list) {

			if (appointment.getDoctorId() == doctorId) {
				patients.add(appointment.getPatient());
			}

		}

		return patients;
	}

	@Override
	public List<Appointment> activeAppointmentsByDoctor(int doctorId) throws HealthException {
		
	 List<Appointment> appointments = 	appointmentRepository.findAll();
	 
	 List<Appointment> list = new ArrayList<Appointment>();
 	 
	 
	 for (Appointment appointment : appointments) {
		 
		 if(appointment.getDoctorId()==doctorId && appointment.getAppointmentStatus().equals(AppointmentStatus.ACTIVE)) {
			 list.add(appointment);
		 }
		
	}
	 
	 
	 if(list.isEmpty()) {
		 throw new HealthException("No Appointments");
	 }
		
		
		return list;
	}

	@Override
	public Doctor loginByDoctorPhoneNumber(long doctorPhoneNumber) throws HealthException {
		
		Doctor doctor = doctorRepository.findByDoctorPhoneNumber(doctorPhoneNumber);
		
		if(doctor==null) {
			throw new HealthException("No Doctor Found");
		}
		
		return doctor;
	}

}
