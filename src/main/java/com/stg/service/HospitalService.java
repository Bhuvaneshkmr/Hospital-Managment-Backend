package com.stg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.web.bind.annotation.PathVariable;

import com.stg.entity.Bill;
import com.stg.entity.Gender;
import com.stg.entity.Hospital;
import com.stg.entity.Patient;
import com.stg.entity.Qualification;
import com.stg.entity.Receptionist;
import com.stg.entity.Speciality;
import com.stg.exception.HealthException;

/**
 * @author bhuvaneshkumarg
 *
 */
public interface HospitalService {

	public abstract String createHospital(Hospital hospital) throws HealthException;

	public abstract String createDoctor(String doctorName,String doctorPassword , String speciality,
			int doctorAge, Gender doctorGender, long doctorPhoneNumber, Qualification doctorQualification, int yearOfPractice,LocalDate joiningDate,int hospitalId) throws HealthException;

	public abstract String createReceptionist(Receptionist receptionist,int id) throws HealthException;

	public abstract String deleteDoctor(int doctorId) throws HealthException;

	public abstract String deleteReceptionist(int receptionistId) throws HealthException;

	public abstract List<Patient> readPatientByReceptionist(int receptionistId) throws HealthException;
	
	public abstract Hospital adminLogin(String adminName)throws HealthException;
	
	public abstract Hospital adminById(int adminId)throws HealthException;
	
	public abstract List<Receptionist> readAllReceptionist()throws HealthException;
	
	public abstract List<Patient> listOfPatientByReceptionist(int receptionistId)throws HealthException;
	
	public abstract long receptionistCount()throws HealthException;
	
	public abstract double totalBillPercentageForAdmin()throws HealthException;
	
	public abstract List<Bill> totalBill()throws HealthException;
	
	public abstract String createAdmin(Hospital hospital)throws HealthException;
	
	public abstract Hospital adminLoginByPhoneNumber(long phoneNo)throws HealthException;
	
	public abstract String updatePassword(int adminId,String Newpassword , String oldPassword)throws HealthException;
	
	public abstract boolean mobileNoExist(long phoneNo)throws HealthException;
	
	public abstract boolean mobileNoExistforDoctor(long phoneNo)throws HealthException;
	
	
}
