#include <iostream>
#include "tree.h"

using namespace std;

int main()
{
	int n;
	cout << "Please input the number of nodes: " << flush;
	cin >> n;
	tree <int> T = tree <int> (n);
	cout << "Test create():" << endl;
	T.create();
	cout << "Test print():" << endl;
	T.print();
	cout << "Test depth(class tree_node <T> * t):" << endl;
	cout << T.depth(T.get_root()) << endl;
}