#include <iostream>
#include "list.h"

using namespace std;

int main()
{
	list <int> L = list <int> ();
	cout << "Test add():" << endl;
	for(int i = 0; i < 10; i++)
	{
		L.add(i);
		cout << "Add " << i << endl;
	}
	cout << "Test traversal():" << endl;
	L.traversal();
	cout << "Test is_empty():" << endl;
	cout << L.is_empty() << endl;
	cout << "Test find(T x): find 5" << endl;
	list_node <int> * f = L.find(5);
	if(f != NULL)
		cout << "The node " << f -> data << " has been found" << endl;
	else
		cout << "Cannot find the node";
	cout << "Test find(T x): find 10" << endl;
	f = L.find(10);
	if(f != NULL)
		cout << "The node " << f -> data << " has been found" << endl;
	else
		cout << "Cannot find the node" << endl;
	cout << "Test _delete(T x): delete 9" << endl;
	L._delete(9);
	L.traversal();
	cout << "Test _delete(T x): delete 10" << endl;
	L._delete(10);
	L.traversal();
	cout << "Test _delete(T x): delete 0" << endl;
	L._delete(0);
	L.traversal();
	cout << "Test insert(T x, list_node <T> * p): insert 10 after 5" << endl;
	f = L.find(5);
	L.insert(10, f);
	L.traversal();
	cout << "Test insert_head: insert -1" << endl;
	L.insert_head(-1);
	L.traversal();
	return 0;
}