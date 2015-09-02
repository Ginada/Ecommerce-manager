package se.ginaDev.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import se.ginaDev.ecommerce.model.Order;
import se.ginaDev.ecommerce.model.Product;
import se.ginaDev.ecommerce.model.User;
import se.ginaDev.ecommerce.repository.exception.SQLRepositoryException;

public final class SQLRepository implements Repository
{

	private SelectUpdateProduct productQuery = new SelectUpdateProduct();
	private SelectUpdateUser userQuery = new SelectUpdateUser();
	private SelectUpdateOrder orderQuery = new SelectUpdateOrder();

	@Override
	public List<Product> getAllProducts() throws SQLRepositoryException
	{
		try
		{
			return (ArrayList<Product>) productQuery.setQuery(
					"SELECT * FROM Products WHERE Product_status = ?", "active")
					.executeQuery();
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get query", e);
		}
	}

	@Override
	public Product getProductById(int id) throws SQLRepositoryException
	{
		try
		{
			return ((ArrayList<Product>) productQuery.setQuery(
					"SELECT * FROM Products WHERE Product_id = ? and product_status = ?",
					Integer.toString(id), "active").executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get product", e);
		}
	}

	@Override
	public Product addProduct(Product product) throws SQLRepositoryException
	{
		try
		{
			Product dBProduct = ((List<Product>) productQuery.setQuery("SELECT * FROM Products WHERE Product_name = ?", product.getProductName()).executeQuery()).get(0);
			throw new SQLRepositoryException("the product with " + dBProduct + " already excist");
		}
		catch (Exception exception)
		{
			try
			{
				productQuery
						.setQuery(
								"INSERT INTO User.Products (product_id, product_name, product_status) VALUES (?,?,?)",
								Integer.toString(product.getProductId()),
								product.getProductName(), "active")
						.updateQuery();
				return ((ArrayList<Product>) productQuery.setQuery(
						"SELECT * FROM Products WHERE Product_id = ?",
						Integer.toString(product.getProductId())).executeQuery())
						.get(0);
			}
			catch (Exception e)
			{
				throw new SQLRepositoryException("could not add product", e);
			}
		}

	}

	@Override
	public Product updateProduct(Product product) throws SQLRepositoryException
	{
		try
		{
			productQuery.setQuery(
					"UPDATE Products SET product_name = ? WHERE Product_id = ? and product_status = ?",
					product.getProductName(), Integer.toString(product.getProductId()), "active").updateQuery();
		
			return ((ArrayList<Product>) productQuery.setQuery(
					"SELECT * FROM Products WHERE Product_id = ?",
					Integer.toString(product.getProductId())).executeQuery())
					.get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not update product", e);
		}
	}

	@Override
	public Product removeProduct(Product product) throws SQLRepositoryException
	{

		try
		{
			productQuery.setQuery(
					"UPDATE Products SET product_status = ? WHERE Product_id = ?",
					"inactive", Integer.toString(product.getProductId()))
					.updateQuery();
			return ((ArrayList<Product>) productQuery.setQuery(
					"SELECT * FROM Products WHERE Product_id = ?",
					Integer.toString(product.getProductId())).executeQuery())
					.get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not remove product", e);
		}
	}

	@Override
	public User getUserById(int id) throws SQLRepositoryException
	{
		try
		{
			return ((ArrayList<User>) userQuery.setQuery(
					"SELECT * FROM Users WHERE User_id = ? and user_status = ?", Integer.toString(id), "active" )
					.executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get user", e);
		}
	}

	@Override
	public User addUser(User user) throws SQLRepositoryException
	{
		try
		{
			User dBUser = ((List<User>) userQuery.setQuery("SELECT * FROM Users WHERE user_name = ?", user.getUsername()).executeQuery()).get(0);
			throw new SQLRepositoryException("the product with " + dBUser + " already excist");
		}
		catch (Exception exception)
		{
		try
		{
			userQuery.setQuery(
					"INSERT INTO Users (user_id, user_name, user_password, user_status) VALUES (?,?,?,?)",
					Integer.toString(user.getUserId()), user.getUsername(),
					user.getPassword(), "active").updateQuery();
			return ((ArrayList<User>) userQuery.setQuery(
					"SELECT * FROM Users WHERE user_id = ?",
					Integer.toString(user.getUserId())).executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get user", e);
		}}
	}

	@Override
	public User updateUser(User user) throws SQLRepositoryException
	{	
		try
		{
			userQuery.setQuery(
					"UPDATE Users SET user_name = ? WHERE user_id = ? and user_status = ?",
					user.getUsername(), Integer.toString(user.getUserId()), "active")
					.updateQuery();
			userQuery.setQuery(
					"UPDATE Users SET user_password = ? WHERE user_id = ? and user_status = ?",
					user.getPassword(), Integer.toString(user.getUserId()), "active")
					.updateQuery();
			return ((ArrayList<User>) userQuery.setQuery(
					"SELECT * FROM Users WHERE user_id = ?",
					Integer.toString(user.getUserId())).executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not update user", e);
		}
	}

	@Override
	public User removeUser(User user) throws SQLRepositoryException
	{
		try
		{
			userQuery.setQuery(
					"UPDATE Users SET user_status = ? WHERE user_id = ?",
					"inactive", Integer.toString(user.getUserId()))
					.updateQuery();
			return ((ArrayList<User>) userQuery.setQuery(
					"SELECT * FROM Users WHERE user_id = ?",
					Integer.toString(user.getUserId())).executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not remove user", e);
		}
	}
	
	@Override
	public User loginUser(String userName, String userPassword) throws Exception
	{
		try{
			return ((ArrayList<User>) userQuery.setQuery(
					"SELECT * FROM Users WHERE user_name = ? and user_password = ? and user_status = ?;",
					userName, userPassword, "active").executeQuery()).get(0);
		} catch (Exception e)
		{
			throw new SQLRepositoryException("could not Authenticate user", e);
		}
	}

	@Override
	public Order AddOrder(Order order) throws SQLRepositoryException
	{
		try
		{	
			orderQuery.setQuery(
					"INSERT INTO Orders (order_id, order_status, user_id) VALUES (?,?,?)",
					Integer.toString(order.getOrderId()),
					"active",
					Integer.toString(order.getUserId())).updateQuery();
			for (Product product : order.getProductList())
			{
				orderQuery.setQuery(
						"INSERT INTO Orders_Products (order_id, product_id) VALUES (?,?)",
						Integer.toString(order.getOrderId()),
						Integer.toString(product.getProductId()))
						.updateQuery();
			}
			Order dbOrder = ((ArrayList<Order>) orderQuery.setQuery(
					"SELECT * FROM Orders WHERE order_id = ?",
					Integer.toString(order.getOrderId())).executeQuery()).get(0);

			ArrayList<Product> productList = (ArrayList<Product>) productQuery.setQuery(
					"SELECT * FROM products INNER JOIN orders_products ON products.product_id = orders_products.product_id WHERE order_id =?",
					Integer.toString(order.getOrderId())).executeQuery();

			return new Order(dbOrder.getOrderId(), dbOrder.getUserId(), dbOrder.getOrderStatus(), productList);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not add order", e);
		}
	}

	@Override
	public List<Order> getAllOrders(User user) throws SQLRepositoryException
	{
		ArrayList<Order> finalOrderList = new ArrayList<>();
		try
		{
			ArrayList<Order> orderList = ((ArrayList<Order>) orderQuery.setQuery(
					"SELECT * FROM Orders WHERE user_id = ? and order_status = ?",
					Integer.toString(user.getUserId()), "active").executeQuery());
			for (Order order : orderList)
			{
				ArrayList<Product> productList = (ArrayList<Product>) productQuery
						.setQuery(
								"SELECT products.product_id, products.product_name, products.product_status FROM products INNER JOIN orders_products ON products.product_id = orders_products.product_id WHERE order_id =?",
								Integer.toString(order.getOrderId())).executeQuery();

				finalOrderList.add(new Order(order.getOrderId(), order.getUserId(),
						order.getOrderStatus(), productList));
			}
			return finalOrderList;
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could execute query", e);
		}
	}

	@Override
	public Order getOrderById(int id) throws SQLRepositoryException
	{
		try
		{
			Order order = ((ArrayList<Order>) orderQuery.setQuery(
					"SELECT * FROM Orders WHERE Order_id = ? and order_status = ?",
					Integer.toString(id), "active").executeQuery()).get(0);
			ArrayList<Product> productList = (ArrayList<Product>) productQuery
					.setQuery(
							"SELECT products.product_id, products.product_name, products.product_status FROM products INNER JOIN orders_products ON products.product_id = orders_products.product_id WHERE order_id =?",
							Integer.toString(order.getOrderId())).executeQuery();
			return new Order(order.getOrderId(), order.getUserId(), order.getOrderStatus(), productList);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get order", e);
		}
	}

	@Override
	public Order updateOrder(Order order) throws SQLRepositoryException
	{
		try
		{
			orderQuery.setQuery(
					"DELETE FROM Orders_Products Where order_id = ?",
					Integer.toString(order.getOrderId())).updateQuery();
			for (Product product : order.getProductList())
			{
				orderQuery.setQuery("INSERT INTO Orders_Products(order_id, product_id) VALUES(?,?)", Integer.toString(order.getOrderId()),
						Integer.toString(product.getProductId())).updateQuery();
			}
			return getOrderById(order.getOrderId());
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not update order", e);
		}
	}

	@Override
	public Order removeOrder(Order order) throws SQLRepositoryException
	{
		try
		{
			orderQuery.setQuery(
					"UPDATE Orders SET order_status = ? WHERE order_id = ?", "inactive", Integer.toString(order.getOrderId())).updateQuery();
			return ((ArrayList<Order>) orderQuery.setQuery(
					"SELECT * FROM Orders WHERE Order_id = ?",
					Integer.toString(order.getOrderId())).executeQuery()).get(0);
		}
		catch (Exception e)
		{
			throw new SQLRepositoryException("could not get order", e);
		}
	}



}
