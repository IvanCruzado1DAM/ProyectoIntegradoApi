package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medicalpart;
import com.example.demo.entity.Valorationgame;
import com.example.demo.model.MedicalpartModel;
import com.example.demo.model.ValorationgameModel;
import com.example.demo.repository.MedicalpartRepository;
import com.example.demo.repository.ValorationgameRepository;
import com.example.demo.service.ValorationgameService;

@Service("valorationgameService")
public class ValorationgameServiceImpl implements ValorationgameService{

	@Autowired
	@Qualifier("valorationgameRepository")
	private ValorationgameRepository valorationgameRepository;
	
	@Override
	public List<ValorationgameModel> listAllValorationgames() {
		ModelMapper modelMapper = new ModelMapper();
		List<Valorationgame> valorationgameList = valorationgameRepository.findAll();
		return valorationgameList.stream().map(p -> modelMapper.map(p, ValorationgameModel.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ValorationgameModel> listAllValorationgamesByGame(int idgame) {
		ModelMapper modelMapper = new ModelMapper();
		List<Valorationgame> valorationgameList = valorationgameRepository.findAll();
		return valorationgameList.stream().filter(p -> p.getIdmatchvm() == idgame)
				.map(p -> modelMapper.map(p, ValorationgameModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public Valorationgame addValorationgame(int idmatchvm, int idplayervm, int defensiverating, int tacticalrating,
			int offensiverating, int finalscore) {
		ValorationgameModel vg = new ValorationgameModel();
		vg.setIdmatchvm(idmatchvm);
		vg.setIdplayervm(idplayervm);
		vg.setDefensiverating(defensiverating);
		vg.setTacticalrating(tacticalrating);
		vg.setOffensiverating(offensiverating);
		vg.setFinalscore(finalscore);
		Valorationgame valorationgame = transformValorationgame(vg);
		return valorationgameRepository.save(valorationgame);
	}

	@Override
	public int removeValorationgame(int id) {
		valorationgameRepository.deleteById(id);
		return id;
	}

	@Override
	public Valorationgame transformValorationgame(ValorationgameModel valorationgame) {
		if (valorationgame == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(valorationgame, Valorationgame.class);
	}

	@Override
	public ValorationgameModel transformValorationgameModel(Valorationgame valorationgame) {
		if (valorationgame == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(valorationgame, ValorationgameModel.class);
	}

	@Override
	public Valorationgame loadValorationgameByIdPlayer(int id) {
		Valorationgame vg = valorationgameRepository.findByIdplayervm(id);
		return vg;
	}

	

	
	

}
