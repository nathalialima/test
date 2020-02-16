package br.com.concrete.teste.controller;

import br.com.concrete.teste.model.ApiError;
import br.com.concrete.teste.model.User;
import br.com.concrete.teste.service.UserService;
import br.com.concrete.teste.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postUser(@RequestBody User user){

        if (userService.checkEmail(user.getEmail()))
            return new ResponseEntity<>(new ApiError(Constantes.EXISTING_EMAIL), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

}
