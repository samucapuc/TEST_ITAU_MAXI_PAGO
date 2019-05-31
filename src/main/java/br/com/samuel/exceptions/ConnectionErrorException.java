package br.com.samuel.exceptions;

public class ConnectionErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConnectionErrorException(String msg) {
		super(msg);
	}
	
	public ConnectionErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
