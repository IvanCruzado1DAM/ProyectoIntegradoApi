package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Coach;
import com.example.demo.entity.Medicalpart;
import com.example.demo.entity.Player;
import com.example.demo.entity.President;
import com.example.demo.model.CoachModel;
import com.example.demo.model.MedicalpartModel;
import com.example.demo.model.PlayerModel;

public interface MedicalpartService {
	
	public abstract List<MedicalpartModel> listAllMedicalparts();
	
	public abstract Medicalpart addMedicalpart(int idPhysioMP, int idTeamMP, int idPlayer, String description, String recoverymethod);

	public abstract int removeMedicalpart(int id);

	public abstract Medicalpart transformMedicalpart(MedicalpartModel medicalpartModel);

	public abstract MedicalpartModel transformMedicalpartModel(Medicalpart medicalpart);

	public abstract Medicalpart loadMedicalpartByIdPlayer(int id);

	

	
}
