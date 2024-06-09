package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Game;
import com.example.demo.model.GameModel;

public interface GameService {
	public abstract List<GameModel> listAllGames();
	
	public abstract Game addGame(GameModel dietistModel);

	public abstract int removeGame(int id);

	public abstract Game updateGame(int id, GameModel dietistModel);

	public abstract Game transformGame(GameModel dietistModel);

	public abstract GameModel transformGameModel(Game dietist);

	boolean Verificarequipos(Game game);

	public abstract List<GameModel> listAllGamesByTeam(int idTeam);

	Game loadGameById(int id);
}
