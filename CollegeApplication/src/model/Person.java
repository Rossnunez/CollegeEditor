package model;

import java.io.Serializable;

public abstract class Person implements Serializable{
	private Name name;
	private String id = "";

	public static int idCounter = 0;

	public Person(Name name) {
		this.name = name;
		
		int lenS = String.valueOf(idCounter).length();
		int len = 8 - lenS;
		for (int i = 0; i < len; i++) {
			id += "0";
		}

		id += String.valueOf(idCounter++); 
	}

	public Name getName() {
		return name;
	}


	public String getId() {
		return id;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}

}
