package se.ginaDev.ecommerce.model;

public final class User
{

	private final int userId;

	private final String username;

	private final String password;

	private final String status;

	public User(int userId, String username, String password, String status)
	{
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public int getUserId()
	{
		return userId;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getStatus()
	{
		return status;
	}
}
