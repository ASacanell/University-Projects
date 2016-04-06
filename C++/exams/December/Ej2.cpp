#include <iostream>
#include <fstream>
using namespace std;

int main()
{
ifstream fibs;
int num, cont = 0, modulo;

fibs.open("fibs.txt");
do
{
	fibs >> num;
	modulo = num % 2;
	if (modulo == 0)
		cont++;
}while (num != -1);
cout << "Habia " << cont << " numero/s par/es" << endl;
fibs.close();
return 0;
}