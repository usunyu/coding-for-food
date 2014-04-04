template <typename T>
class queue_node
{
public:
	queue_node() : next(0) {}
	T data;
	queue_node * next;
};

template <typename T>
class queue
{
private:
	unsigned int queue_length;
	queue_node <T> * rear;
	queue_node <T> * front;
public:
	queue();
	unsigned int length();
	void push(T x);
	bool is_empty();
	T pop();
	T get_front();
	void clear();
};

template <typename T>
queue <T> :: queue()
{
	queue_length = 0;
	rear = 0;
	front = 0;
}

template <typename T>
unsigned int queue <T> :: length()
{
	return queue_length;
}

template <typename T>
void queue <T> :: push(T x)
{
	queue_node <T> * node = new queue_node <T> ();
	node -> data = x;
	if(rear == 0)
	{
		front = node;
		rear = node;
	}
	else
	{
		rear -> next = node;
		rear = node;
	}
	++queue_length;
}

template <typename T>
bool queue <T> :: is_empty()
{
	return queue_length == 0;
}

template <typename T>
T queue <T> :: pop()
{
	if(queue_length == 0)
		return 0;
	queue_node <T> * node = front;
	front = front -> next;
	if(!front)
		rear = 0;
	T value = node -> data;
	delete(node);
	--queue_length;
	return value;
}

template <typename T>
T queue <T> :: get_front()
{
	if(queue_length == 0)
		return 0;
	return front -> data;
}

template <typename T>
void queue <T> :: clear()
{
	queue_node <T> * node;
	while(front != 0)
	{
		node = front;
		front = front -> next;
		delete(node);
		--queue_length;
	}
	rear = 0;
}
