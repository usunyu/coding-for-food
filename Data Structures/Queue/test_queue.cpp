#include <iostream>
#include "queue.h"

using namespace std;

int main()
{
	queue <int> Q = queue <int> ();
	cout << "Test push(T x):" << endl;
	for(int i = 0; i < 10; i++)
	{
		Q.push(i);
		cout << "Push " << i << endl;
	}
	cout << "Test length():" << endl;
	cout << Q.length() << endl;
	cout << "Test get_front():" << endl;
	cout << Q.get_front() << endl;
	cout << "Test pop():" << endl;
	for(int i = 0; i < 5; i++)
	{
		cout << "Pop " << Q.pop() << endl;
	}
	cout << "Length " << Q.length() << endl;
	cout << "Test clear():" << endl;
	Q.clear();
	cout << "Length " << Q.length() << endl;
}