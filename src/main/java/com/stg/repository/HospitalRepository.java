package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.Hospital;
import com.stg.entity.Patient;
import com.stg.entity.Receptionist;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	
	public abstract Hospital findByUserName(String UserName);
	
	public abstract Hospital findByAdminPhoneNo(long PhoneNo);
	
//	public abstract Hospital findById(int adminId);

}
