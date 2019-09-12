package com.teste.response;

public class Response<T> {

	private T data;
	private String status;
	private Exception error;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
		setStatus("Sucesso");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Exception getError() {
		return error;
	}

	public void setError(Exception error) {
		this.error = error;
		setStatus("Falha");
	}

	public Response() {

	}

}
