template <typename T>
class stack_element
{
	stack_element * next;
	T data;
public:
	stack_element() : next(0) {}
};

template <typename T>
class stack
{
	stack_element <T> * top;
public:
	bool create_stack();
	bool delete_stack();
	bool push(T data);
	bool pop(T & data);
};

template <typename T>
bool stack <T> :: delete_stack()
{
	stack_element <T> * element = top;
	while(top != 0)
	{
		top = top -> next;
		delete element;
		element = top;
	}
	return true;
}

template <typename T>
bool stack <T> :: pop(T & data)
{
	stack_element <T> * element = top;
	if(!element)
		return false;
	top = top -> next;
	data = element -> data;
	delete element;
	return true;
}

template <typename T>
bool stack <T> :: push(T data)
{
	stack_element <T> element = new stack_element <T> ();
	if(!element)
		return false;
	element -> data = data;
	element -> next = top;
	top = element;
	return true;
}

template <typename T>
bool stack <T> :: create_stack()
{
	top = 0;
	return true;
}

template <typename T>
class Stack
{
public:
	Stack();
	~Stack();
	bool Push(T data);
	bool Pop(T & data);
protected:
	struct Element
	{
		struct Element * next;
		T data;
	};
	Element * Top;
};

template <typename T>
bool Stack <T> :: Pop(T & data)
{
	if(!Top)
		return false;
	struct Element * element = Top;
	Top = Top -> next;
	data = element -> data;
	delete element;
	return true;
}

template <typename T>
bool Stack <T> :: Push(T data)
{
	struct Element * element = new struct Element;
	if(!element)
		return false;
	element -> data = data;
	element -> next = Top;
	Top = element;
	return true;
}

template <typename T>
Stack <T> :: ~Stack()
{
	Element * element;
	while(Top != 0)
	{
		element = Top;
		Top = Top -> next;
		delete element;
	}
}

template <typename T>
Stack <T> :: Stack()
{
	Top = 0;
}