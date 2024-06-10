package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Coach;
import com.example.demo.entity.Medicalpart;

@Repository("medicalpartRepository")
public interface MedicalpartRepository extends JpaRepository <Medicalpart, Integer> {
	
	public abstract Medicalpart findById(int id);
	
	public abstract Medicalpart findByIdplayermp(int idPlayer);

}
