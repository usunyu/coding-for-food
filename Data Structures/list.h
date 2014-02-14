template <typename T>
class list_node
{
public:
	list_node() : next(0) {}
	T data;
	list_node * next;
};

template <typename T>
class list
{
private:
	unsigned int list_length;
	list_node <T> * last_node;
	list_node <T> * head_node;
public:
	list();
	unsigned int length();
	void add(T x);
	void traversal();
	bool is_empty();
	list_node <T> * find (T x);
	void _delete(T x);
	void insert(T x, list_node <T> * p);
	void insert_head(T x);
};

template <typename T>
list <T> :: list()
{
	last_node = 0;
	head_node = 0;
	list_length = 0;
}

template <typename T>
void list <T> :: add(T x)
{
	list_node <T> * node = new list_node <T> ();
	node -> data = x;
	if(last_node == 0)
	{
		last_node = node;
		head_node = node;
	}
	else
	{
		last_node -> next = node;
		last_node = node;
	}
	++list_length;
}

template <typename T>
void list <T> :: traversal()
{
	list_node <T> * node = head_node;
	while(node != 0)
	{
		std::cout << node -> data << " ";
		node = node -> next;
	}
	std::cout << std::endl;
}

template <typename T>
bool list <T> :: is_empty()
{
	return list_length == 0;
}

template <typename T>
list_node <T> * list <T> :: find(T x)
{
	list_node <T> * node = head_node;
	while(node != 0 && node -> data != x)
		node = node -> next;
	return node;
}

template <typename T>
void list <T> :: _delete(T x)
{
	list_node <T> * temp = head_node;
	list_node <T> * pre_temp;
	if(temp == 0)
		return;
	if(temp -> data == x)
	{
		head_node = temp -> next;
		if(temp -> next == 0)
			last_node = 0;
		// delete(temp);
		--list_length;
		return;
	}
	while(temp != 0 && temp -> data != x)
	{
		pre_temp = temp;
		temp = temp -> next;
	}
	if(temp == 0)
		return;
	if(temp == last_node)
	{
		last_node = pre_temp;
		// delete(temp);
		last_node -> next = 0;
		--list_length;
	}
	else
	{
		pre_temp -> next = temp -> next;
		// delete(temp);
		--list_length;
	}
}

template <typename T>
void list <T> :: insert(T x, list_node <T> * p)
{
	if(p == 0)
		return;
	list_node <T> * node = new list_node <T> ();
	node -> data = x;
	node -> next = p -> next;
	p -> next = node;
	if(node -> next == 0)
		last_node = node;
	++list_length;
}

template <typename T>
void list <T> :: insert_head(T x)
{
	list_node <T> * node = new list_node <T> ();
	node -> data = x;
	node -> next = head_node -> next;
	head_node = node;
	if(last_node == 0)
		last_node = node;
	++list_length;
}
