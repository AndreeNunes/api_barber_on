package br.com.api.barberon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.api.barberon.dao.LoginDAO;
import br.com.api.barberon.entity.Login;

@CrossOrigin(origins = "localhost:3000", maxAge = 3000)
@RestController
@RequestMapping("/barberon/login")
public class LoginController {
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste(){
		return new ResponseEntity<>("TESTE", HttpStatus.OK);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> logar(Login login){
		Gson gson = new Gson();
		String json;
		Login l = new Login();
		LoginDAO dao = new LoginDAO();
		l = dao.validaAcessoAoApp(login);
		json = gson.toJson(l);
		
		System.out.println("FOOOOOOI");
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
}
