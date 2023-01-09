package com.stg.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.entity.Appointment;
import com.stg.entity.AppointmentStatus;

/**
 * @author bhuvaneshkumarg
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	public abstract Appointment findByPatientId(int patientId);

	public abstract List<Appointment> findByDoctorId(int doctorId);
	
	public abstract List<Appointment> findByPatientName(String patientName);

	public abstract List<Appointment> findByAppointmentStatus(AppointmentStatus appointmentStatus);

	public abstract List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
	
	
	
	

}
