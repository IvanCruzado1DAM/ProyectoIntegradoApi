package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Valorationgame;
import com.example.demo.model.ValorationgameModel;

public interface ValorationgameService {
	
	public abstract List<ValorationgameModel> listAllValorationgames();
	
	public abstract Valorationgame addValorationgame(int idmatchvm, int idplayervm, int defensiverating, int tacticalrating, int offensiverating, int finalscore);

	public abstract int removeValorationgame(int id);

	public abstract Valorationgame transformValorationgame(ValorationgameModel valorationgame);

	public abstract ValorationgameModel transformValorationgameModel(Valorationgame valorationgame);

	public abstract Valorationgame loadValorationgameByIdPlayer(int id);

	List<ValorationgameModel> listAllValorationgamesByGame(int idgame);

	

	
}
