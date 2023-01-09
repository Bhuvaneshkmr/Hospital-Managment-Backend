package com.stg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stg.entity.Appointment;
import com.stg.entity.AppointmentStatus;
import com.stg.entity.Availability;
import com.stg.entity.Bill;
import com.stg.entity.Doctor;
import com.stg.entity.Gender;
import com.stg.entity.Hospital;
import com.stg.entity.Patient;
import com.stg.entity.Qualification;
import com.stg.entity.Receptionist;
import com.stg.entity.Speciality;
import com.stg.exception.HealthException;
import com.stg.repository.AppointmentRepository;
import com.stg.repository.BillRepository;
import com.stg.repository.DoctorRepository;
import com.stg.repository.HospitalRepository;
import com.stg.repository.PatientRepository;
import com.stg.repository.ReceptionistRepository;

/**
 * @author bhuvaneshkumarg
 *
 */
@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ReceptionistRepository receptionistRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public String createHospital(Hospital hospital) throws HealthException {
		hospitalRepository.save(hospital);

		return "\"Created\"";
	}

	@Override
	public String createDoctor(String doctorName, String doctorPassword, String speciality, int doctorAge,
			Gender doctorGender, long doctorPhoneNumber, Qualification doctorQualification, int yearOfPractice,
			LocalDate joiningDate, int hospitalId) throws HealthException {

		Doctor doctor = new Doctor();
//		if (!(String.valueOf(doctorId).length() > 0)) {
//			throw new HealthException("Enter the Correct Id");
//		}
//		doctor.setDoctorId(doctorId);
//		if (!(doctorName.matches("[a-zA-Z]{3,31}"))) {
//			throw new HealthException("Enter the Correct Name");
//		}
		doctor.setDoctorName(doctorName);

		if (!(doctorPassword.length() > 8)) {
			throw new HealthException("Enter the Coorect Password");
		}

		doctor.setDoctorPassword(doctorPassword);

		doctor.setAvailability(Availability.AVAILABLE);

//		if (!(speciality.equalsIgnoreCase("General") || speciality.equalsIgnoreCase("ENT"))) {
//			throw new HealthException("This Speciality is Not Eligible");
//		}

		doctor.setSpeciality(speciality);
		System.out.println(speciality);

//		if (!(String.valueOf(doctorSalary).length() > 0)) {
//			throw new HealthException("Enter the Correct Salary");
//		}
//		doctor.setDoctorSalary(doctorSalary);
		if (!(doctorAge > 25 && doctorAge < 58)) {
			throw new HealthException("Age is not Eligible");
		}
		doctor.setDoctorAge(doctorAge);

		doctor.setDoctorGender(doctorGender);

		if (!(String.valueOf(doctorPhoneNumber).matches("^[6-9]{1}[0-9]{9}"))) {
			throw new HealthException("Enter the Correct Phone Number");
		}

		doctor.setDoctorPhoneNumber(doctorPhoneNumber);

//		if (!(doctorQualification.contains("MBBS") || doctorQualification.contains("MBBS,MD"))) {
//			throw new HealthException("Qualification Not Eligible");
//		}
		doctor.setDoctorQualification(doctorQualification);
		if (!(yearOfPractice > 2)) {
			throw new HealthException("Year Of Practice Not Eligible");
		}
		doctor.setYearOfPractice(yearOfPractice);

		doctor.setJoiningDate(joiningDate);

		Hospital hospital = hospitalRepository.findById(hospitalId).get();
		doctor.setHospital(hospital);
		doctorRepository.save(doctor);

		return "\"Doctor Created Successfully \"";
	}

	@Override
	public String createReceptionist(Receptionist receptionist, int id) throws HealthException {

		if (!(String.valueOf(receptionist.getReceptionistId()).length() > 0)) {
			throw new HealthException("Enter the Correct No");
		}
		if (!(receptionist.getReceptionistUserName().length() > 0)) {
			throw new HealthException("Enter the Correct Name");
		}
		if (!(receptionist.getReceptionistPassword().length() >= 8)) {
			throw new HealthException("Enter the Password Correctly");
		}
//		if (!(receptionist.getReceptionistGender().matches("[M|F]{1}"))) {
//			throw new HealthException("Enter the Correct Gender");
//		}
		if (!(String.valueOf(receptionist.getReceptionistPhoneNo()).length() == 10)) {
			throw new HealthException("Enter the 10 Digit Number");
		}

		Hospital hospital = hospitalRepository.findById(id).get();
		receptionist.setHospital(hospital);
		receptionistRepository.save(receptionist);
		return "\"Created Successfully\"";
	}

	@Override
	public String deleteDoctor(int doctorId) throws HealthException {
		
		
		Doctor doctor =  doctorRepository.findByDoctorId(doctorId);
		
		List<Appointment> appointments =   appointmentRepository.findAll();
		
		for (Appointment appointment : appointments) {
			
			if(appointment.getDoctorId()==doctor.getDoctorId() && appointment.getAppointmentStatus().equals(AppointmentStatus.ACTIVE)) {
				throw new HealthException("This Doctor having an Appointment");
			}
		}
		
		
		if (doctorRepository.existsById(doctorId)) {
			
			doctorRepository.deleteById(doctorId);
			return "Deleted Successfully";
		} else {
			throw new HealthException("No Doctor Found");
		}

	}

	@Override
	public String deleteReceptionist(int receptionistId) throws HealthException {

		if (receptionistRepository.existsById(receptionistId)) {
			receptionistRepository.deleteById(receptionistId);
			return "Deleted Successfully";
		} else {
			throw new HealthException("Receptionist Id Not Found");
		}

	}

	@Override
	public List<Patient> readPatientByReceptionist(int receptionistId) throws HealthException {

		List<Patient> patients = patientRepository.findAll();

		List<Patient> patients2 = new ArrayList<Patient>();

		if (receptionistRepository.existsById(receptionistId)) {

			for (Patient patient : patients) {

				if (patient.getReceptionist().getReceptionistId() == receptionistId) {
					patients2.add(patient);
				}

			}

		} else {
			throw new HealthException("Receptionist ID Not Found");
		}

		if (patients2.isEmpty()) {
			throw new HealthException("No Patient found");
		}

		return patients2;
	}

	@Override
	public Hospital adminLogin(String adminName) throws HealthException {

		Hospital hospital = hospitalRepository.findByUserName(adminName);

		if (hospital == null) {
			throw new HealthException("No Admin Found");
		}

		return hospital;
	}

	@Override
	public Hospital adminById(int adminId) throws HealthException {

		Hospital hospital = hospitalRepository.findById(adminId).get();
		
		if(hospital==null) {
			throw new HealthException("no Admin Found");
		}

		return hospital;
	}

	@Override
	public List<Receptionist> readAllReceptionist() throws HealthException {

		return receptionistRepository.findAll();
	}

	@Override
	public List<Patient> listOfPatientByReceptionist(int receptionistId) throws HealthException {

		List<Patient> list = new ArrayList<Patient>();

		List<Patient> patients = patientRepository.findAll();

		for (Patient patient : patients) {

			if (patient.getReceptionist().getReceptionistId() == receptionistId) {
				list.add(patient);
			}

		}

		if (list.isEmpty()) {
			throw new HealthException("No Patient Found");
		}

		return list;
	}

	@Override
	public long receptionistCount() throws HealthException {

		long count = receptionistRepository.count();

		return count;
	}

	@Override
	public double totalBillPercentageForAdmin() throws HealthException {

		List<Bill> bills = billRepository.findAll();

		double totalBill = 0;

		for (Bill bill : bills) {

			totalBill = totalBill + bill.getAmount();
		}

		return totalBill;

	}

	@Override
	public List<Bill> totalBill() throws HealthException {

		List<Bill> bills = billRepository.findAll();

		return bills;
	}

	@Override
	public String createAdmin(Hospital hospital) throws HealthException {

		if (hospital == null) {
			throw new HealthException("No Hospital");
		}
		
		if(!(String.valueOf(hospital.getUserName()).length()>0)) {
			throw new HealthException("Enter the Name Correctly");
		}
//		if(!(String.valueOf(hospital.getAdminPhoneNo()).matches("^[6-9]{1}[0-9]{9}"))) {
//			throw new HealthException("Enter the Phone Correctly");
//		}
		
		if(!(hospital.getPassword().length()>=8)) {
			throw new HealthException("Enter the Password Correctly");
		}
		

		hospitalRepository.save(hospital);

		return "\"Created Successfully\"";
	}

	@Override
	public Hospital adminLoginByPhoneNumber(long phoneNo) throws HealthException {
		
		Hospital hospital =  hospitalRepository.findByAdminPhoneNo(phoneNo);
		
		if(hospital==null) {
			throw new HealthException("No Hospital Found");
		}
	
		return hospital;
	}

	@Override
	public String updatePassword(int adminId, String Newpassword, String oldPassword) throws HealthException {
		
		Hospital hospital = hospitalRepository.findById(adminId).get();

		if (hospital == null) {
			throw new HealthException("No admin found");
		}

		System.out.println(hospital.getUserName());
		System.out.println(oldPassword);

		if (hospital.getPassword().equals(oldPassword)
				&& (!hospital.getPassword().equals(Newpassword))) {

			hospital.setPassword(Newpassword);

			hospitalRepository.save(hospital);
			return "\"Updated Successfully\"";
		} else {
			throw new HealthException("Already Password Exists");
		}

		
		
	}
	
	@Override
	public boolean mobileNoExist(long phoneNo) throws HealthException {
		
		
		
		return receptionistRepository.existsByReceptionistPhoneNo(phoneNo);
	}

	@Override
	public boolean mobileNoExistforDoctor(long phoneNo) throws HealthException {
		
		
		
		return doctorRepository.existsByDoctorPhoneNumber(phoneNo);
	}

}
