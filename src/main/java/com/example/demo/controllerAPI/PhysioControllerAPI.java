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
import com.example.demo.service.impl.MedicalpartServiceImpl;
import com.example.demo.service.impl.PlayerServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/apiPhysio")
public class PhysioControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("playerService")
    private PlayerServiceImpl playerService;
    
    @Autowired
    @Qualifier("medicalpartService")
    private MedicalpartServiceImpl medicalpartService;

    @PostMapping("/setMedicalPart")
    public ResponseEntity<?> setMedicalPart(@RequestParam("idPhysioMP") int idPhysioMP, @RequestParam("idTeamMP") int idTeamMP, @RequestParam("idPlayer") int idPlayer, @RequestParam("description") String description, @RequestParam("recoverymethod") String recoverymethod ) {
        User d=userService.loadUserById(idPhysioMP);
        Player p= playerService.loadPlayerById(idPlayer);
        if (p.getId_team() == d.getId_team_user()) {
        	if(d.getRole().equals("ROLE_PHYSIO")) {
	        	medicalpartService.addMedicalpart(d.getId_user(), idTeamMP, idPlayer, description, recoverymethod );
	            return ResponseEntity.ok().body("Medical Part created succesfully!");
        	}else {
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("FORBIDDEN FOR A USER WHO IS NOT A PHYSIO");
        	}
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You cannot assign a medical part to a player who is not on your team");
        }
    }
    
    @GetMapping("/getPlayersInjured")
    public List<PlayerModel> getPlayers(@RequestParam("id") int id) {
        return playerService.listAllPlayersInjuredbyIdTeam(id);
    }
    
    

    

}