package com.teste.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConexaoModel {

	public Connection conexaoUsuario() throws Exception {
		String url = "jdbc:postgresql://localhost:5432/teste";
		String usuario = "postgres";
		String senha = "postgres";

		Class.forName("org.postgresql.Driver");
		Connection conexao = DriverManager.getConnection(url, usuario, senha);

		return conexao;
	}

	private void createTableFuncionario() {
		try {
			System.out.println("CRIAR TABLE");
			Connection conexao = conexaoUsuario();
			String sql = "CREATE TABLE funcionario(_id serial NOT NULL,\r\n"
					+ "					nome character varying(30) not null,\r\n"
					+ "					sobrenome character varying(50) not null,\r\n"
					+ "					email character varying(255) not null,\r\n"
					+ "					nis numeric not null,\r\n"
					+ "					dataCadastro date default now(),\r\n"
					+ "					CONSTRAINT cadastro_pkey PRIMARY KEY (_id)) ";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.executeQuery();

		} catch (Exception e) {

		}
	}

	public void createDatabase() throws Exception {
		try {

			String url = "jdbc:postgresql://localhost:5432/";
			String usuario = "postgres";
			String senha = "postgres";

			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			PreparedStatement stmt = conexao.prepareStatement("CREATE DATABASE teste");
			stmt.execute();

			createTableFuncionario();
			
		} catch (Exception e) {

		}
	}
}