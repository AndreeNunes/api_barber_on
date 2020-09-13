package br.com.api.barberon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.api.barberon.connection.ConnectionFactory;
import br.com.api.barberon.entity.Login;


public class LoginDAO {
	Connection connection;
	
	public LoginDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Login validaAcessoAoApp(Login login) {
		boolean logado = false;
		
		String sql = "SELECT *"
				+ " FROM bo_usuario"
				+ " WHERE login_usuario = ? AND senha_usuario = MD5(?)"; 
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, login.getUsuarioLogin());	
			stmt.setString(2, login.getSenhaLogin());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				logado = true;
				login.setIdLogin(rs.getInt("id_usuario"));
				login.setStatusLogin("200");
				login.setMensagemLogin("O usuario foi logado com sucesso");
			}
			
			if(!logado) {
				login.setStatusLogin("400");
				login.setMensagemLogin("O usuario não foi encontrado");
			}
			
			return login;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
