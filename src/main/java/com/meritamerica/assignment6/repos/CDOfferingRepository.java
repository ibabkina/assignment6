package com.meritamerica.assignment6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.CDOffering;

public interface CDOfferingRepository extends JpaRepository<CDOffering, Integer> {
	
	CDOffering findById(int id);
	
}
