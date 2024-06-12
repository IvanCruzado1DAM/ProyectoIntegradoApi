package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Coach;
import com.example.demo.entity.Renovationoffer;
import com.example.demo.model.CoachModel;
import com.example.demo.model.RenovationofferModel;
import com.example.demo.repository.RenovationofferRepository;
import com.example.demo.service.RenovationofferService;

@Service("renovationService")
public class RenovationofferServiceImpl implements RenovationofferService {
	
	@Autowired
	@Qualifier("renovationRepository")
	private RenovationofferRepository renovationRepository;
	
	@Override
	public List<RenovationofferModel> listAllRenovationoffers() {
		ModelMapper modelMapper = new ModelMapper();
		List<Renovationoffer> renovationoffersList = renovationRepository.findAll();
		return renovationoffersList.stream().map(p -> modelMapper.map(p, RenovationofferModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public Renovationoffer addRenovationoffer(int idplayerrenovation, int year) {
		RenovationofferModel ro=new RenovationofferModel();
		ro.setIdplayerrenovation(idplayerrenovation);
		ro.setYear(year);
		Renovationoffer renovationoffer=transformRenovationoffer(ro);
		return renovationRepository.save(renovationoffer);
	}

	@Override
	public int removeRenovationoffer(int id) {
		renovationRepository.deleteById(id);
		return id;
	}

	@Override
	public Renovationoffer transformRenovationoffer(RenovationofferModel renovationofferModel) {
		if (renovationofferModel == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(renovationofferModel, Renovationoffer.class);
	}

	@Override
	public RenovationofferModel transformRenovationofferModel(Renovationoffer renovationoffer) {
		if (renovationoffer == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(renovationoffer, RenovationofferModel.class);
	}

}
