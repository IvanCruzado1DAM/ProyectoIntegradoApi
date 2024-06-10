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
public class Medicalpart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_medicalpart;
	private int idphysiomp;
	private int idteammp;
	private int idplayermp;
	private String description;
	private String recoverymethod;
	
}
