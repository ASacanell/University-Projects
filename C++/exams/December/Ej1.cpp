#include <iostream>
#include <fstream>
using namespace std;

int main()
{
	int num, cont;
	int fib, fibmenos2 = 0, fibmenos1 = 1;
	bool terminar = false;
	ofstream fibs;

	fibs.open("fibs.txt");
	
	cout << "Introduce un numero mayor que 2: ";
	cin >> num;
	cout << endl;
	cont = num - 3;
	fib = fibmenos2 + fibmenos1;
	cout << "0, 1";
	fibs << "0" << endl << "1" << endl;
	do
	{
		fibmenos2 = fibmenos1;
		fibmenos1 = fib;
		fib = fibmenos2 + fibmenos1;
		cont--;
		fibs << fib << endl;
		cout << ", " << fib;
	}while(cont > 0);
	cout << endl;
	fibs << "-1";
	fibs.close();
	return 0;
	}