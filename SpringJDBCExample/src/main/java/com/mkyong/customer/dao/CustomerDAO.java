package com.mkyong.customer.dao;

import com.mkyong.customer.model.Customer;

public interface CustomerDAO {
	public void insert(Customer customer);

	public void update(Customer customer);

	public Boolean transaction(Customer customer1, int locationID, double amount);

	public Customer findByCustomerId(int custId);

	public void log(int id, String action);

	public String logging(int id);

}
