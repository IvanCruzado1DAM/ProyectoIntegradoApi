package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Valorationgame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idvalorationmatch;
	private int idmatchvm;
	private int idplayervm;
	private int defensiverating;
	private int tacticalrating;
	private int offensiverating;
	private int finalscore;  
}
