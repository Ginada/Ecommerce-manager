package se.ginaDev.ecommerce.repository.exception;

public class SQLRepositoryException extends Exception
{
	private static final long serialVersionUID = -5905244292350656099L;

	public SQLRepositoryException()
	{
		super();
	}

	public SQLRepositoryException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SQLRepositoryException(String message)
	{
		super(message);
	}

	public SQLRepositoryException(Throwable cause)
	{
		super(cause);
	}
	
	
}
