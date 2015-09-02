package se.ginaDev.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import se.ginaDev.ecommerce.repository.exception.SelectQueryException;

public abstract class SQLQuery<T> extends SQLConnection
{
	protected String queryString;
	protected String[] queryList;

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	public void resetQueryList()
	{
		this.queryList = null;
	}

	public Collection<T> executeQuery()
	{
		try (final Connection connection = getConnection())
		{
			// Get query string from subclass
			PreparedStatement statement = connection.prepareStatement(getQueryString());

			// Get parameters from subclass. Every key is a parameter index
			// and every value is a parameter value
			Map<Integer, Object> parameters = getParameters();

			for (Map.Entry<Integer, Object> parameter : parameters.entrySet())
			{
				statement.setObject(parameter.getKey(), parameter.getValue());
			}

			Collection<T> result = new ArrayList<>();

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				result.add(mapRow(resultSet));
			}
			resetQueryList();
			return result;
		}
		catch (Exception e)
		{
			resetQueryList();
			throw new RuntimeException("Could not execute query", e);
		}
	}

	public int updateQuery()
	{
		try (final Connection connection = getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(getQueryString());

			Map<Integer, Object> parameters = getParameters();

			for (Map.Entry<Integer, Object> parameter : parameters.entrySet())
			{
				statement.setObject(parameter.getKey(), parameter.getValue());
			}
			resetQueryList();
			return statement.executeUpdate();

		}
		catch (Exception e)
		{
			resetQueryList();
			throw new RuntimeException("Could not execute query", e);
		}
	}

	protected Map<Integer, Object> getParameters()
	{
		return new HashMap<Integer, Object>();
	}

	protected abstract T mapRow(ResultSet resultSet) throws Exception;

	protected abstract String getQueryString() throws SelectQueryException;
}
