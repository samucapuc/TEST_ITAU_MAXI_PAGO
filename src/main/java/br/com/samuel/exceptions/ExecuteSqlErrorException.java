package br.com.samuel.exceptions;

public class ExecuteSqlErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExecuteSqlErrorException(String msg) {
		super(msg);
	}
	
	public ExecuteSqlErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
