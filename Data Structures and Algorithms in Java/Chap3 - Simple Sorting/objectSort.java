import java.io.*;

class Person {
	private String lastName;
	private String firstName;
	private int age;

	public Person(String last, String first, int a)
	{                               // constructor
		lastName = last;
		firstName = first;
		age = a;
	}

	public void displayPerson()
	{
		System.out.print("   Last name: " + lastName);
		System.out.print(", First name: " + firstName);
		System.out.println(", Age: " + age);
	}
	public String getLast() { return lastName; }
}

class ArrayInOb {
	private Person[] a;               // ref to array a
	private int nElems;               // number of data items

	public ArrayInOb(int max) {
		a = new Person[max];
		nElems = 0;
	}

	public void insert(String last, String first, int age) {
		a[nElems] = new Person(last, first, age);
		nElems++;                          // increment size
	}

	public void display() {
		for(int j=0; j<nElems; j++)
			a[j].displayPerson();
		System.out.println("");
	}

	public void insertionSort() {
		for(int i = 1; i < nElems; i++) {
			Person temp = a[i];
			int j;
			for(j = i; j > 0; j--) {
				if(a[j - 1].getLast().compareTo(temp.getLast()) > 0)
					a[j] = a[j - 1];
				else
					break;
			}
			a[j] = temp;
		}
	}
}

class ObjectSortApp
{
	public static void main(String[] args) {
		int maxSize = 100;             // array size
		ArrayInOb arr;                 // reference to array
		arr = new ArrayInOb(maxSize);  // create the array
		arr.insert("Evans", "Patty", 24);
		arr.insert("Smith", "Doc", 59);
		arr.insert("Smith", "Lorraine", 37);
		arr.insert("Smith", "Paul", 37);
		arr.insert("Yee", "Tom", 43);
		arr.insert("Hashimoto", "Sato", 21);
		arr.insert("Stimson", "Henry", 29);
		arr.insert("Velasquez", "Jose", 72);
		arr.insert("Vang", "Minh", 22);
		arr.insert("Creswell", "Lucinda", 18);
		System.out.println("Before sorting:");
		arr.display();                 // display items
		arr.insertionSort();           // insertion-sort them
		System.out.println("After sorting:");
		arr.display();                 // display them again
	}  // end main()
}  // end class ObjectSortApp


