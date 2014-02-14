/*
List Flattening
*/
template <typename T>
class node
{
	node * next;
	node * prev;
	node * child;
	T data;
public:
	node(T d) : data(d), next(0), prev(0), child(0) {}
};

template <typename T>
class flat_list
{
	node <T> * head;
	node <T> * tail;
	void append(node <T> *);
	void explore_seperate(node <T> *);
public:
	flat_list() : head(0), tail(0) {}
	void flatten_list();
	void unflaten_list();
};

template <typename T>
void flat_list <T> :: flatten_list()
{
	node <T> * cur_node = head;
	while(cur_node)
	{
		if(cur_node -> child)
			append(cur_node -> child);
		cur_node = cur_node -> next;
	}
}

template <typename T>
void flat_list<T> :: append(node <T> * child)
{
	node <T> * cur_node = child;
	tail -> next = child;
	child -> prev - tail;
	while(cur_node -> next)
	{
		cur_node = cur_node -> next;
	}
	tail = cur_node;
}

typedef struct Node
{
	Node * next;
	Node * prev;
	Node * child;
	int value;
} Node;

/*
List Unflattening
*/
template <typename T>
void flat_list <T> :: unflaten_list()
{
	node <T> * cur_node = head;
	explore_seperate(head);
	while(cur_node -> next)
		cur_node = cur_node -> next;
	tail = cur_node;
}

template <typename T>
void flat_list <T> :: explore_seperate(node <T> * child_start)
{
	node <T> * cur_node = child_start;
	while(cur_node)
	{
		if(cur_node -> child)
		{
			cur_node -> child -> prev -> next = 0;
			cur_node -> child -> prev = 0;
			explore_seperate(cur_node -> child);
		}
		cur_node = cur_node -> next;
	}
}

/*
Single Linked List
*/
// typedef struct int_element
// {
// 	struct int_element * next;
// 	int data;
// } IntElement;

template <typename T>
class list_element
{
	list_element * next;
	T data;
public:
	list_element(T d) : data(d), next(0) {}
};

template <typename T>
class linked_list
{
	list_element <T> * head;
	list_element <T> * tail;
public:
	linked_list();
	bool insert_in_front(list_element <T> * *, T);
	bool insert_in_front(list_element <T> * &, T);
	list_element <T> find(list_element <T> *, T);
	bool delete_element(list_element <T> * &, list_element <T> *);
	bool delete_list(list_element <T> * &);
	bool remove(list_element <T> *);
	bool insert_after(list_element <T> *, T);
	bool remove_head();
	list_element <T> * find_m_to_last(int);
	bool determine_termination();
};

template <typename T>
bool linked_list <T> :: determine_termination()
{
	list_element <T> * fast_element = head;
	list_element <T> * slow_element = head;
	while(true)
	{
		if(fast_element -> next && fast_element -> next -> next)
			fast_element = fast_element -> next -> next;
		else
			return false; // Acycle list
		slow_element = slow_element -> next;
		if(fast_element == slow_element || fast_element -> next == slow_element)
			return true; // Find the cycle
	}
}

template <typename T>
list_element <T> * linked_list <T> :: find_m_to_last(int m)
{
	list_element <T> * element = head;
	while(m)
	{
		// Special case for the list shorter than m
		if(!element)
			return 0;
		element = element -> next;
		m--;
	}
	list_element <T> * target_element = head;
	while(element)
	{
		element = element -> next;
		target_element = target_element -> next;
	}
	return target_element;
}

template <typename T>
bool linked_list <T> :: remove_head()
{
	if(!head)
		return false;
	list_element <T> * element = head;
	head = head -> next;
	if(!head)
		tail = head;
	delete element;
}

template <typename T>
bool linked_list <T> :: insert_after(list_element <T> * element, T data)
{
	list_element <T> * new_element = new list_element <T> ();
	if(!new_element)
		return false;
	new_element -> data = data;
	list_element <T> * cur_pos = head;
	// Insert at the beginning
	if(!element)
	{
		element -> next = head;
		head = element;
		// Special case for empty list
		if(!tail)
			tail = element;
		return true;
	}
	while(cur_pos)
	{
		if(cur_pos == element)
		{
			new_element -> next = cur_pos -> next;
			cur_pos -> next = new_element;
			// Special case for inserting after the last element
			if(!(new_element -> next))
				tail = new_element;
			return true;
		}
		cur_pos = cur_pos -> next;
	}
	delete new_element;
	return false;
}

template <typename T>
bool linked_list <T> :: remove(list_element <T> * element)
{
	if(!element)
		return false;
	list_element <T> * cur_pos = head;
	if(element == head)
	{
		head = head -> next;
		delete element;
		if(!head)
			tail = 0;
		return true;
	}
	while(cur_pos)
	{
		if(cur_pos -> next == element)
		{
			cur_pos -> next = element -> next;
			delete element;
			if(cur_pos -> next == 0)
				tail = cur_pos;
			return true;
		}
		cur_pos = cur_pos -> next;
	}
	return false;
}

template <typename T>
bool linked_list <T> :: delete_list(list_element <T> * & head)
{
	list_element <T> * head_temp = head;
	while(head_temp)
	{
		list_element <T> * next = head_temp -> next;
		delete head_temp;
		head_temp = next;
	}
	head = 0;
}

template <typename T>
bool linked_list <T> :: delete_element(list_element <T> * & head, list_element <T> * delete_element)
{
	list_element <T> * head_temp = head;
	if(delete_element == head)
	{
		head = head -> next;
		delete delete_element;
		return true;
	}
	while(head_temp != 0)
	{
		if(delete_element == head_temp -> next)
		{
			head_temp -> next = delete_element -> next;
			delete delete_element;
			return true;
		}
	}
	return false;
}

template <typename T>
list_element <T> linked_list <T> :: find(list_element <T> * head, T data)
{
	while(head != 0 && head -> data != data)
		head = head -> next;
	return head;
}

// Update by reference
template <typename T>
bool linked_list <T> :: insert_in_front(list_element <T> * & head, T data)
{
	list_element <T> * element = new list_element <T> ();
	if(!element)
		return false;
	element -> next = head;
	head = element;
	return true;
}

// Update by pointer
template <typename T>
bool linked_list <T> :: insert_in_front(list_element <T> * * head, T data)
{
	list_element <T> * element = new list_element <T> ();
	if(!element)
		return false;
	element -> next = * head;
	* head = element;
	return true;
}

template <typename T>
linked_list <T> :: linked_list()
{
	head = 0;
	tail = 0;
}