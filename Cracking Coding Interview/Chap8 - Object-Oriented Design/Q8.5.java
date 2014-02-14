import java.util.*;

enum Category {
	Art,
	Math,
	Novel,
	Computer
}

class Book {
	Category category;
	String name;
	String author;
	int number;
	int price;

	public boolean equals(Book book) {
		if(category == book.category && name.equals(book.name) && author.equals(book.author))
			return true;
		else
			return false;
	}
}

class OnlineBookSystem {
	int funds;
	LinkedList<LinkedList<Book>> storeBook;

	public OnlineBookSystem() {
		funds = 1000;
		storeBook = new LinkedList<LinkedList<Book>>();
		for(Category c : Category.values()) {
			LinkedList<Book> bookList = new LinkedList<Book>();
			storeBook.add(bookList);
		}
	}

	public Book checkExist(Book book, LinkedList<Book> bookList) {
		ListIterator<Book> it = bookList.listIterator();

		while(it.hasNext()) {
			Book eBook = it.next();
			if(eBook.equals(book))
				return eBook;
		}
		return null;
	}

	public void buy(Book book) {
		int totalPrice = book.number * book.price;
		if(totalPrice > funds) {
			System.out.println("Sorry, we cannot afford");
			return;
		}
		else
			funds -= totalPrice;

		Category category = book.category;
		// get corresponding book list
		LinkedList<Book> bookList = storeBook.get(category.ordinal());
		Book eBook = checkExist(book, bookList);
		if(eBook != null) {
			eBook.number += book.number;
		}
		else {
			bookList.add(book);
		}
	}

	public void sell(Book book) {
		Category category = book.category;
		LinkedList<Book> bookList = storeBook.get(category.ordinal());
		Book eBook = checkExist(book, bookList);
		if(eBook != null) {
			if(eBook.number >= book.number) {
				eBook.number -= book.number;
				// TODO
				id(eBook.number == 0) {
					
				}
				funds += book.price * book.number;
			}
			else
				System.out.println("Sorry, we don't have enough this kind of book");
		}
		else {
			System.out.println("Sorry, we don't have this kind of book");
		}
	}
}

class Q8_5App {
	public static void main(String[] args) {

	}
}