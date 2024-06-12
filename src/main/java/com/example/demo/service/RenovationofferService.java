package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Renovationoffer;
import com.example.demo.model.RenovationofferModel;

public interface RenovationofferService {
	
	public abstract List<RenovationofferModel> listAllRenovationoffers();
	
	public abstract Renovationoffer addRenovationoffer(int idplayerrenovation, int year);

	public abstract int removeRenovationoffer(int id);

	public abstract Renovationoffer transformRenovationoffer(RenovationofferModel renovationofferModel);

	public abstract RenovationofferModel transformRenovationofferModel(Renovationoffer renovationoffer);


	
}
