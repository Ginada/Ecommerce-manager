package se.ginaDev.ecommerce.repository;

import java.util.ArrayList;


import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import se.ginaDev.ecommerce.model.Order;
import se.ginaDev.ecommerce.model.Product;
import se.ginaDev.ecommerce.model.User;
import se.ginaDev.ecommerce.repository.SQLRepository;
import se.ginaDev.ecommerce.repository.exception.EcommerceManagerException;
import se.ginaDev.ecommerce.repository.exception.SQLRepositoryException;
import se.ginaDev.ecommerce.service.EcommerceManager;

public final class SQLRepositoryTest
{
//
//	@Test
//	public void shouldReturnListOfProducts() throws SQLRepositoryException
//	{
//		final SQLRepository repository = new SQLRepository();
//		final List<Product> listofProducts = repository.getAllProducts();
//		assertThat(listofProducts, is(notNullValue()));
//		assertThat(listofProducts.size(), is(7));
//		assertThat(listofProducts.get(0).getProductName(), is("pen"));
//	}

	@Test
	public void shouldReturnProductById() throws SQLRepositoryException
	{
		final SQLRepository repository = new SQLRepository();
		final Product product = repository.getProductById(102);
		assertThat(product, is(notNullValue()));
		assertThat(product.getProductName(), is("scissors"));
	}
	
	@Test (expected = SQLRepositoryException.class)
	public void shouldThrowExceptionProductExcist() throws SQLRepositoryException{
		final SQLRepository repository = new SQLRepository();
		final Product product = repository.getProductById(102);
		repository.addProduct(new Product(108, product.getProductName(),"active"));
	}

	@Test
	public void shouldUpdateProduct() throws SQLRepositoryException, EcommerceManagerException
	{
		
		final Product product = new Product(109, "erase6", "active");
	
		EcommerceManager manager = new EcommerceManager();	
		manager.updateProduct(product);
		String name = manager.getProductById(109).getProductName();
		
		assertThat(name,is("erase6"));
	}

	// @Test
	// public void shouldAddNewProduct()
	// {
	// final SQLRepository repository = new SQLRepository();
	// final Product product = new Product(106, "letter", "active");
	// repository.addProduct(product);
	// assertThat(repository.getProductById(106), is(notNullValue()));
	// assertThat(repository.getProductById(106).getProductName(),
	// is("letter"));
	// }
//	@Test
//	public void shouldRemoveProduct() throws SQLRepositoryException
//	{
//		final SQLRepository repository = new SQLRepository();
//		Product product = repository.getProductById(110);
//		assertThat(repository.removeProduct(product).getStatus(), is("inactive"));
//	}
	
	@Test
	public void shouldAuthenticateUser() throws Exception
	{
		final SQLRepository repository = new SQLRepository();
		assertThat(repository.loginUser("Gandalf", "pass1"), is(notNullValue()));
	}

	@Test(expected = SQLRepositoryException.class)
	public void shouldThrowFailToLoginException() throws Exception
	{
		final SQLRepository repository = new SQLRepository();
		assertThat(repository.loginUser("Gandalf", "gandalf"), is(notNullValue()));
	}

	@Test
	public void shouldReturnUserbyId() throws SQLRepositoryException
	{
		final SQLRepository repository = new SQLRepository();
		assertThat(repository.getUserById(1000), is(notNullValue()));
		assertThat(repository.getUserById(1000).getUsername(), is("Gandalf"));
	}

	// @Test
	// public void shouldAddUser()
	// {
	// final SQLRepository repository = new SQLRepository();
	// User user = new User(1004, "smaug", "gold", "active" );
	// assertThat(repository.addUser(user), is(notNullValue()));
	// assertThat(repository.getUserById(1004).getUsername(), is("smaug"));
	// }
//	@Test
//	public void shouldUpdateUser() throws SQLRepositoryException
//	{
//		final SQLRepository repository = new SQLRepository();
//		repository.updateUser(new User(1002, "Mario", "mushroom2", "active"));
//		assertThat(repository.getUserById(1002).getUsername(), is("Mario"));
//		assertThat(repository.getUserById(1002).getPassword(), is("mushroom2"));
//
//	}

//	@Test
//	public void shouldRemoveUser() throws SQLRepositoryException
//	{
//		final SQLRepository repository = new SQLRepository();
//		repository.removeUser(new User(1001, "Link", "pass1", "active"));
//		assertThat(repository.getUserById(1002).getStatus(), is("inactive"));
//	}

	//
	// @Test
	// public void shouldAddNewOrder()
	// {
	// final SQLRepository repository = new SQLRepository();
	//
	// ArrayList<Product> productList = new ArrayList<>();
	// productList.add(repository.getProductById(101));
	// productList.add(repository.getProductById(103));
	// productList.add(repository.getProductById(105));
	// Order order = new Order(15, 1002, "active", productList);
	//
	// repository.AddOrder(order);
	// assertThat(order, is(notNullValue()));
	// assertThat(order.getProductList().size(), is(3));
	// assertThat(order.getUserId(), is(1002));
	// assertThat(order.getProductList().get(0).getProductName(), is("papper"));
	// }

	@Test
	public void shouldReturnListOfOrders() throws SQLRepositoryException
	{
		final SQLRepository repository = new SQLRepository();
		User user = repository.getUserById(1001);
		assertThat(repository.getAllOrders(user).size(), is(2));
		assertThat(repository.getAllOrders(user).get(1).getOrderId(), is(12));
	}

	@Test
	public void shouldReturnOrder() throws SQLRepositoryException
	{
		final SQLRepository repository = new SQLRepository();
		Order order = repository.getOrderById(10);
		assertThat(order, is(notNullValue()));
		assertThat(order.getProductList().size(), is(3));
		assertThat(order.getProductList().get(0).getProductId(), is(100));
	}

	@Test
	public void shouldUpdateOrder() throws SQLRepositoryException
	{
		final SQLRepository repository = new SQLRepository();
		ArrayList<Product> productList = new ArrayList<>();
		productList.add(repository.getProductById(101));
		productList.add(repository.getProductById(102));
		productList.add(repository.getProductById(105));
		productList.add(repository.getProductById(106));
		Order order = repository.getOrderById(12);
		repository.updateOrder(new Order(order.getOrderId(), order.getUserId(), order.getOrderStatus(), productList));
		assertThat(repository.getOrderById(12).getProductList().get(2).getProductId(), is(105));
		assertThat(repository.getOrderById(12).getProductList().size(), is(4));
	}

//	@Test
//	public void shouldInactivateOrder() throws SQLRepositoryException
//	{
//		final SQLRepository repository = new SQLRepository();
//		Order order = repository.getOrderById(11);
//		assertThat(repository.removeOrder(order).getOrderStatus(), is("inactive"));
//	}
}
