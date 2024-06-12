package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Valorationgame;

@Repository("valorationgameRepository")
public interface ValorationgameRepository extends JpaRepository <Valorationgame, Integer> {
	public abstract Valorationgame findById(int id);
	
	public abstract Valorationgame findByIdplayervm(int Idplayervm);
}
