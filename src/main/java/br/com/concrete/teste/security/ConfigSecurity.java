package br.com.concrete.teste.security;

import br.com.concrete.teste.filters.AuthFilter;
import br.com.concrete.teste.handlers.Custom403AccessDeniedHandler;
import br.com.concrete.teste.handlers.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .authorizeRequests()
                .antMatchers( "/register/user**").permitAll()
                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new Custom403AccessDeniedHandler())
                .and()

                // filtra outras requisições para verificar a presença do JWT no header
                .addFilterBefore(new AuthFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
