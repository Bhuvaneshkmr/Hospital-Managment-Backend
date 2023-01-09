package com.stg.service;

import java.util.List;

import com.stg.entity.Appointment;
import com.stg.entity.Bill;
import com.stg.entity.Patient;
import com.stg.exception.HealthException;

/**
 * @author bhuvaneshkumarg
 *
 */
public interface PatientService {

	public abstract Patient readByPatientId(int patientId) throws HealthException;

	public abstract List<Appointment> patientAppointment(int patientId) throws HealthException;

	public abstract List<Bill> patientBill(int patientId) throws HealthException;

	public abstract String updatePatientDetails(int patientId, long patientPhoneNo) throws HealthException;

}
