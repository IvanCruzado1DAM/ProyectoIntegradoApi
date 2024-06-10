package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicalpartModel {
	
	private int id_medicalpart;
	private int idphysiomp;
	private int idteammp;
	private int idplayermp;
	private String description;
	private String recoverymethod;
} 
