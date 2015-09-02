package se.ginaDev.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import se.ginaDev.ecommerce.model.Order;
import se.ginaDev.ecommerce.model.Product;
import se.ginaDev.ecommerce.model.User;
import se.ginaDev.ecommerce.repository.Repository;
import se.ginaDev.ecommerce.repository.SQLRepository;
import se.ginaDev.ecommerce.repository.exception.EcommerceManagerException;

public final class EcommerceManager implements Repository
{

	final private Repository repository;

	public EcommerceManager()
	{
		this.repository = new SQLRepository();
	}

	public Product addProduct(Product product) throws EcommerceManagerException
	{
		try
		{
			return repository.addProduct(product);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("the product could not be added", e);
		}

	}

	public ArrayList<Product> getAllProducts() throws EcommerceManagerException
	{
		try
		{
			return (ArrayList<Product>) repository.getAllProducts();
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not get product", e);
		}
	}

	public Product getProductById(int id) throws EcommerceManagerException
	{
		try
		{
			return repository.getProductById(id);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not get product", e);
		}
	}

	@Override
	public Product updateProduct(Product product) throws EcommerceManagerException
	{
		try
		{	
			return repository.updateProduct(product);
			
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not update product", e);
		}
	}

	@Override
	public Product removeProduct(Product product) throws EcommerceManagerException
	{
		try
		{
			return repository.removeProduct(product);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not remove product", e);
		}
	}

	@Override
	public User getUserById(int id) throws EcommerceManagerException
	{
		try
		{
			return repository.getUserById(id);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not get Product", e);
		}
	}

	@Override
	public User addUser(User user) throws EcommerceManagerException
	{
		try
		{
			return repository.addUser(user);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not add user", e);
		}
	}

	@Override
	public User updateUser(User user) throws EcommerceManagerException
	{
		try
		{
			return repository.updateUser(user);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not add user", e);
		}
	}

	@Override
	public User removeUser(User user) throws EcommerceManagerException
	{
		try
		{
			return repository.removeUser(user);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not remove user", e);
		}
	}

	@Override
	public Order AddOrder(Order order) throws EcommerceManagerException
	{
		try
		{
			return repository.AddOrder(order);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not remove user", e);
		}
	}

	@Override
	public List<Order> getAllOrders(User user) throws EcommerceManagerException
	{
		try
		{
			return repository.getAllOrders(user);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not get orders", e);
		}
	}

	@Override
	public Order getOrderById(int id) throws EcommerceManagerException
	{
		try
		{
			return repository.getOrderById(id);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not get order", e);
		}
	}

	@Override
	public Order updateOrder(Order order) throws EcommerceManagerException
	{
		try
		{
			return repository.updateOrder(order);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not update order", e);
		}
	}

	@Override
	public Order removeOrder(Order order) throws EcommerceManagerException
	{
		try
		{
			return repository.removeOrder(order);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("could not remove order", e);
		}
	}

	@Override
	public User loginUser(String userName, String userPass) throws Exception
	{
		try
		{
			return repository.loginUser(userName, userPass);
		}
		catch (Exception e)
		{
			throw new EcommerceManagerException("couldn't authenticate the user", e);
		}
	}

}
