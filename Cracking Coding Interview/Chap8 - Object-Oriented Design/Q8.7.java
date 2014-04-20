/*
Explain how you would design a chat server. In particular, provide details about the various backend components, 
classes, and methods. What would be the hardest problems to solve?
*/

import java.util.*;

enum Status {
	Offline,
	Online
}

class User {
	int id;
	String name;
	Status status;
	String ip;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		status = Status.Offline;
	}

	public void login() {
		status = Status.Online;
	}

	public void logout() {
		status = Status.Offline;
	}

	public void chat(User friend) {

	}
}

class ChatServer {
	int userCount;
	LinkedList<User> onlineUserList;
	LinkedList<User> offlineUserList;

	public ChatServer() {
		onlineUserList = new LinkedList<User>();
		offlineUserList = new LinkedList<User>();
	}

	public User register(String name) {
		User user = new User(userCount++, name);
		onlineUserList.add(user);
		return user;
	}

	public User login(int id) {
		// find in the offline user
		ListIterator<User> it = offlineUserList.listIterator();
		while(it.hasNext()) {
			User temp = it.next();
			if(temp.id == id) {
				temp.login();
				it.remove();
				onlineUserList.add(temp);

				// give user's contact list, ip address


				return temp;
			}
		}
		return null;
	}

	public User logout(int id) {
		//...
		return null;
	}

	public User find(int id) {
		//...
		return null;
	}
}

class Solution {
	public static void main(String[] args) {

	}
}