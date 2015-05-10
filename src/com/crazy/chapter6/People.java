package com.crazy.chapter6;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.ResponseWrapper;

class Name{
	private String firstName;
	private String lastName;
	
	public Name(){}
	
	public Name(String firstName,String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
public class People {

	private final Name name;
	public People(Name name){
		this.name = name;
	}
	
	public Name getName(){
		return name;
	}

	public static void main(String[] args){
		Name name = new Name("yong","li");
		People people = new People(name);
		System.out.println(people.getName().getFirstName());
		name.setFirstName("mingjue");
		System.out.println(people.getName().getFirstName());
		
		List<Name> nameList = new ArrayList<>();
		for(int i = 0; i < 10; i ++){
			name = new Name("yong",String.valueOf(i));
			nameList.add(name);
		}
		for(Name names : nameList){
			System.out.println(names.getLastName());
		}
	}
}
