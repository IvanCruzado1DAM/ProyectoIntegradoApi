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
public class Renovationoffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrenovationoffer;
	private int idplayerrenovation;
	private int year;
	private String status;
}
