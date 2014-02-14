#include <iostream>
#include <string>
#include "hash_table.h"

using namespace std;

int main()
{
	hash_table <int, string> H = hash_table <int, string> ();
	cout << "Test initial_hash():" << endl;
	H.initial_hash();
	cout << "Test insert_hash():" << endl;
	H.insert_hash(10, "Hello");
	H.insert_hash(10, "Hello");
	for(int i = 0; i < 11; i++)
	{
		H.insert_hash(i * 10 + 1, "Test");
	}
	H.insert_hash(2, "World");
	H.print();
	H.insert_hash(111, "Test");
	H.print();
}