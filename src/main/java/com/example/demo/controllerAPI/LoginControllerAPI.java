package com.example.demo.controllerAPI;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.TeamModel;
import com.example.demo.service.impl.TeamServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.security.SecurityConfig;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api")
public class LoginControllerAPI {

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("teamService")
    private TeamServiceImpl teamService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        User user = userService.findUserByUsername(username);
        if (user != null && userService.checkPassword(pwd, user.getPassword())) {
            String token = getJWTToken(user.getUsername(), user.getRole());
            user.setToken(token);
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

    private String getJWTToken(String username, String role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

        String token = Jwts.builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600_000)) // 10 minutos
                .signWith(SecurityConfig.getSecretKey()) // Firma con la clave segura
                .compact();

        return "Bearer " + token;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam("name") String name,
                                          @RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("id_team_user") int idTeamUser) {
        User existingUser = userService.findUserByUsername(username);
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setId_team_user(idTeamUser);

        User savedUser = userService.registrar(newUser);

        return ResponseEntity.ok(savedUser);
    }
    
    @GetMapping("/getTeams")
    public List<TeamModel> getTeams() {
        return teamService.listAllTeams();
    }
}
