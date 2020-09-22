package br.com.api.barberon.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.Gson;

import br.com.api.barberon.DTO.LoginDTO;
import br.com.api.barberon.dao.LoginDAO;
import br.com.api.barberon.entity.Login;

@EnableWebMvc
@RestController
@RequestMapping("/barberon/login")
public class LoginController implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// <- assim permite de qualquer origem, troque "/**" pelo seu dominio por exemplo "http://meudominio.com"
        .allowedMethods("GET", "POST", "PUT");
	}
	
	@PostMapping
	@ResponseStatus
	public String logar(@RequestBody LoginDTO login){
		Gson gson = new Gson();
		String json;
		Login l = new Login();
		LoginDAO dao = new LoginDAO();
		
		l = dao.validaAcessoAoApp(login);
		json = gson.toJson(l);
		
		return json;
	}
}
