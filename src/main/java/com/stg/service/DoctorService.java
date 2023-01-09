package com.stg.service;

import java.time.LocalDate;
import java.util.List;

import com.stg.entity.Appointment;
import com.stg.entity.Availability;
import com.stg.entity.Doctor;
import com.stg.entity.Patient;
import com.stg.entity.Receptionist;
import com.stg.exception.HealthException;

/**
 * @author bhuvaneshkumarg
 *
 */
public interface DoctorService {

	public abstract Doctor readDoctor(int doctorId) throws HealthException;

	public abstract Doctor loginByDoctorPhoneNumber(long doctorPhoneNumber) throws HealthException;

	public abstract List<Appointment> readAppointment(int doctorId) throws HealthException;

	public abstract List<Appointment> readAppointmentByDate(LocalDate appointmentDate, int doctorId)
			throws HealthException;

	public abstract String updateAppointmentByDoctor(int appointmentId, Appointment appointment) throws HealthException;

	public abstract Doctor doctorlogin(String doctorName) throws HealthException;

	public abstract List<Appointment> doctorAppointment(int doctorId) throws HealthException;

	public abstract List<Appointment> allAppointment() throws HealthException;

	public abstract String updateAvailability(int doctorId, Availability availability) throws HealthException;

	public abstract String updatePassword(int doctorId, String password) throws HealthException;

	public abstract String updatePassword(int receptionistId, String Newpassword, String oldPassword)
			throws HealthException;

	public abstract List<Patient> listOfPatientByDoctor(int doctorId) throws HealthException;

	public abstract List<Appointment> activeAppointmentsByDoctor(int doctorId) throws HealthException;
	
	

}
