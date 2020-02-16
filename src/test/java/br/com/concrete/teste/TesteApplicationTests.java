package br.com.concrete.teste;

import br.com.concrete.teste.model.Phone;
import br.com.concrete.teste.model.User;
import br.com.concrete.teste.repository.UserRepository;
import br.com.concrete.teste.service.TokenService;
import br.com.concrete.teste.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TesteApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Test
    public void createShouldPersistData(){
        User user = new User();
        user.setName("Teste");
        user.setEmail("email@gmail.com");
        user.setPassword("123");
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setDdd("82");
        phone.setNumber("988888888");
        phoneList.add(phone);
        user.setPhones(phoneList);
        User newUser = this.userRepository.save(user);
        Assertions.assertThat(newUser.getId()).isNotNull();
        Assertions.assertThat(newUser.getName()).isEqualTo("Teste");
    }

    @Test
    public void shouldLogin(){
        User userLogin = this.userService.login("email@gmail.com", "123");
        Assertions.assertThat(userLogin.getToken()).isNotNull();
    }

    @Test
    public void shouldCheckWrongEmail(){
        Boolean result = this.userService.checkEmail("teste@teste.com");
        Assertions.assertThat(result).isFalse();
    }


}
