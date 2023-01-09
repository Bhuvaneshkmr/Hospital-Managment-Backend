package com.stg.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

/**
 * @author bhuvaneshkumarg
 *
 */
public interface ReceptionistService {

	public abstract Receptionist receptionlogin(String receptionName) throws HealthException;

	public abstract Receptionist receptionloginByPhoneNumber(long receptionPhoneNumber) throws HealthException;

	public abstract String createPatient(Patient patientint, int receptionistId) throws HealthException;

	public abstract List<Patient> readPatientByName(String patientName) throws HealthException;

	public abstract List<Appointment> readByPatient(String patientName) throws HealthException;

	public abstract String createBill(int appointmentNo, AmountStatus amountStatus, int amount,
			AppointmentStatus appointmentStatus) throws HealthException;

	public abstract String upateAppointmentDateAndTime(int appointmentNo, LocalDate appointmentDate,
			LocalTime appointmentTime) throws HealthException;

	public abstract String updateAppointment(int appointmentId, Appointment appointment) throws HealthException;

	public abstract Appointment getAppointmentById(int appointmentId) throws HealthException;

	public abstract String upateAppointmentDateTime(int appointmentNo, Appointment appointment) throws HealthException;

	public abstract String updateAppointmentStatus(int appointmentNo, AppointmentStatus appointmentStatus)
			throws HealthException;

	public abstract Appointment getAppointment(int appointmentNo) throws HealthException;

	public abstract List<Doctor> readDoctorByName(String doctorName) throws HealthException;

	public abstract List<Patient> readAllPatient() throws HealthException;

	public abstract List<Doctor> readAllDoctor() throws HealthException;

	public abstract List<Patient> readPatientByDoctor(int doctorId) throws HealthException;

	public abstract List<Appointment> allAppointment() throws HealthException;

	public abstract Receptionist readReceptionistById(int ReceptionistId) throws HealthException;

	public abstract List<Bill> allBill() throws HealthException;

	public abstract List<Doctor> appoint(String reason, String speciality, LocalTime appoinmentTime,
			LocalDate appointmentDate) throws HealthException;

	public abstract String saveAppoint(Appointment appointment, int patientId, int receptionistId)
			throws HealthException;

	public abstract Patient getPatientById(int patientId) throws HealthException;

	public abstract String updatePassword(int receptionistId, String Newpassword, String oldPassword)
			throws HealthException;

	public abstract List<Appointment> appointmentsForToday() throws HealthException;

	public abstract List<Appointment> activeAppointments() throws HealthException;

	public abstract long patientCount() throws HealthException;

	public abstract long activeAppointmentsCount() throws HealthException;

	public abstract double totalBillPercentage() throws HealthException;

	public abstract int totalVistForTodatCount() throws HealthException;

	public abstract long doctorsCount() throws HealthException;

	public abstract long billCount() throws HealthException;

	public abstract long allAppointmentsCount() throws HealthException;

	public abstract List<String> getSpeciality() throws HealthException;

}
