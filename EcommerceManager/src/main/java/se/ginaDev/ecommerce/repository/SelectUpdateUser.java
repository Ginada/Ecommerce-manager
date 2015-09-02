package se.ginaDev.ecommerce.repository;

import java.sql.ResultSet;

import se.ginaDev.ecommerce.model.User;

public final class SelectUpdateUser extends SelectUpdateQuery<User>
{

	@Override
	protected User mapRow(ResultSet resultSet) throws Exception
	{
		return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
	}

}
