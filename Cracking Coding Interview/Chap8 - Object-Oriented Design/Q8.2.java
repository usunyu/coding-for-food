/*
Imagine you have a call center with three levels of employees: respondent, manager, and director. 
An incoming telephone call must be first allocated to a respondent who is free. If the respondent 
can't handle the call, he or she must escalate the call to a manager. If the manager is not free or 
notable to handle it, then the call should be escalated to a director. Design the classes and data 
structures for this problem. Implement a method dispatchCaL L() which assigns a call to the first 
available employee.
*/

import java.util.*;

abstract class Employee {
	int id;
	String name;
	int age;
	boolean available;

	public Employee(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		available = true;
	}

	public abstract void getCall();

	public boolean isAvailable() {
		return available;
	}
}

class Respondent extends Employee {
	public Respondent(int id, String name, int age) {
		super(id, name, age);
	}

	public void getCall() {
		available = false;
		System.out.println("Respondent No." + id + " " + name + " is answering the phone");
	}
}

class Manager extends Employee {
	public Manager(int id, String name, int age) {
		super(id, name, age);
	}

	public void getCall() {
		available = false;
		System.out.println("Manager No." + id + " " + name + " is answering the phone");
	}
}

class Director extends Employee {
	public Director(int id, String name, int age) {
		super(id, name, age);
	}

	public void getCall() {
		available = false;
		System.out.println("Director No." + id + " " + name + " is answering the phone");
	}
}

class CallCenter {
	LinkedList<Respondent> respondentList;
	LinkedList<Manager> managerList;
	LinkedList<Director> directorList;

	public CallCenter() {
		respondentList = new LinkedList<Respondent>();
		managerList = new LinkedList<Manager>();
		directorList = new LinkedList<Director>();
	}

	public void recruit() {
		for(int i = 0; i < 10; i++) {
			Respondent respondent = new Respondent(i + 1, "respondent", 20);
			respondentList.add(respondent);
		}
		for(int i = 0; i < 3; i++) {
			Manager manager = new Manager(i + 1, "manager", 20);
			managerList.add(manager);
		}
		for(int i = 0; i < 1; i++) {
			Director director = new Director(i + 1, "director", 20);
			directorList.add(director);
		}
	}

	public void dispatchCall() {
		// looking for respondentList
		ListIterator<Respondent> rIt = respondentList.listIterator();
		while(rIt.hasNext()) {
			Respondent respondent = rIt.next();
			if(respondent.isAvailable()) {
				respondent.getCall();
				return;
			}
		}
		ListIterator<Manager> mIt = managerList.listIterator();
		while(mIt.hasNext()) {
			Manager manager = mIt.next();
			if(manager.isAvailable()) {
				manager.getCall();
				return;
			}
		}
		ListIterator<Director> dIt = directorList.listIterator();
		while(dIt.hasNext()) {
			Director director = dIt.next();
			if(director.isAvailable()) {
				director.getCall();
				return;
			}
		}
		System.out.println("No one can answer the phone");
	}
}

class Solution {
	public static void main(String[] args) {
		CallCenter callCenter = new CallCenter();
		callCenter.recruit();
		for(int i = 0; i < 20; i++) {
			callCenter.dispatchCall();
		}
	}
}