package br.com.concrete.teste.controller;

import br.com.concrete.teste.model.ApiError;
import br.com.concrete.teste.model.User;
import br.com.concrete.teste.service.TokenService;
import br.com.concrete.teste.service.UserService;
import br.com.concrete.teste.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestController
@RequestMapping("/login")
public class LoginController {


    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response){
        if(!this.userService.checkEmail(user.getEmail())) {
            return new ResponseEntity<>(new ApiError(Constantes.INVALID_USER_OR_PASSWORD), HttpStatus.OK);
        }

        User userLogin = this.userService.login(user.getEmail(), user.getPassword());

        if(userLogin.getToken()==null) {
            return new ResponseEntity<>(new ApiError(Constantes.INVALID_USER_OR_PASSWORD), HttpStatus.UNAUTHORIZED);
        }

        this.tokenService.addAuthentication(response, userLogin.getToken());
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }
}
