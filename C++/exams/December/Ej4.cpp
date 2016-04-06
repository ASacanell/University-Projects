#include <iostream>
using namespace std;

int main ()
{
bool creciente = true, encontrado = false;
int num1, num2;
cout << "Introduce una secuencia de numeros terminada en 0: " << endl;
cin >> num1;
do 
{	
	cin >> num2;
	if (num2 == 0)
		{encontrado = true;}
	else
		{
		if (num1 > num2)
			{
			creciente = false;
			cout << "La secuencia NO es creciente";
			}
		num1 = num2;
		}
}while(((!encontrado) && (creciente)));
if (creciente)
	{cout << "La secuencia es creciente";}
cout << endl;
return 0;
}