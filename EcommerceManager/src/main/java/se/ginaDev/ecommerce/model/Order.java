package se.ginaDev.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public final class Order
{

	private final int orderId;
	private final String orderStatus;
	private final int userId;
	private final List<Product> productList;

	public Order(int orderId, int userId, String orderStatus, List<Product> productList)
	{
		this.orderId = orderId;
		this.userId = userId;
		this.orderStatus = orderStatus;
		this.productList = productList;
	}

	public Order(int orderId, String orderStatus, int userId)
	{
		this.orderId = orderId;
		this.userId = userId;
		this.orderStatus = orderStatus;
		productList = new ArrayList<>();
	}

	public List<Product> getProductList()
	{
		return productList;
	}

	public int getOrderId()
	{
		return orderId;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public int getUserId()
	{
		return userId;
	}
}