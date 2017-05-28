package com.mkyong.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		Customer customer = new Customer(2, "John UPDATED", "Snow", "NY", "2 Jan 1968", "pass", 500);
		customerDAO.update(customer);
		customerDAO.transaction(customer, 3, 50);
		Customer customer1 = customerDAO.findByCustomerId(3);
		System.out.println(customer1);

	}
}
