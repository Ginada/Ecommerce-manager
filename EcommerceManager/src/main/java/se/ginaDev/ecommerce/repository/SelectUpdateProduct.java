package se.ginaDev.ecommerce.repository;

import java.sql.ResultSet;
import se.ginaDev.ecommerce.model.Product;

public final class SelectUpdateProduct extends SelectUpdateQuery<Product>{

	@Override
	protected Product mapRow(ResultSet resultSet) throws Exception {
		return new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
	}

}
