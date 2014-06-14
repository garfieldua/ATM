package com.vabank.admin;

public class Employee implements Comparable<Employee> {
	public int salary;
	public String cardNumber;

	public Employee(int s, String c) {
		salary = s;
		cardNumber = c;
	}

	@Override
	public String toString() {
		return "Employee {" + "salary =" + salary + ", card_number ="
				+ cardNumber + '}';
	}

	@Override
	public int compareTo(Employee arg0) {
		return this.cardNumber.compareTo(arg0.cardNumber);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Employee && (this.compareTo((Employee) obj) == 0);
	}
}
