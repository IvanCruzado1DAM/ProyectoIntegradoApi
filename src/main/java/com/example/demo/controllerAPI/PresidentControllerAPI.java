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
import com.example.demo.model.PlayerModel;
import com.example.demo.model.TeamModel;
import com.example.demo.service.impl.PlayerServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/apiPresident")
public class PresidentControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("playerService")
    private PlayerServiceImpl playerService;

    @PostMapping("/setTransferible")
    public ResponseEntity<?> setTransferible(@RequestParam("idPresident") int idPresident, @RequestParam("idPlayer") int idPlayer) {
        User d=userService.loadUserById(idPresident);
        Player p= playerService.loadPlayerById(idPlayer);
        if (p.getId_team() == d.getId_team_user()) {
        	playerService.updateTransferible(idPlayer);
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You cannot assign a diet to a player who is not on your team");
        }
    }
    
    @GetMapping("/getPlayersbyTeam")
    public List<PlayerModel> getPlayers(@RequestParam("id") int id) {
        return playerService.listAllPlayersbyIdTeam(id);
    }
    
    

    

}