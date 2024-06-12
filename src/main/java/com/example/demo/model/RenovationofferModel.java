package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RenovationofferModel {
	private int idrenovationoffer;
	private int idplayerrenovation;
	private int year;
	private String status;
}
