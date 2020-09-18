package br.com.api.barberon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/teste")	
	public ResponseEntity<String> teste(){
		return new ResponseEntity<>("TESTE", HttpStatus.OK);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String logar(@RequestBody LoginDTO login){
		
		System.out.println(login.getSenha());
		System.out.println(login.getUsuario());
		
		Gson gson = new Gson();
		String json;
		Login l = new Login();
		LoginDAO dao = new LoginDAO();
		l = dao.validaAcessoAoApp(login);
		json = gson.toJson(l);
		
		System.out.println(l);
		
		return json;
	}
}
