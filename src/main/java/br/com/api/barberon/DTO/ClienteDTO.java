package br.com.api.barberon.DTO;

import lombok.Data;

@Data
public class ClienteDTO {
	int idCliente;
	String nomeCliente;
	String telefoneCliente;
	String cpfCliente;
	int idBarbearia;
}
