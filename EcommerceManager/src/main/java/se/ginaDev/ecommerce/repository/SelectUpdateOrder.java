package se.ginaDev.ecommerce.repository;

import java.sql.ResultSet;
import se.ginaDev.ecommerce.model.Order;

public final class SelectUpdateOrder extends SelectUpdateQuery<Order>{

	@Override
	protected Order mapRow(ResultSet resultSet) throws Exception {
		return new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
	}

}
