package br.com.concrete.teste.handlers;

import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("OOOOO");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write("{mensagem : 'Não Autorizado'}");
    }
}
