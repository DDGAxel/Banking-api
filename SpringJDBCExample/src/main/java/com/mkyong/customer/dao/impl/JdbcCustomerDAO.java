package com.mkyong.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;

public class JdbcCustomerDAO implements CustomerDAO {
	private DataSource dataSource;
	long millis = System.currentTimeMillis();
	java.sql.Date date = new java.sql.Date(millis);

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Customer customer) {

		String sql = "INSERT INTO CUSTOMER "
				+ "(custId, firstName, lastName, address, dateOfBirth, password, ballance) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getDateOfBirth());
			ps.setString(6, customer.getPassword());
			ps.setDouble(7, customer.getBallance());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public Customer findByCustomerId(int custId) {

		String sql = "SELECT * FROM CUSTOMER WHERE custId = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getInt("custId"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("dateOfBirth"), rs.getString("password"),
						rs.getDouble("ballance"));
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void update(Customer customer) {

		String sql = "UPDATE CUSTOMER "
				+ " SET custId=?, firstName=?, lastName=?, address=?, dateOfBirth=?, password=?, ballance=?"
				+ "WHERE custId=" + customer.getCustId();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getDateOfBirth());
			ps.setString(6, customer.getPassword());
			ps.setDouble(7, customer.getBallance());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public Boolean transaction(Customer customer, int locationID, double amount) {
		if (customer.getBallance() < amount) {
			return false;
		}
		customer.setBallance(customer.getBallance() - amount);
		update(customer);
		Customer temp = findByCustomerId(3);
		temp.setBallance(temp.getBallance() + amount);
		update(temp);
		return true;
	}

	public void log(int id, String action) {
		String sql = "INSERT INTO LOG " + "(ID,Time,Log) VALUES (?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setDate(2, date);
			ps.setString(3, action);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public String logging(int id) {

		String sql = "SELECT * FROM LOGGER WHERE ID = ? ORDER BY TIME";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

			}
			rs.close();
			ps.close();
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
