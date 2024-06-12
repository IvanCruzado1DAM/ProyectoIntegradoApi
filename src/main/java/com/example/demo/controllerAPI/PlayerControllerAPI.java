package com.example.demo.controllerAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Player;
import com.example.demo.service.impl.PlayerServiceImpl;
import com.example.demo.service.impl.RenovationofferServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/apiPlayer")
public class PlayerControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("playerService")
    private PlayerServiceImpl playerService;
    
    @Autowired
    @Qualifier("renovationService")
    private RenovationofferServiceImpl renovationService;
    
    @PostMapping("/sendRenovationoffer")
    public ResponseEntity<?> sendRenovationoffer(@RequestParam("idplayerrenovation") int idplayerrenovation, @RequestParam("year") int year ) {
    	renovationService.addRenovationoffer(idplayerrenovation, year);
        return ResponseEntity.ok().body("Renovation offer sent!");        
    }
    
    
    @GetMapping("/getInfoPlayer")
    public Player getInfoPlayer(@RequestParam("id") int id) {
        return playerService.getByUser(id);
    }
    
    
    
    

    

}