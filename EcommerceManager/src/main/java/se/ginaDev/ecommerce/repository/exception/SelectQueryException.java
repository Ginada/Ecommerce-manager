package se.ginaDev.ecommerce.repository.exception;

public class SelectQueryException extends Exception{

	private static final long serialVersionUID = 3912287252666401189L;

	public SelectQueryException() {
		super();
	}

	public SelectQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public SelectQueryException(String message) {
		super(message);
	}

	public SelectQueryException(Throwable cause) {
		super(cause);
	}
	

}
