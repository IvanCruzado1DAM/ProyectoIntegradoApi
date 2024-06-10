package com.example.demo.controllerAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Player;
import com.example.demo.entity.User;
import com.example.demo.service.impl.PlayerServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/apiDietist")
public class DietistControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("playerService")
    private PlayerServiceImpl playerService;

    @PostMapping("/setDiet")
    public ResponseEntity<?> setDiet(@RequestParam("idDietist") int idDietist, @RequestParam("idPlayer") int idPlayer, @RequestParam("diet") String diet) {
        User d=userService.loadUserById(idDietist);
        Player p= playerService.loadPlayerById(idPlayer);
        if (p.getId_team() == d.getId_team_user()) {
        	playerService.updateDiet(idPlayer, diet);
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No puedes asignar una dieta a un jugador que no es de tu equipo");
        }
    }
    
    

    

}