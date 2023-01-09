package com.stg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stg.entity.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {
	
	public abstract Receptionist findByReceptionistUserName(String ReceptionistUserName);
	
	public abstract Receptionist findByReceptionistPhoneNo(long receptionistPhoneNo);
	
	
	@Query(value = "SELECT COUNT(*) FROM appointment where appointment_status='ACTIVE'", nativeQuery = true)
	public abstract int activeAppointmentsCount();

	public abstract boolean existsByReceptionistPhoneNo(long phoneNo);

}
