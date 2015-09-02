package se.ginaDev.ecommerce.repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import se.ginaDev.ecommerce.repository.exception.SelectQueryException;

public abstract class SelectUpdateQuery<T> extends SQLQuery<T>
{

	public SelectUpdateQuery<T> setQuery(String queryString, String... queryList)
	{
		this.queryList = queryList;
		setQueryString(queryString);
		return this;
	}

	protected abstract T mapRow(ResultSet resultSet) throws Exception;

	@Override
	protected String getQueryString() throws SelectQueryException
	{
		if (queryString != null)
		{

			return queryString;
		}
		else
		{
			throw new SelectQueryException("use setQuery() to set your query attributes");
		}
	}

	@Override
	protected Map<Integer, Object> getParameters()
	{
		Map<Integer, Object> parameters = new HashMap<>();
		for (int i = 0; i < queryList.length; i++)
		{
			parameters.put(i + 1, queryList[i]);
		}
		return parameters;
	}
}
