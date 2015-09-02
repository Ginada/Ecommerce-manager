package se.ginaDev.ecommerce.repository.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = -7034718854909354355L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}
	

}
