package se.ginaDev.ecommerce.repository;

import java.util.List;

import se.ginaDev.ecommerce.model.Order;
import se.ginaDev.ecommerce.model.Product;
import se.ginaDev.ecommerce.model.User;

public interface Repository
{
	List<Product> getAllProducts() throws Exception;

	Product getProductById(int id) throws Exception;

	Product addProduct(Product product) throws Exception;

	Product updateProduct(Product product) throws Exception;

	Product removeProduct(Product product) throws Exception;
	
	User getUserById(int id) throws Exception;

	User addUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	User removeUser(User user) throws Exception;
	
	User loginUser(String userName, String userPass) throws Exception;
	
	Order AddOrder(Order order) throws Exception;

	List<Order> getAllOrders(User user) throws Exception;

	Order getOrderById(int id) throws Exception;

	Order updateOrder(Order order) throws Exception;

	Order removeOrder(Order order) throws Exception;
	
}
