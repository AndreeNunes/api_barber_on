package br.com.api.barberon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.api.barberon.DTO.RecuperaEmailDTO;
import br.com.api.barberon.util.JavaMailApp;

@RestController
@RequestMapping("barberon/")
public class RecuperaEmail {
	
	@PostMapping("recupera")
	public ResponseEntity<String> recuperaEmail(@RequestBody RecuperaEmailDTO email){
		String json;
		Gson gson = new Gson();
		
		JavaMailApp javaMailApp = new JavaMailApp();
		javaMailApp.recuperar("André Nunes", "andre");
		
		email.setMensagem("Email enviado com sucesso");
		email.setStatus("200");
		
		json = gson.toJson(email);
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	
	
}
