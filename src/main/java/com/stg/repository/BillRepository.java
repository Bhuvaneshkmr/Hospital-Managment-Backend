package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.entity.Bill;

/**
 * @author bhuvaneshkumarg
 *
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
	
	public abstract Bill findByPatientId(int patientId);
	
	
	

}
