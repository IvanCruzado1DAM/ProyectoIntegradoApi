package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medicalpart;
import com.example.demo.model.MedicalpartModel;
import com.example.demo.repository.MedicalpartRepository;
import com.example.demo.service.MedicalpartService;

@Service("medicalpartService")
public class MedicalpartServiceImpl implements MedicalpartService{

	@Autowired
	@Qualifier("medicalpartRepository")
	private MedicalpartRepository medicalpartRepository;
	
	@Override
	public List<MedicalpartModel> listAllMedicalparts() {
		ModelMapper modelMapper = new ModelMapper();
		List<Medicalpart> medicalpartList = medicalpartRepository.findAll();
		return medicalpartList.stream().map(p -> modelMapper.map(p, MedicalpartModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public Medicalpart addMedicalpart(int idPhysioMP, int idTeamMP, int idPlayer, String description, String recoverymethod) {
		MedicalpartModel mp = new MedicalpartModel();
		mp.setIdphysiomp(idPhysioMP);
		mp.setIdteammp(idTeamMP);
		mp.setIdplayermp(idPlayer);
		mp.setDescription(description);
		mp.setRecoverymethod(recoverymethod);
		Medicalpart medicalpart = transformMedicalpart(mp);
		return medicalpartRepository.save(medicalpart);
	}

	@Override
	public int removeMedicalpart(int id) {
		medicalpartRepository.deleteById(id);
		return id;
	}

	@Override
	public Medicalpart transformMedicalpart(MedicalpartModel medicalpartModel) {
		if (medicalpartModel == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(medicalpartModel, Medicalpart.class);
	}

	@Override
	public MedicalpartModel transformMedicalpartModel(Medicalpart medicalpart) {
		if (medicalpart == null) {
			return null; 
		}

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(medicalpart, MedicalpartModel.class);
	}

	@Override
	public Medicalpart loadMedicalpartByIdPlayer(int id) {
		Medicalpart mp = medicalpartRepository.findByIdplayermp(id);
		return mp;
	}

	
	

}
