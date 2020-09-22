package br.com.api.barberon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import br.com.api.barberon.DTO.ClienteDTO;
import br.com.api.barberon.dao.ClienteDAO;
import br.com.api.barberon.entity.Retorno;

@EnableWebMvc
@RestController
@RequestMapping("/barberon/clientes")
@CrossOrigin
public class ClienteController {
	
	ClienteDAO dao;
	
	//cadastro, editar, deletar, listar
	
	@PostMapping
	public ResponseEntity<String> cadastro(@RequestBody ClienteDTO cliente) {
		dao = new ClienteDAO();
		Retorno retorno = new Retorno();
		Gson gson = new Gson();
		
		try {
			retorno = dao.cadastroCliente(cliente);
			
			String json = gson.toJson(retorno);
			
			return new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
	}
	
	@GetMapping("/lista")
	public List<ClienteDTO> lista(@RequestParam("idBarbearia") int idBarbearia) {
		dao = new ClienteDAO();
		return dao.lista(idBarbearia);
	}
}
