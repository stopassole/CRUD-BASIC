package com.teste.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.teste.entity.Funcionario;

public class FuncionarioModel {

	public void salvar(Funcionario funcionario) throws Exception {
		ConexaoModel conexaoModel = new ConexaoModel();

		try {
			Connection conexao = conexaoModel.conexaoUsuario();
			PreparedStatement stmt = null;
			if (funcionario.getId() == null) {
				stmt = conexao.prepareStatement("INSERT INTO funcionario(nome, sobrenome, email, nis) values(?,?,?,?)");
				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getSobrenome());
				stmt.setString(3, funcionario.getEmail());
				stmt.setFloat(4, funcionario.getNis());
			} else {
				stmt = conexao.prepareStatement(
						"UPDATE funcionario SET nome = ?, sobrenome = ?, email = ?,  nis = ?  WHERE _id = \'"
								+ funcionario.getId() + "\';");
				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getSobrenome());
				stmt.setString(3, funcionario.getEmail());
				stmt.setFloat(4, funcionario.getNis());
			}

			stmt.executeQuery();

			conexao.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Funcionario> buscar() throws Exception {
		ConexaoModel conexaoModel = new ConexaoModel();

		try {
			Connection conexao = conexaoModel.conexaoUsuario();
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM funcionario;");
			ResultSet rs = stmt.executeQuery();

			List<Funcionario> list = new ArrayList<>();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();

				funcionario.setId(Long.valueOf(rs.getString("_id")));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setNis(rs.getLong("nis"));

				list.add(funcionario);
			}

			conexao.close();

			return list;
		} catch (Exception e) {
			return null;// TODO: handle exception
		}
	}

	public void deletar(Long id) throws Exception {
		ConexaoModel conexaoModel = new ConexaoModel();

		try {

			Connection conexao = conexaoModel.conexaoUsuario();

			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM  funcionario WHERE _id =" + id + ";");
			stmt.executeQuery();

			conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
