package com.mkyong.customer.model;

public class Customer {
	String firstName, lastName;
	String address;
	String dateOfBirth;
	String password;
	double ballance;
	int custId;

	public Customer(int custId, String firstName, String lastName, String address, String dateOfBirth, String password,
			double ballance) {
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.ballance = ballance;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBallance(double ballance) {
		this.ballance = ballance;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public double getBallance() {
		return ballance;
	}

	public void setAge(double ballance) {
		this.ballance = ballance;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + firstName + " " + lastName + ", ballance=" + ballance + "]";
	}

}
