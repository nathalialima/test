package br.com.concrete.teste.service;

import br.com.concrete.teste.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    User create(User user);

    Boolean checkEmail(String email);

    User login(String email, String Password);

    User getUser(UUID id);

    Boolean checkLastLogin(User user);
}
