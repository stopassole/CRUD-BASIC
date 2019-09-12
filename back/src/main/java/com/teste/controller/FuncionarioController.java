package com.teste.controller;

import java.util.List;

import com.teste.entity.Funcionario;
import com.teste.model.ConexaoModel;
import com.teste.model.FuncionarioModel;

public class FuncionarioController {

	public java.util.List<Funcionario> getAll() throws Exception {
		ConexaoModel conexao = new ConexaoModel();
		try {
			// Valida conexao com o banco
			conexao.conexaoUsuario();
		} catch (Exception e) {
			// Caso não tenha conexao com o banco cria o mesmo
			System.out.println("CRIAR DATABASE");
			conexao.createDatabase();
		} finally {
			try {
				System.out.println("BUSCAR");
				FuncionarioModel fModel = new FuncionarioModel();
				List<Funcionario> funcionarios = fModel.buscar();
				return funcionarios;
			} catch (Exception e2) {

			}
		}
		return null;
	}

	public void salvar(Funcionario funcionario) throws Exception {
		FuncionarioModel fModel = new FuncionarioModel();
		fModel.salvar(funcionario);
	}
	

	public void deletar(Long id) throws Exception {
		FuncionarioModel fModel = new FuncionarioModel();
		fModel.deletar(id);
	}
}
