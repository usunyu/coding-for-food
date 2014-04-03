#include <iostream>
#include "stack.h"

using namespace std;

int main()
{
	stack <int> S = stack <int> ();
	cout << "Test push(T x):" << endl;
	for(int i = 0; i < 10; i++)
	{
		S.push(i);
		cout << "Push " << i << endl;
	}
	cout << "Test length():" << endl;
	cout << S.length() << endl;
	cout << "Test top():" << endl;
	cout << S.top() << endl;
	cout << "Test pop():" << endl;
	for(int i = 0; i < 5; i++)
	{
		cout << "Pop " << S.pop() << endl;
	}
	cout << "Length " << S.length() << endl;
	cout << "Test clear():" << endl;
	S.clear();
	cout << "Length " << S.length() << endl;
}