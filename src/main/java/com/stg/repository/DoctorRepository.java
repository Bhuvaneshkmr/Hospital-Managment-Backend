package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.entity.Doctor;
import com.stg.entity.Receptionist;
import com.stg.entity.Speciality;

/**
 * @author bhuvaneshkumarg
 *
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	public abstract List<Doctor> findByDoctorName(String doctorName);
	
	public abstract Doctor findByDoctorId(int doctorId);
	
	public abstract List<Doctor> findBySpeciality(String speciality);
	
	
	public abstract Doctor findByDoctorPhoneNumber(long doctorPhoneNumber);

	public abstract boolean existsByDoctorPhoneNumber(long phoneNo);
	
	
	
	@Query(value = "SELECT DISTINCT(speciality) FROM doctor", nativeQuery = true)
	public abstract List<String> getSpeciality();
	
	
	
	
	

}
