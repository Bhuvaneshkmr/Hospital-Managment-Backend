package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stg.entity.Patient;

/**
 * @author bhuvaneshkumarg
 *
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public abstract List<Patient> findByPatientGender(String patientGender);

	public abstract List<Patient> findByPatientAgeBetween(int startAge, int endAge);

	public abstract List<Patient> findByPatientAge(int patientAge);

	public abstract List<Patient> findByPatientName(String patientName);

	@Modifying
	@Query(value = "UPDATE patient SET patient_phone_no=:patientPhoneNo WHERE patient_id=:patientId", nativeQuery = true)
	public abstract void updatePatientPhoneNo(@Param("patientId") int patientId, @Param("patientPhoneNo") long patientPhoneNo);
}
