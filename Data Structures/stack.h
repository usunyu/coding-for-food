template <typename T>
class stack_node
{
public:
	stack_node() : next(0) {}
	T data;
	stack_node * next;
};

template <typename T>
class stack
{
private:
	unsigned int stack_length;
	stack_node <T> * top_node;
public:
	stack();
	unsigned int length();
	void push(T x);
	bool is_empty();
	T pop();
	T top();
	void clear();
};

template <typename T>
stack <T> :: stack()
{
	top_node = 0;
	stack_length = 0;
}

template <typename T>
unsigned int stack <T> :: length()
{
	return stack_length;
}

template <typename T>
void stack <T> :: push(T x)
{
	stack_node <T> * node = new stack_node <T> ();
	node -> data = x;
	node -> next = top_node;
	top_node = node;
	++stack_length;
}

template <typename T>
bool stack <T> :: is_empty()
{
	return stack_length == 0;
}

template <typename T>
T stack <T> :: pop()
{
	if(is_empty())
		return 0;
	stack_node <T> * node = top_node;
	top_node = top_node -> next;
	T value = node -> data;
	delete(node);
	--stack_length;
	return value;
}

template <typename T>
T stack <T> :: top()
{
	if(is_empty())
		return 0;
	return top_node -> data;
}

template <typename T>
void stack <T> :: clear()
{
	stack_node <T> * node;
	while(top_node != 0)
	{
		node = top_node;
		top_node = top_node -> next;
		delete(top_node);
		--stack_length;
	}
}