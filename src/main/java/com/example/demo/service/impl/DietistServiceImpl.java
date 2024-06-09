package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Dietist;
import com.example.demo.entity.Physio;
import com.example.demo.entity.Team;
import com.example.demo.model.DietistModel;
import com.example.demo.model.PhysioModel;
import com.example.demo.model.TeamModel;
import com.example.demo.repository.DietistRepository;
import com.example.demo.service.DietistService;

@Service("dietistService")
public class DietistServiceImpl implements DietistService {

	@Autowired
	@Qualifier("dietistRepository")
	private DietistRepository dietistRepository;
	
	@Override
	public List<DietistModel> listAllDietist() {
		ModelMapper modelMapper = new ModelMapper();
		List<Dietist> dietistsList = dietistRepository.findAll();
		return dietistsList.stream().map(p -> modelMapper.map(p, DietistModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public Dietist addDietist(DietistModel dietistModel) {
		dietistModel.setName(dietistModel.getName());
		dietistModel.setAge(dietistModel.getAge());
		dietistModel.setIdteam_dietist(dietistModel.getIdteam_dietist());
		Dietist p = transformDietist(dietistModel);
		return dietistRepository.save(p);
	}

	@Override
	public int removeDietist(int id) {
		dietistRepository.deleteById(id);
		return id;
	}

	@Override
	public Dietist updateDietist(int id, DietistModel model) {
		Dietist dietist = dietistRepository.findById(id);
		if (dietist != null) {
			dietist.setName(model.getName());
			dietist.setAge(model.getAge());
			dietist.setIdteam_dietist(model.getIdteam_dietist());

			return dietistRepository.save(dietist);
		}
		return null;
	}

	@Override
	public Dietist transformDietist(DietistModel dietistModel) {
		if (dietistModel == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dietistModel, Dietist.class);
	}

	@Override
	public DietistModel transformDietistModel(Dietist dietist) {
		if (dietist == null) {
			return null; 
		}
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dietist, DietistModel.class);
	}

	@Override
	public Dietist loadDietistById(int id) {
		Dietist dietist = dietistRepository.findById(id);
		return dietist;
	}

	@Override
	public List<DietistModel> listAllDietistsbyIdTeam(int idTeam) {
		ModelMapper modelMapper = new ModelMapper();
		List<Dietist> dietistsList = dietistRepository.findAll();
		
		List<DietistModel> filtreddietistsList =  dietistsList.stream()
				 .filter(p -> p.getIdteam_dietist() == idTeam)
				.map(p -> modelMapper.map(p, DietistModel.class))
				.collect(Collectors.toList());
		return filtreddietistsList;
	}
	
	@Override
	public boolean exists(int id) {
		Dietist d=dietistRepository.findById(id);
		if( d != null) {
			return true;
		}
		return false;
	}

}
