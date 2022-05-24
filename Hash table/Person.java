package il.ac.telhai.ds.misc;

import java.util.Objects;

public class Person {
	String id;
	String firstName;
	String lastName;

	public Person(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public boolean equals(Object p) {
		if (this == p)
			return true;
		if (p == null || getClass() != p.getClass())
			return false;
		Person person = (Person) p;
		return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
	}

	@Override
	public int hashCode(){
		return Integer.parseInt(this.id);
	}

}