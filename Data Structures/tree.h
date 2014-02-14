// Children parent represent
template <typename T>
class tree_node
{
public:
	T data;
	int parent_index;
	class child_node * first_child;
	tree_node() : data(0), parent_index(-1), first_child(0) {}
	~tree_node() {delete first_child;}
};

class child_node
{
public:
	int node_index;
	class child_node * next;
	child_node() : node_index(-1), next(0) {}
	~child_node() {delete next;}
};

template <typename T>
class tree
{
private:
	tree_node <T> * _tree;
	unsigned int node_number;
	unsigned int max_number;
public:
	tree(int n);
	void create();
	bool is_empty();
	class tree_node <T> get_root();
	int depth(class tree_node <T> t);
	T value(int i);
	// void assign(T old, T _new);
	int degree(int i);
	// void insert(int i, child_node & node);
	// void _delete(int i);
	void print();
	void traverse();
};

template <typename T>
tree <T> :: tree(int n)
{
	node_number = n;
	max_number = 2 * node_number;
	// Allocate more space for expanding
	_tree = new tree_node <T> [max_number];
}

template <typename T>
void tree <T> :: create()
{
	std::cout << "Please input the root element: " << std::flush;
	T temp_value;
	std::cin >> temp_value;
	_tree[0].data = temp_value;
	std::cout << "Add root whith value " << _tree[0].data << std::endl;
	for(int i = 1; i < node_number; i++)
	{
		std::cout << "Please input the node " << i << " element: " << std::flush;
		std::cin >> temp_value;
		_tree[i].data = temp_value;
		std::cout << "Add node " << i << " whith value " << _tree[i].data << std::endl;
	}
	std::cout << "Nodes:" << std::endl;
	print();
	std::cout << "(0 indicate the end of input of childs)" << std::endl;
	int child_index;
	for(int i = 0; i < node_number; i++)
	{
		class child_node * prev_child = 0;
		if(i == 0)
			std::cout << "Please input the root element's childs: " << std::flush;
		else
			std::cout << "Please input the node " << i << " element's childs: " << std::flush;
		while(std::cin >> child_index)
		{
			if(child_index == 0)
				break;
			// Add child node
			class child_node * child = new child_node();
			child -> node_index = child_index;
			_tree[i].first_child = child;
			child -> next = prev_child;
			prev_child = child;
			// Set parent index
			_tree[child_index].parent_index = i;
		}
	}
}

template <typename T>
bool tree <T> :: is_empty()
{
	return node_number == 0;
}

template <typename T>
class tree_node <T> tree <T> :: get_root()
{
	return _tree[0];
}

template <typename T>
int tree <T> :: depth(class tree_node <T> t)
{
	int depth = 0;
	class child_node * child = t.first_child;
	if(child == 0)
		return 1;
	while(child != 0)
	{
		int temp_depth = tree <T> :: depth(_tree[child -> node_index]);
		if(temp_depth > depth)
			depth = temp_depth;
		child = child -> next;
	}
	return depth + 1;
}

template <typename T>
void tree <T> :: print()
{
	std::cout << "Node:\t" << "Value:\t" << "Parent:\t" << "Childs:\t" << std::endl;
	for(int i = 0; i < node_number; i++)
	{
		std::cout << i << "\t" << _tree[i].data << "\t" << _tree[i].parent_index << "\t" << std::flush;
		class child_node * child = _tree[i].first_child;
		if(child != 0)
		{
			while(child != 0)
			{
				std::cout << "--> " << child -> node_index << " " << std::flush;
				child = child -> next;
			}
			std::cout << std::endl;
		}
		else
			std::cout << "NULL" << std::endl;
	}
}