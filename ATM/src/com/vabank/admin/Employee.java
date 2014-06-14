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
		String comparator = arg0.cardNumber;
		return cardNumber.compareTo(comparator);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Employee))
			return false;
		if (obj == this)
			return true;

		Employee rhs = (Employee) obj;
		return cardNumber.equals(rhs.cardNumber);
	}
}
