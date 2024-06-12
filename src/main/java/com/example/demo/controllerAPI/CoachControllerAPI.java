package com.example.demo.controllerAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Player;
import com.example.demo.entity.User;
import com.example.demo.model.GameModel;
import com.example.demo.model.PlayerModel;
import com.example.demo.model.ValorationgameModel;
import com.example.demo.service.impl.GameServiceImpl;
import com.example.demo.service.impl.PlayerServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.service.impl.ValorationgameServiceImpl;

@RestController
@RequestMapping("/apiCoach")
public class CoachControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("playerService")
    private PlayerServiceImpl playerService;
    
    @Autowired
    @Qualifier("gameService")
    private GameServiceImpl gameService;
    
    @Autowired
    @Qualifier("valorationgameService")
    private ValorationgameServiceImpl valorationgameService;
    

    @PostMapping("/setValorationGame")
    public ResponseEntity<?> setValorationGame(@RequestParam("idmatchvm") int idmatchvm, @RequestParam("idplayervm") int idplayervm, @RequestParam("defensiverating") int defensiverating, @RequestParam("tacticalrating") int tacticalrating,  @RequestParam("offensiverating") int offensiverating, @RequestParam("finalscore")  int finalscore) {
        List<ValorationgameModel> valorationGames = valorationgameService.listAllValorationgamesByGame(idmatchvm);
        Player p = playerService.loadPlayerById(idplayervm);
        
        boolean idExists = valorationGames.stream().anyMatch(valorationGame -> valorationGame.getIdplayervm() == idplayervm);
        
        if (!idExists) {
           valorationgameService.addValorationgame(idmatchvm, idplayervm, defensiverating, tacticalrating, offensiverating, finalscore);
           return ResponseEntity.ok().body("Player has been valorated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already has a valoration assigned");
        }
    }

    
    
    @GetMapping("/getGamesbyTeam")
    public List<GameModel> getGames(@RequestParam("id") int id) {
        return gameService.listAllGamesByTeam(id);
    }
    
    @GetMapping("/getPlayersbyTeam")
    public List<PlayerModel> getPlayers(@RequestParam("id") int id) {
        return playerService.listAllPlayersbyIdTeam(id);
    }
    
    

    

}