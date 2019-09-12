package com.teste.resource;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.controller.FuncionarioController;
import com.teste.entity.Funcionario;
import com.teste.response.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioResource {
	@GetMapping(path = "/buscar")
	public ResponseEntity<Response<java.util.List<Funcionario>>> buscar() {
		System.out.println("REQUEST BUSCAR");
		Response<java.util.List<Funcionario>> response = new Response<java.util.List<Funcionario>>();
		try {
			FuncionarioController funcionarioController = new FuncionarioController();
			java.util.List<Funcionario> funcionarios = funcionarioController.getAll();
			response.setData(funcionarios);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.setError(e);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@PostMapping(path = "/salvar")
	public ResponseEntity<Response<String>> salvar(@Valid @RequestBody Funcionario funcionario) {
		System.out.println("REQUEST SALVAR");
		Response<String> response = new Response<String>();
		try {
			FuncionarioController funcionarioController = new FuncionarioController();
			funcionarioController.salvar(funcionario);
			response.setData("Sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.setError(e);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping(path = "/deletar/{id}")
	public  ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) {
		System.out.println("REQUEST DELETAR");
		Response<String> response = new Response<String>();
		try {
			FuncionarioController funcionarioController = new FuncionarioController();
			funcionarioController.deletar(id);
			response.setData("Sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
				response.setError(e);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
}
