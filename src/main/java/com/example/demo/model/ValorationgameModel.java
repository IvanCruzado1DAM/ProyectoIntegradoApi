package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValorationgameModel {
	
	private int idvalorationmatch;
	private int idmatchvm;
	private int idplayervm;
	private int defensiverating;
	private int tacticalrating;
	private int offensiverating;
	private int finalscore;  
}
