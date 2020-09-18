package br.com.api.barberon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.api.barberon.DTO.LoginDTO;
import br.com.api.barberon.connection.ConnectionFactory;
import br.com.api.barberon.entity.Login;


public class LoginDAO {
	Connection connection;
	
	public LoginDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Login validaAcessoAoApp(LoginDTO login) {
		boolean logado = false;
		Login l = new Login();
		
		String sql = "SELECT *"
				+ " FROM bo_usuario"
				+ " WHERE login_usuario = ? AND senha_usuario = MD5(?)"; 
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, login.getUsuario());	
			stmt.setString(2, login.getSenha());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				logado = true;
				l.setIdLogin(rs.getInt("id_usuario"));
				l.setStatusLogin("200");
				l.setMensagemLogin("O usuario foi logado com sucesso");
			}
			
			if(!logado) {
				l.setStatusLogin("412");
				l.setMensagemLogin("O usuario não foi encontrado");
			}
			
			return l;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
