package com.stg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stg.entity.Appointment;
import com.stg.entity.Bill;
import com.stg.entity.Patient;
import com.stg.exception.HealthException;
import com.stg.repository.AppointmentRepository;
import com.stg.repository.BillRepository;
import com.stg.repository.PatientRepository;

/**
 * @author bhuvaneshkumarg
 *
 */
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private BillRepository billRepository;

	@Override
	public Patient readByPatientId(int patientId) throws HealthException {

		Patient patient = null;

		if (String.valueOf(patientId).length() > 0) {

			Optional<Patient> optional = patientRepository.findById(patientId);

			if (optional.isPresent()) {

				patient = optional.get();
				return patient;

			} else {
				throw new HealthException("Enter the Correct ID ");
			}

		} else {
			throw new HealthException("Enter the Correct Data");
		}

	}

	@Override
	public List<Appointment> patientAppointment(int patientId) throws HealthException {

		if (!(String.valueOf(patientId).length() > 0)) {
			throw new HealthException("Enter the Correct Id");
		}

		List<Appointment> appointmentRes = new ArrayList<Appointment>();

		List<Appointment> appointments = appointmentRepository.findAll();

		if (patientRepository.existsById(patientId)) {

			for (Appointment appointment : appointments) {

				if (appointment.getPatientId() == patientId) {
					appointmentRes.add(appointment);
				}
			}

		} else {
			throw new HealthException("Id Not Found");
		}

		return appointmentRes;
	}

	@Override
	public List<Bill> patientBill(int patientId) throws HealthException {

		if (!(String.valueOf(patientId).length() > 0)) {
			throw new HealthException("Enter the Correct Id");
		}

		if (patientRepository.existsById(patientId)) {
			List<Bill> billRes = new ArrayList<Bill>();

			List<Bill> bills = billRepository.findAll();

			for (Bill bill : bills) {

				if (bill.getPatientId() == patientId) {

					billRes.add(bill);
				}
			}
			return billRes;
		} else {
			throw new HealthException("No Patient Found");
		}

	}

	@Transactional
	@Override
	public String updatePatientDetails(int patientId, long patientPhoneNo) throws HealthException {

		if (patientRepository.existsById(patientId)) {

			patientRepository.updatePatientPhoneNo(patientId, patientPhoneNo);
			return "Updated Successfully";

		} else {
			throw new HealthException("Patient Not Found");
		}

	}

}
