package se.ginaDev.ecommerce.model;

public final class Product
{

	private final int productId;
	private final String productName;
	private final String status;

	public Product(int productId, String productName, String status)
	{
		this.productId = productId;
		this.productName = productName;
		this.status = status;
	}

	public int getProductId()
	{
		return productId;
	}

	public String getProductName()
	{
		return productName;
	}

	public String getStatus()
	{
		return status;
	}

}