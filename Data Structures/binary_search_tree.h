#include "stack.h"
#include "queue.h"

template <typename T>
// Tree node
class bst_node
{
public:
	int tag; // Postorder traversal acess tag
	T data;
	bst_node * left_child;
	bst_node * right_child;

	bst_node(T dt) : tag(0), data(dt), left_child(0), right_child(0) {}
};

template <typename T>
class bst
{
public:
	bst();
	/*
	Find the node with value of data in the BST, if it is found,
	store the address of node in p and its parent in f.
	*/
	bool search_bst(T data, bst_node <T> * & p, bst_node <T> * & f);
	/*
	Insert a node with value of data into BST, if it is already existed,
	do not insert.
	*/
	bool insert_bst(T data);
	/*
	Insert the data with the way of recursion.
	*/
	bool insert_bst_rec(T data);
	/*
	Create Binary Sort Tree, store the root of BST in the root pointer.
	*/
	bool create_bst();
	/*
	Delete the node with value of data in the BST.
	Four situations:
	1. the node to be deleted is leaf;
	2. the node to be deleted is root;
	3. the node to be deleted has only left child or right child;
	4. the node to be deleted has left child and right child.
	*/
	bool delete_bst(T data);
	/*
	Print the BST.
	*/
	void print_bst();
	/*
	Four solutions of traversing BST(Iteration, not recursion).
	*/
	void pre_order_traverse();
	void in_order_traverse();
	void post_order_traverse();
	void level_order_traverse();
private:
	/*
	Root node of BST.
	*/
	bst_node <T> * root;

	bool insert_rec(T data, bst_node <T> * & element);

	void print_rec(bst_node <T> * & element);
};

template <typename T>
void bst <T> :: level_order_traverse()
{
	queue <bst_node <T> *> Q = queue <bst_node <T> *> ();
	bst_node <T> * node = root;
	if(node)
		Q.push(node);
	while(!Q.is_empty())
	{
		node = Q.pop();
		std::cout << node -> data << " " << std::flush;
		if(node -> left_child)
			Q.push(node -> left_child);
		if(node -> right_child)
			Q.push(node -> right_child);
	}
	std::cout << std::endl;
}

template <typename T>
void bst <T> :: post_order_traverse()
{
	stack <bst_node <T> *> S = stack <bst_node <T> *> ();
	bst_node <T> * node = root;
	while(node || !S.is_empty())
	{
		if(node)
		{
			node -> tag = 0;
			S.push(node);
			node = node -> left_child;
		}
		else
		{
			node = S.top();
			if(node -> tag)
			{
				std::cout << node -> data << " " << std::flush;
				S.pop();
				node = 0;
			}
			else
			{
				node -> tag = 1;
				node = node -> right_child;
			}
		}
	}
	std::cout << std::endl;
}

template <typename T>
void bst <T> :: in_order_traverse()
{
	stack <bst_node <T> *> S = stack <bst_node <T> *> ();
	bst_node <T> * node = root;
	while(node || !S.is_empty())
	{
		if(node)
		{
			S.push(node);
			node = node -> left_child;
		}
		else
		{
			node = S.pop();
			std::cout << node -> data << " " << std::flush;
			node = node -> right_child;
		}
	}
	std::cout << std::endl;
}

template <typename T>
void bst <T> :: pre_order_traverse()
{
	stack <bst_node <T> *> S = stack <bst_node <T> *> ();
	bst_node <T> * node = root;
	while(node || !S.is_empty())
	{
		if(node)
		{
			std::cout << node -> data << " " << std::flush;
			S.push(node);
			node = node -> left_child;
		}
		else
		{
			node = S.pop();
			node = node -> right_child;
		}
	}
	std::cout << std::endl;
}

template <typename T>
bool bst <T> :: delete_bst(T data)
{
	bst_node <T> * f = 0;
	bst_node <T> * p = 0;
	if(!search_bst(data, p, f))
		return false;
	// temporary root
	bst_node <T> * r = 0;
	if(p -> left_child && p -> right_child)
	{
		bst_node <T> * t = p -> left_child;
		r = t;
		while(t -> right_child)
			t = t -> right_child;
		// create temporary tree
		t -> right_child = p -> right_child;
	}
	else if(p -> left_child)
		r = p -> left_child;
	else if(p -> right_child)
		r = p -> right_child;
	// the node to be deleted is a leaf;
	else
		r = 0;
	if(f)
	{
		// p is left child of its parent
		if(f -> left_child == p)
			f -> left_child = r;
		else
		// p is right child of its parent
			f -> right_child = r;
	}
	else
	// p is root
		root = r;
	delete p;
	return true;
}

template <typename T>
void bst <T> :: print_rec(bst_node <T> * & element)
{
	std::cout << "Node: " << element -> data << "\t";
	if(element -> left_child)
		std::cout << "Left Child: " << element -> left_child -> data << "\t";
	if(element -> right_child)
		std::cout << "Right Child: " << element -> right_child -> data << "\t";
	std::cout << std::endl;
	if(element -> left_child)
		print_rec(element -> left_child);
	if(element -> right_child)
		print_rec(element -> right_child);
}

template <typename T>
void bst <T> :: print_bst()
{
	if(root)
		print_rec(root);
}

template <typename T>
bool bst <T> :: create_bst()
{
	std::cout << "Please input the value of BST, input -1 indicate the end." << std::endl;
	T data = 0;
	while(std::cin >> data && data != -1)
	{
		insert_bst(data);
	}
	return true;
}

template <typename T>
bool bst <T> :: insert_rec(T data, bst_node <T> * & element)
{
	if(data == element -> data)
		return false;
	if(data > element -> data)
	{
		if(!(element -> right_child))
		{
			bst_node <T> * node = new bst_node <T> (data);
			if(!node)
				return false;
			element -> right_child = node;
			return true;
		}
		else
			return insert_rec(data, element -> right_child);
	}
	if(data < element -> data)
	{
		if(!(element -> left_child))
		{
			bst_node <T> * node = new bst_node <T> (data);
			if(!node)
				return false;
			element -> left_child = node;
			return true;
		}
		else
			return insert_rec(data, element -> left_child);
	}
}

template <typename T>
bool bst <T> :: insert_bst_rec(T data)
{
	if(!root)
	{
		bst_node <T> * node = new bst_node <T> (data);
		if(!node)
			return false;
		root = node;
		return true;
	}
	else
		return insert_rec(data, root);
}

template <typename T>
bool bst <T> :: insert_bst(T data)
{
	bst_node <T> * f = 0;
	bst_node <T> * p = 0;
	if(search_bst(data, p, f))
		return false;
	else
	{
		bst_node <T> * node = new bst_node <T> (data);
		if(!node)
			return false;
		if(!f)
			root = node;
		else
		{
			if (data > f -> data)
				f -> right_child = node;
			else
				f -> left_child = node;
		}
		return true;
	}
}

template <typename T>
bool bst <T> :: search_bst(T data, bst_node <T> * & p, bst_node <T> * & f)
{
	p = root;
	f = 0;
	while(p)
	{
		if(p -> data == data)
			return true;
		else if(p -> data > data)
		{
			f = p;
			p = p -> left_child;
		}
		else
		{
			f = p;
			p = p -> right_child;
		}
	}
	return false;
}

template <typename T>
bst <T> :: bst()
{
	root = 0;
}