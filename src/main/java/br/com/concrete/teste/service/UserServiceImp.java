package br.com.concrete.teste.service;

import br.com.concrete.teste.model.User;
import br.com.concrete.teste.repository.UserRepository;
import br.com.concrete.teste.util.Util;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Util util;

    private BCryptPasswordEncoder encoder;

    @Autowired TokenService tokenService;

    public UserServiceImp(){
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public User create(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setLast_login(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Boolean checkLastLogin(User user){
        LocalDateTime lastLogin = this.userRepository.getLastLogin(user.getId());
        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(lastLogin, now);
        long hours = ChronoUnit.HOURS.between(lastLogin, now);
        long days = ChronoUnit.DAYS.between(lastLogin, now);
        System.out.println("last login "+lastLogin);
        System.out.println("minutos "+minutes);
        System.out.println("hrs "+hours);
        System.out.println("days "+days);
        Duration duration = Duration.between(lastLogin, now);
        System.out.println("Duracao "+Math.abs(duration.toMinutes()));
        return (Math.abs(duration.toMinutes())<=30);
    }



    public Boolean checkEmail(String email) {
        return this.userRepository.countByEmail(email) > 0;
    }

    @Override
    public User login(String email, String password) {
        User user = this.userRepository.findByEmail(email);
        if(this.encoder.matches(password,user.getPassword())){
            String token = tokenService.getToken(user.getEmail());
            user.setToken(token);
            user.setLast_login(LocalDateTime.now());
            this.userRepository.save(user);
            return  user;
        }

        return user;
    }

    @Override
    public User getUser(UUID id) {
        return this.userRepository.findById(id);
    }


}
