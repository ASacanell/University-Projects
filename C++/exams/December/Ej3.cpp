#include <iostream>
#include <fstream>
using namespace std;

int main ()
{
int num, numfibs, solucion;
ifstream fibs;
bool encontrado = false;

fibs.open("fibs.txt");
cout << "Dame un numero positivo: ";
cin >> num;
do
{
	fibs >> numfibs;
	solucion = numfibs % num;
	if ((solucion == 0) && (numfibs > 0))
		{encontrado = true;}
}while((!(fibs.eof())) && (!encontrado));
if (encontrado)
	{cout << "Multiplo encontrado: " << numfibs << endl;}
else
	{cout << "Multiplo NO encontrado" << endl;}
fibs.close();
return 0;
}