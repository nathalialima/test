package br.com.concrete.teste.controller;

import br.com.concrete.teste.model.ApiError;
import br.com.concrete.teste.model.User;
import br.com.concrete.teste.service.TokenService;
import br.com.concrete.teste.service.UserService;
import br.com.concrete.teste.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id, HttpServletRequest request){
        User user = this.userService.getUser(id);

        if(user==null){
            return new ResponseEntity<>(new ApiError(Constantes.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
        }

        if(!this.tokenService.getTokenHeader(request).equals(user.getToken())){
            return new ResponseEntity<>(new ApiError(Constantes.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
        }

        if(!this.userService.checkLastLogin(user)){
            return new ResponseEntity<>(new ApiError(Constantes.INVALID_SESSION), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.userService.getUser(id), HttpStatus.ACCEPTED);
    }
}
