package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Dietist;
import com.example.demo.entity.Renovationoffer;

@Repository("renovationRepository")
public interface RenovationofferRepository extends JpaRepository <Renovationoffer, Integer> {
	public abstract Renovationoffer findById(int id);
	
	public abstract Renovationoffer findByIdplayerrenovation(int idplayerrenovation);
}
