package br.com.api.barberon.entity;

import lombok.Data;

@Data
public class Login {
	int idLogin;
	String usuarioLogin;
	String senhaLogin;
	String statusLogin;
	String mensagemLogin;
}
