#include <iostream>
#include "binary_search_tree.h"

using namespace std;

int main()
{
	bst <int> bin_tree = bst <int> ();
	cout << "Test create_bst():" << endl;
	bin_tree.create_bst();
	cout << "Test print_bst():" << endl;
	bin_tree.print_bst();
	cout << "Test delete_bst():" << endl << "please input the value of node to be deleted:" << endl;
	int data = 0;
	cin >> data;
	bin_tree.delete_bst(data);
	bin_tree.print_bst();
	cout << "Test pre_order_traverse():" << endl;
	bin_tree.pre_order_traverse();
	cout << "Test in_order_traverse():" << endl;
	bin_tree.in_order_traverse();
	cout << "Test post_order_traverse():" << endl;
	bin_tree.post_order_traverse();
	cout << "Test level_order_traverse():" << endl;
	bin_tree.level_order_traverse();
	return 0;
}