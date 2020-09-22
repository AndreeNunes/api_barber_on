package br.com.api.barberon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.api.barberon.DTO.ClienteDTO;
import br.com.api.barberon.connection.ConnectionFactory;
import br.com.api.barberon.entity.Retorno;


public class ClienteDAO {
	Connection connection;
	
	public ClienteDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	
	public Retorno cadastroCliente(ClienteDTO c) {
		Retorno retorno = new Retorno();
		
		String sql = "INSERT INTO bo_clientes (nome_cliente, cpf_ciente, "
				+ "telefone_cliente, fk_empresa_cliente) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, c.getNomeCliente());
			stmt.setString(2, c.getCpfCliente());
			stmt.setString(3, c.getTelefoneCliente());
			stmt.setInt(4, c.getIdBarbearia());
			
			stmt.execute();
			stmt.close();
			
			retorno.setStatus("200");
			retorno.setMensagem("Sucesso ao inserir o cliente");
		} catch (Exception e) {
			retorno.setMensagem(e.toString());
			throw new RuntimeException(e);
		}
		
		return retorno;
	}
	
	public List<ClienteDTO> lista(int idBarbearia) {
		List<ClienteDTO> list = new ArrayList<ClienteDTO>();
		
		String sql = "SELECT * "
				+ "FROM bo_clientes "
				+ "WHERE fk_empresa_cliente = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
				
			stmt.setInt(1,idBarbearia);
			
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				ClienteDTO c = new ClienteDTO();
				c.setIdCliente(rs.getInt("id_cliente"));
				c.setCpfCliente(rs.getString("cpf_ciente"));
				c.setNomeCliente(rs.getString("nome_cliente"));
				c.setTelefoneCliente(rs.getString("telefone_cliente"));
				c.setIdBarbearia(rs.getInt("fk_empresa_cliente"));
				
				list.add(c);
			}	
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return list;
	}
}
