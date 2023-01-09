package com.stg.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
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
import com.stg.repository.AppointmentRepository;
import com.stg.repository.BillRepository;
import com.stg.repository.DoctorRepository;
import com.stg.repository.PatientRepository;
import com.stg.repository.ReceptionistRepository;

/**
 * @author bhuvaneshkumarg
 *
 */
@Service
public class ReceptionistServiceImpl implements ReceptionistService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ReceptionistRepository receptionistRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private BillRepository billRepository;

	@Override
	public String createPatient(Patient patient, int receptionistId) throws HealthException {

//		if (!(String.valueOf(patient.getPatientId()).length() > 0)) {
//			throw new HealthException("\"Enter the Correct Id\"");
//		}
		if (!(patient.getPatientName().matches("[a-zA-Z]{3,31}"))) {
			throw new HealthException("\"Enter the Correct Name\"");
		}
		if (!(String.valueOf(patient.getPatientPhoneNo()).length() == 10)) {
			throw new HealthException("\"Enter the 10 Digit Number\"");
		}
		if (!(patient.getPatientAddress().length() > 0)) {
			throw new HealthException("\"Enter the Address\"");
		}
		if (!(String.valueOf(patient.getPatientAge()).length() == 2)) {
			throw new HealthException("\"Enter the Correct Age\"");
		}
//		if (!(patient.getPatientGender().matches("[M|F]{1}"))) {
//			throw new HealthException("Enter the Correct Gender");
//		}

		Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

		patient.setReceptionist(receptionist);

		patientRepository.save(patient);

		return "\"Patient Created Successfully\"";
	}

	public boolean isAvailable(int doctordId, LocalTime appointmentTime, LocalDate appointmentDate) {

		List<Appointment> appointments = appointmentRepository.findByAppointmentDate(appointmentDate);

		for (Appointment appointment : appointments) {

			if (appointment.getDoctorId() == doctordId) {
				if (appointment.getAppointmentStatus().equals(AppointmentStatus.ACTIVE)) {

					if (appointmentTime.equals(appointment.getAppoinmentTime())
							|| appointmentTime.isAfter(appointment.getAppoinmentTime())
									&& appointmentTime.isBefore(appointment.getAppoinmentTime().plusMinutes(31))) {
						return false;
					}
				}
			}
		}
		return true;

	}

	@Override
	public List<Patient> readPatientByName(String patientName) throws HealthException {

		List<Patient> patients = patientRepository.findByPatientName(patientName);

		if (patients.isEmpty()) {
			throw new HealthException("\"No Patient Found\"");
		}

		return patients;
	}

	@Override
	public String createBill(int appointmentNo, AmountStatus amountStatus, int amount,
			AppointmentStatus appointmentStatus) throws HealthException {

		Bill bill = new Bill();

		Appointment appointment = appointmentRepository.findById(appointmentNo).get();

		if (appointment == null) {
			throw new HealthException("\"No Appointment Found\"");
		}

		if (!(String.valueOf(appointmentNo).length() > 0)) {
			throw new HealthException("\"Enter the Correct No\"");
		}
//		if (!(String.valueOf(doctorId).length() > 0)) {
//			throw new HealthException("Enter the Doctor No");
//		}
//		if (!(String.valueOf(patientId).length() > 0)) {
//			throw new HealthException("Enter the Patient No");
//		}

		if (!(String.valueOf(amount).length() > 0)) {
			throw new HealthException("\"Enter the Ammount\"");
		}

		LocalDateTime dateTime = LocalDateTime.now();

		appointment.setAppointmentStatus(appointmentStatus);
		bill.setDoctorId(appointment.getDoctorId());
		bill.setAppointment(appointment);
		bill.setDoctorName(appointment.getDoctorName());
		bill.setPatientId(appointment.getPatientId());
		bill.setPatientName(appointment.getPatientName());
		bill.setAmountStatus(amountStatus);
		bill.setBillDateAndTime(dateTime);
		bill.setAmount(amount);
		billRepository.save(bill);

		return "\"Bill Generated\"";

	}

	@Override
	public List<Patient> readAllPatient() throws HealthException {

		List<Patient> patients = patientRepository.findAll();

		if (patients.isEmpty()) {
			throw new HealthException("\"No Patient Found\"");
		}
		return patients;
	}

	@Override
	public List<Doctor> readAllDoctor() throws HealthException {

		List<Doctor> doctors = doctorRepository.findAll();

		if (doctors.isEmpty()) {
			throw new HealthException("\"Doctors Not Found\"");
		}

		return doctors;
	}

	@Override
	public String upateAppointmentDateAndTime(int appointmentNo, LocalDate appointmentDate, LocalTime appointmentTime)
			throws HealthException {

		Appointment appointment = appointmentRepository.findById(appointmentNo).get();

		if (appointment != null) {

			appointment.setAppointmentDate(appointmentDate);
			appointment.setAppoinmentTime(appointmentTime);
			appointmentRepository.save(appointment);
			return "\"Appointment Updated Successfully\"";

		} else {
			throw new HealthException("\"No Appointment Found\"");
		}

	}

	@Override
	public String updateAppointmentStatus(int appointmentNo, AppointmentStatus appointmentStatus)
			throws HealthException {

		List<Appointment> appointments = appointmentRepository.findAll();

		Appointment appointment = null;

		for (Appointment appointment2 : appointments) {

			if (appointment2.getAppointmentNo() == appointmentNo) {
				appointment = appointment2;
			}
		}

		if (appointment != null) {

			appointment.setAppointmentStatus(appointmentStatus);
			appointmentRepository.save(appointment);
			return "\"Updated Successfully\"";
		} else {
			throw new HealthException("\"No Patient Id\"");
		}

	}

	@Override
	public List<Doctor> readDoctorByName(String doctorName) throws HealthException {

		List<Doctor> doctors = doctorRepository.findByDoctorName(doctorName);

		if (doctors.isEmpty()) {
			throw new HealthException("\"No Doctors Found\"");
		}

		return doctors;
	}

	@Override
	public List<Patient> readPatientByDoctor(int doctorId) throws HealthException {

		List<Patient> patients = new ArrayList<Patient>();

		List<Appointment> appointments = appointmentRepository.findAll();
		Patient patient = new Patient();

		for (Appointment appointment : appointments) {

			if (appointment.getDoctorId() == doctorId) {

				patient = patientRepository.findById(appointment.getPatientId()).get();
				patients.add(patient);

			}
		}

		if (patients.isEmpty()) {
			throw new HealthException("\"No Patient Found\"");
		}

		return patients;
	}

	@Override
	public List<Appointment> allAppointment() throws HealthException {

		List<Appointment> appointments = appointmentRepository.findAll();

		if (appointments.isEmpty()) {
			throw new HealthException("\"No Appointments Found\"");
		}
		return appointments;
	}

	@Override
	public String upateAppointmentDateTime(int appointmentNo, Appointment appointment) throws HealthException {

		Appointment appointment2 = appointmentRepository.findById(appointmentNo).get();

		appointment2.setAppoinmentTime(appointment.getAppoinmentTime());
		appointment2.setAppointmentDate(appointment.getAppointmentDate());

		appointmentRepository.save(appointment2);

		return "\"Updated Successfully\"";
	}

	@Override
	public Appointment getAppointment(int appointmentNo) throws HealthException {

		Appointment appointment = appointmentRepository.findById(appointmentNo).get();

		return appointment;
	}

	@Override
	public List<Appointment> readByPatient(String patientName) throws HealthException {

		List<Appointment> appointments = appointmentRepository.findByPatientName(patientName);

		if (appointments.isEmpty()) {
			throw new HealthException("\"No Appointments Found\"");
		}

		return appointments;
	}

	@Override
	public String updateAppointment(int appointmentId, Appointment appointment) throws HealthException {

		Appointment appointment2 = appointmentRepository.findById(appointmentId).get();

		if ((appointment.getAppointmentStatus().equals(AppointmentStatus.COMPLETED)
				|| appointment.getAppointmentStatus().equals(AppointmentStatus.CANCEL))) {
			appointment2.setAppointmentStatus(appointment.getAppointmentStatus());
			appointmentRepository.save(appointment2);
			return "\"Updated Successfully\"";
		}

		if (isAvailable(appointment.getDoctorId(), appointment.getAppoinmentTime(), appointment.getAppointmentDate())) {

			appointment2.setAppointmentDate(appointment.getAppointmentDate());

			appointment2.setAppoinmentTime(appointment.getAppoinmentTime());

			appointment2.setAppointmentStatus(appointment.getAppointmentStatus());

			appointmentRepository.save(appointment2);

			return "\"Updated Successfully\"";

		} else {

			throw new HealthException("\"Cannot Update At this Time\"");

		}

//		System.out.println(appointment.getAppointmentDate());
//		System.out.println(appointment.getAppoinmentTime());

	}

	@Override
	public Appointment getAppointmentById(int appointmentId) throws HealthException {

		Appointment appointment = appointmentRepository.findById(appointmentId).get();

		if (appointment == null) {
			throw new HealthException("\"No Appointment Found\"");
		}
		return appointment;
	}

	@Override
	public Receptionist receptionlogin(String receptionName) throws HealthException {

		Receptionist receptionist = receptionistRepository.findByReceptionistUserName(receptionName);

		if (receptionist == null) {
			throw new HealthException("\"No Receptionist Found\"");
		}

		return receptionist;
	}

	@Override
	public List<Bill> allBill() throws HealthException {

		List<Bill> bills = billRepository.findAll();

		if (bills.isEmpty()) {
			throw new HealthException("No Bill Found");
		}

		return bills;
	}

	@Override
	public Receptionist readReceptionistById(int ReceptionistId) throws HealthException {

		Receptionist receptionist = receptionistRepository.findById(ReceptionistId).get();

		if (receptionist == null) {
			throw new HealthException("No Receptionist Found");
		}
		return receptionist;
	}

	@Override
	public List<Doctor> appoint(String reason, String speciality, LocalTime appoinmentTime, LocalDate appointmentDate)
			throws HealthException {

		List<Doctor> doctors = doctorRepository.findBySpeciality(speciality);

		if (doctors.isEmpty()) {
			throw new HealthException("\"No Speciality Found\"");
		}

		List<Doctor> list = new ArrayList<Doctor>();

//		List<Appointment> appointments =  appointmentRepository.findAll();

		for (Doctor doctor : doctors) {

			if (doctor.getSpeciality().equals(speciality)) {
				if (isAvailable(doctor.getDoctorId(), appoinmentTime, appointmentDate)) {
					list.add(doctor);
				}
			}

		}

		return list;
	}

	@Override
	public String saveAppoint(Appointment appointment, int patientId, int receptionistId) throws HealthException {

		Patient patient = patientRepository.findById(patientId).get();

		Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

//		List<Appointment> appointments =  appointmentRepository.findAll();

		if (appointment.getAppointmentDate().isAfter(LocalDate.now())
				|| appointment.getAppointmentDate().isEqual(LocalDate.now())) {

			appointment.setPatient(patient);
			appointment.setReceptionist(receptionist);

			appointment.setPatientId(patient.getPatientId());

			appointment.setPatientName(patient.getPatientName());
			appointment.setAppointmentStatus(AppointmentStatus.ACTIVE);

			appointmentRepository.save(appointment);

		} else {
			throw new HealthException("\"Enter Correct Data\"");
		}

//		 System.out.println("gfdgdf");

		return "\"created\"";
	}

	@Override
	public Patient getPatientById(int patientId) throws HealthException {

		Patient patient = patientRepository.findById(patientId).get();

		return patient;
	}

	@Override
	public String updatePassword(int receptionistId, String Newpassword, String oldPassword) throws HealthException {

		Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

		if (receptionist == null) {
			throw new HealthException("No Receptionist found");
		}

		System.out.println(receptionist.getReceptionistPassword());
		System.out.println(oldPassword);

		if (receptionist.getReceptionistPassword().equals(oldPassword)
				&& (!receptionist.getReceptionistPassword().equals(Newpassword))) {

			receptionist.setReceptionistPassword(Newpassword);

			receptionistRepository.save(receptionist);
			return "\"Updated Successfully\"";
		} else {
			throw new HealthException("Already Password Exists");
		}

	}

	@Override
	public List<Appointment> appointmentsForToday() throws HealthException {

		List<Appointment> list = new ArrayList<Appointment>();

		List<Appointment> appointments = appointmentRepository.findAll();

		LocalDate date = LocalDate.now();
		System.out.println(date);

		for (Appointment appointment : appointments) {

			if (appointment.getAppointmentDate().equals(date)) {
				list.add(appointment);
			}
		}

		return list;
	}

	@Override
	public List<Appointment> activeAppointments() throws HealthException {

		List<Appointment> list = new ArrayList<Appointment>();

		List<Appointment> appointments = appointmentRepository.findAll();

		for (Appointment appointment : appointments) {

			if (appointment.getAppointmentStatus().equals(AppointmentStatus.ACTIVE)) {
				list.add(appointment);
			} else {
				System.out.println("hii");
			}

		}

		return list;
	}

	@Override
	public long patientCount() throws HealthException {

		return patientRepository.count();
	}

	@Override
	public long activeAppointmentsCount() throws HealthException {

		long activeCount = receptionistRepository.activeAppointmentsCount();

		return activeCount;
	}

	@Override
	public double totalBillPercentage() throws HealthException {

		List<Bill> bills = billRepository.findAll();

		double totalBill = 0;

		for (Bill bill : bills) {

			totalBill = totalBill + bill.getAmount();
		}

		return totalBill;
	}

	@Override
	public int totalVistForTodatCount() throws HealthException {

		List<Appointment> list = new ArrayList<Appointment>();

		List<Appointment> appointments = appointmentRepository.findAll();

		LocalDate date = LocalDate.now();

		for (Appointment appointment : appointments) {

			if (appointment.getAppointmentDate().equals(date)) {
				list.add(appointment);
			}

		}

		int count = list.size();

		return count;
	}

	@Override
	public long doctorsCount() throws HealthException {

		return doctorRepository.count();
	}

	@Override
	public long allAppointmentsCount() throws HealthException {

		long allAppointmentsCount = appointmentRepository.count();

		return allAppointmentsCount;
	}

	@Override
	public long billCount() throws HealthException {

		return billRepository.count();
	}

	@Override
	public Receptionist receptionloginByPhoneNumber(long receptionPhoneNumber) throws HealthException {

		Receptionist receptionist = receptionistRepository.findByReceptionistPhoneNo(receptionPhoneNumber);

		if (receptionist == null) {
			throw new HealthException("\"No Receptionist Found\"");
		}

		return receptionist;

	}

	@Override
	public List<String> getSpeciality() throws HealthException {

		List<String> list = doctorRepository.getSpeciality();

		return list;
	}

}
