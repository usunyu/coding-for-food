static int HASH_LENGTH = 10;
const int COLLISION_LENGTH = 10;

template <typename KeyType, typename ValueType>
class hash_node
{
public:
	KeyType key;
	ValueType value;
	hash_node() : key(0) {}
	hash_node(KeyType k, ValueType v) : key(k), value(v) {}
};

template <typename KeyType, typename ValueType>
class hash_table
{
private:
	hash_node <KeyType, ValueType> * table;
	hash_node <KeyType, ValueType> * collision;
	// the number of nodes
	// int count;
public:
	hash_table();
	~hash_table();
	bool initial_hash();
	unsigned hash(KeyType k);
	/*
	search the hash table to find whether exist the node with key of k,
	return true if it is found and store the address in p, in_collision
	determine whether it is in collision; if it is not found, p indicate
	the address where the node can be inserted.
	*/
	bool search_hash(KeyType k, unsigned & p, bool & in_collision);
	/*
	search the hash table t find whether there exist the node with key of
	k, if it is existed, return true and v store the value.
	*/
	bool search_hash(KeyType k, ValueType & v);
	bool insert_hash(KeyType k, ValueType v);
	void expand_hash();
	/*
	print the whole hash table and collision table.
	*/
	void print();
};

template <typename KeyType, typename ValueType>
void hash_table <KeyType, ValueType> :: print()
{
	std::cout << "Hash Table:" << std::endl << "Key:\tValue:" << std::endl;
	for(int i = 0; i < HASH_LENGTH; i++)
	{
		if(table[i].key)
			std::cout << table[i].key << "\t" << table[i].value << std::endl;
		else
			std::cout << "NULL\tNULL" << std::endl;
	}
	std::cout << "Collision Table:" << std::endl << "Key:\tValue:" << std::endl;
	for(int i = 0; i < COLLISION_LENGTH; i++)
	{
		if(collision[i].key)
			std::cout << collision[i].key << "\t" << collision[i].value << std::endl;
		else
			std::cout << "NULL\tNULL" << std::endl;
	}
}

template <typename KeyType, typename ValueType>
void hash_table <KeyType, ValueType> :: expand_hash()
{
	HASH_LENGTH = HASH_LENGTH * 2;
	hash_node <KeyType, ValueType> * table_temp = new hash_node <KeyType, ValueType> [HASH_LENGTH];
	hash_node <KeyType, ValueType> * collision_temp = collision;
	collision = new hash_node <KeyType, ValueType> [COLLISION_LENGTH];
	if(!table_temp || !collision_temp)
	{
		std::cout << "Memory allocation failed!" << std::endl;
		exit(0);
	}
	// copy from original hash table
	for(int i = 0; i < HASH_LENGTH / 2; i++)
	{
		if(table[i].key)
		{
			unsigned p = hash(table[i].key);
			table_temp[p] = table[i];
		}
	}
	delete [] table;
	table = table_temp;
	// copy from original collision table
	for(int i = 0; i < COLLISION_LENGTH; i++)
	{
		if(collision_temp[i].key)
		{
			insert_hash(collision_temp[i].key, collision_temp[i].value);
		}
	}
	delete [] collision_temp;
}

template <typename KeyType, typename ValueType>
bool hash_table <KeyType, ValueType> :: insert_hash(KeyType k, ValueType v)
{
	unsigned p;
	bool in_collision;
	if(search_hash(k, p, in_collision))
	{
		std::cout << "(" << k << "," << v << ") " << "cannot insert, the key already exists." << std::endl;
		return false;
	}
	else
	{
		hash_node <KeyType, ValueType> * node = new hash_node <KeyType, ValueType> (k, v);
		if(!in_collision)
		{
			table[p] = (*node);
			std::cout << "(" << k << "," << v << ") " << "insert into hash table successed." << std::endl;
			return true;
		}
		else
		{
			if(p < COLLISION_LENGTH)
			{
				collision[p] = (*node);
				std::cout << "(" << k << "," << v << ") " << "insert into collision table successed." << std::endl;
			}
			else
			{
				std::cout << "Inserting (" << k << "," << v << ") " << "the collision table is full, expanding hash table." << std::endl;
				expand_hash();
				std::cout << "Expand hash table successed." << std::endl;
				insert_hash(k, v);
			}
		}
	}
}

template <typename KeyType, typename ValueType>
bool hash_table <KeyType, ValueType> :: search_hash(KeyType k, unsigned & p, bool & in_collision)
{
	in_collision = false;
	p = hash(k);
	if(table[p].key)
	{
		if(k == table[p].key)
			return true;
		else
		{
			in_collision = true;
			for(p = 0; p < COLLISION_LENGTH && collision[p].key; p++)
				if(k == collision[p].key)
					break;
			if(p == COLLISION_LENGTH || !collision[p].key)
				return false;
			return true;
		}
	}
	else
		return false;
}

template <typename KeyType, typename ValueType>
bool hash_table <KeyType, ValueType> :: search_hash(KeyType k, ValueType & v)
{
	unsigned p = hash(k);
	if(table[p].key)
	{
		if(k == table[p].key)
		{
			v = table[p].value;
			std::cout << "Find it in the hash table, key: " << k << " value: " << v << std::endl;
			return true;
		}
		else
		{
			int i;
			for(i = 0; i < COLLISION_LENGTH && collision[i].key; i++)
			{
				if(k == collision[i].key)
				{
					v = collision[i].value;
					std::cout << "Find it in the collision table, key: " << k << " value: " << v << std::endl;
					break;
				}
			}
			if(i == COLLISION_LENGTH)
			{
				std::cout << "Cannot find!" << std::endl;
				return false;
			}
			return true;
		}
	}
	else
	{
		std::cout << "Cannot find!" << std::endl;
		return false;
	}
}

template <typename KeyType, typename ValueType>
unsigned hash_table <KeyType, ValueType> :: hash(KeyType k)
{
	return k % HASH_LENGTH;
}

template <typename KeyType, typename ValueType>
bool hash_table <KeyType, ValueType> :: initial_hash()
{
	table = new hash_node <KeyType, ValueType> [HASH_LENGTH];
	collision = new hash_node <KeyType, ValueType> [COLLISION_LENGTH];
	if(!table || !collision)
	{
		std::cout << "Memory allocation failed!" << std::endl;
		exit(0);
	}
	return true;
}

template <typename KeyType, typename ValueType>
hash_table <KeyType, ValueType> :: ~hash_table()
{
	delete [] table;
	delete [] collision;
	table = 0;
	collision = 0;
	HASH_LENGTH = 10;
}

template <typename KeyType, typename ValueType>
hash_table <KeyType, ValueType> :: hash_table()
{
	table = 0;
	collision = 0;
}