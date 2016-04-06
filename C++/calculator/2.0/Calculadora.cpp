//Apellidos: Sacanell Nogales	//Nombre: Alejandro	//DNI: 05435094 X
//Apellidos: Peinado Delgado	//Nombre: Guillermo	//DNI: 76088775 Z
//Grupo: 28		//Laboratorio: 2

#include <iostream>
#include <cmath>
#include <fstream>
#include <cstdlib>

using namespace std;


//Prototipos
void cargarConfiguracion(char &borrar, char &finalizar, char &sumar, char &restar, char &multiplicar, char &dividir, char &fact, char &expo, char &raiz, char &simplificar);
char mostrarMenu(char &opcion);
void mostrarOperandos(char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char borrar, char simplificar, char opcion);
void guardarConfiguracion(char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar);
void cambiarConfiguracion(char &borrar, char &finalizar, char &sumar, char &restar, char &multiplicar, char &dividir, char &fact, char &expo, char &raiz, char &simplificar);
void leerFraccion(double &num, int &den);
void opSumar(double &num1, int &den1, double num2, int den2);
void opRestar(double &num1, int &den1, double num2, int den2);
void opDividir(double &num1, int &den1, double num2, int den2);
void opMultiplicar(double &num1, int &den1, double num2, int den2);
void opPotencia(double &num1, int &den1, double num2, int den2, bool &error);
void opFactorial(double &num1, int &den1, bool &error);
void opSimplificar(double &num1, int &den1);
int mcd(int num,int den);
bool diferenteVariable(char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar);
void calculadoraNormal(double &num1, int &den1, double &num2, int &den2, bool &terminar, char &operador, bool &error, char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar, char opcion);

//Subprogramas

char mostrarMenu(char &opcion)
{
	cout << "***********************************" << endl;
	cout << "*        CALCULADORA  2.0         *" << endl;
	cout << "***********************************" << endl;
	cout << "* 1. - Calculadora Normal         *" << endl;
	cout << "* 2. - Calculadora con Fracciones *" << endl;
	cout << "* 3. - Configuracion              *" << endl;
	cout << "* 4. - Terminar                   *" << endl;
	cout << "***********************************" << endl;
	cout << "Elige una opcion ";
	cin >> opcion;
	cin.sync();	//Elimina el carácter '\n'
	system("cls");

return opcion;
}
void mostrarOperandos(char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char borrar, char simplificar, char opcion)
{
	cout << "*******************************************" << endl;
	cout << "*             CALCULADORA  2.0            *" << endl;
	cout << "*******************************************" << endl;
	cout << "* Sumar =       | " << sumar << " |  Restar =   | " << restar << " |   *" << endl;
	cout << "* Multiplicar = | " << multiplicar << " |  Dividir =  | " << dividir << " |   *"<< endl;
	if (opcion == '1')
		{cout << "* Factorial =   | " << fact << " |  Raiz =     | " << raiz << " |   *" << endl;}
	cout << "* Exponencial = | " << expo << " |  Borrar =   | " << borrar << " |   *"<< endl;
	if (opcion == '2')
		{cout << "* Simplificar = | " << simplificar << " |                     *"<< endl;}
	cout << "*******************************************" << endl;
	cout << endl;
}
void cargarConfiguracion(char &borrar, char &finalizar, char &sumar, char &restar, char &multiplicar, char &dividir, char &fact, char &expo, char &raiz, char &simplificar)
{
	ifstream confint;
	
	confint.open("conf.txt");
	
	confint >> borrar;
	confint >> finalizar;
	confint >> sumar;
	confint >> restar;
	confint >> multiplicar;
	confint >> dividir;
	confint >> fact; 
	confint >> expo;
	confint >> raiz;
	confint >> simplificar;
	
	confint.close();
}
void guardarConfiguracion(char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar)
{
	ofstream confout;
	
	confout.open("conf.txt");
	
	confout << borrar;
	confout << finalizar;
	confout << sumar;
	confout << restar;
	confout << multiplicar;
	confout << dividir;
	confout << fact; 
	confout << expo;
	confout << raiz;
	confout << simplificar;
	
	confout.close();
}
bool diferenteVariable(char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar)
{
	bool repetir = false;

	if((borrar == finalizar) || (borrar == sumar) || (borrar == restar) || (borrar == multiplicar) || (borrar == dividir) || (borrar == fact) || (borrar == expo) || (borrar == raiz) || (borrar == simplificar))
	{repetir = true;}	
	else if((finalizar == borrar) || (finalizar == sumar) || (finalizar == restar) || (finalizar == multiplicar) || (finalizar == dividir) || (finalizar == fact) || (finalizar == expo) || (finalizar == raiz) || (finalizar == simplificar))
	{repetir = true;}	
	else if((sumar == finalizar) || (sumar == borrar) || (sumar == restar) || (sumar == multiplicar) || (sumar == dividir) || (sumar == fact) || (sumar == expo) || (sumar == raiz) || (sumar == simplificar))
	{repetir = true;}
	else if((restar == finalizar) || (restar == sumar) || (restar == borrar) || (restar == multiplicar) || (restar == dividir) || (restar == fact) || (restar == expo) || (restar == raiz) || (restar == simplificar))
	{repetir = true;}	
	else if((multiplicar == finalizar) || (multiplicar == sumar) || (multiplicar == restar) || (multiplicar == borrar) || (multiplicar == dividir) || (multiplicar == fact) || (multiplicar == expo) || (multiplicar == raiz) || (multiplicar == simplificar))
	{repetir = true;}	
	else if((dividir == finalizar) || (dividir == sumar) || (dividir == restar) || (dividir == multiplicar) || (dividir == borrar) || (dividir == fact) || (dividir == expo) || (dividir == raiz) || (dividir == simplificar))
	{repetir = true;}	
	else if((fact == finalizar) || (fact == sumar) || (fact == restar) || (fact == multiplicar) || (fact == dividir) || (fact == borrar) || (fact == expo) || (fact == raiz) || (fact == simplificar))
	{repetir = true;}	
	else if((expo == finalizar) || (expo == sumar) || (expo == restar) || (expo == multiplicar) || (expo == dividir) || (expo == fact) || (expo == borrar) || (expo == raiz) || (expo == simplificar))
	{repetir = true;}
	else if((raiz == finalizar) || (raiz == sumar) || (raiz == restar) || (raiz == multiplicar) || (raiz == dividir) || (raiz == fact) || (raiz == expo) || (raiz == borrar) || (raiz == simplificar))
	{repetir = true;}	
	else if((simplificar == finalizar) || (simplificar == sumar) || (simplificar == restar) || (simplificar == multiplicar) || (simplificar == dividir) || (simplificar == fact) || (simplificar == expo) || (simplificar == raiz) || (simplificar == borrar))
	{repetir = true;}	

	return repetir;
}
void cambiarConfiguracion(char &borrar, char &finalizar, char &sumar, char &restar, char &multiplicar, char &dividir, char &fact, char &expo, char &raiz, char &simplificar)
{	
	bool repetir = false;
	do
	{
		system("cls");   
		cout << "***********************************" << endl;
		cout << "*        CALCULADORA  2.0         *" << endl;
		cout << "***********************************" << endl;
		cout << "*         CONFIGURACION           *" << endl;
		cout << "***********************************" << endl;
		cout << endl;
		if (repetir)
			{cout << "Algun valor se ha repetido, por favor, introduzcalos de nuevo" << endl << endl;}
		cout << "Si pulsa Intro se quedara el valor actual"<< endl << endl;
		cout << "Introduce el nuevo valor para Borrar (Actual: " << borrar << " ): ";
		if (cin.peek() != '\n')
			{cin >> borrar;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Finalizar (Actual: " << finalizar << " ): ";
		if (cin.peek() != '\n')
			{cin >> finalizar;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Sumar (Actual: " << sumar << " ): ";
		if (cin.peek() != '\n')
			{cin >> sumar;}
		cin.sync();	
		cout << endl;
		cout << "Introduce el nuevo valor para Restar : (Actual: " << restar << " ): ";
		if (cin.peek() != '\n')
			{cin >> restar;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Multiplicar : (Actual: " << multiplicar << " ): ";
		if (cin.peek() != '\n')
			{cin >> multiplicar;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Dividir : (Actual: " << dividir << " ): ";
		if (cin.peek() != '\n')
			{cin >> dividir;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Factorial : (Actual: " << fact << " ): ";
		if (cin.peek() != '\n')
			{cin >> fact;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Exponencial : (Actual: " << expo << " ): ";
		if (cin.peek() != '\n')
			{cin >> expo;}
		cin.sync();
		cout << endl;
		cout << "Introduce el nuevo valor para Raiz : (Actual: " << raiz << " ): ";
		if (cin.peek() != '\n')
			{cin >> raiz;}
		cin.sync();
		cout << endl;		
		cout << "Introduce el nuevo valor para Simplificar : (Actual: " << simplificar << " ): ";
		if (cin.peek() != '\n')
			{cin >> simplificar;}
		cin.sync();
		cout << endl;
		system("cls");
		repetir = true;
	}while(diferenteVariable(borrar, finalizar, sumar, restar, multiplicar, dividir, fact, expo, raiz, simplificar));
}
void leerFraccion(double &num, int &den)
{
	char aux;
	
	cin >> num;
	aux = cin.peek();
	if (aux == '|')
		{cin.get();
		cin >> den;}
	else
		{den = 1;}
	
}  
void opSumar(double &num1, int &den1, double num2, int den2)
{
	num1 = num1 * den2 + num2 * den1;
	den1 = den1 * den2;
}
void opRestar(double &num1, int &den1, double num2, int den2)
{
	num1 = num1 * den2 - num2 * den1;
	den1 = den1 * den2;
}
void opMultiplicar(double &num1, int &den1, double num2, int den2)
{
	num1 = num1 * num2;
	den1 = den1 * den2;
}
void opDividir(double &num1, int &den1, double num2, int den2)
{
	num1 = num1 * den2;
	den1 = den1 * num2;
}
void opPotencia(double &num1, int &den1, double num2, int den2, bool &error)
{
	int aux2;
	double aux1;
	
	if((num2 / den2 == int(num2 / den2)) && (num2 > 0)) //Comprobacion de que es natural
	{
		aux1 = num1;
		aux2 = den1;
		while (num2 > 1)
		{
			num1 = num1 *aux1;
			den1 = den1 * aux2;
			num2--;
		}
	}
	else
		{error = true;}
}
void opFactorial(double &num1, int &den1, bool &error)
{
	int factorial;
	int aux;
	
	if (((num1 / den1) == int(num1 / den1)) && (num1 > 0)) //Comprobacion de que es natural
	{	
		aux = (num1 / den1);
		factorial = --aux;
		while (factorial > 1)
		{
		num1 = num1 * factorial;
		factorial--;
		}
	}
	else
	{error = true;}
}
void opSimplificar(double &num1, int &den1)
{
	int aux;
    
	aux = den1;
	den1 = den1 / mcd(num1, den1);
	num1 = num1 / mcd(num1, aux);
}
int mcd(int num,int den)
{
	int resto;
	
	while (den != 0)
    {
		resto = (num % den);
		num = den;
		den = resto;  
	}
return num;
}
void calculadoraNormal(double &num1, int &den1, double &num2, int &den2, bool &terminar, char &operador, bool &error, char borrar, char finalizar, char sumar, char restar, char multiplicar, char dividir, char fact, char expo, char raiz, char simplificar, char opcion)
{

	cin.get(operador);
	
	if ((operador != finalizar) && (operador != borrar) && (operador != sumar) && (operador != restar) && (operador != multiplicar) && (operador != dividir) && (operador != expo) && (operador != fact) && (operador != raiz) && (operador != simplificar) && (operador != '\n'))
	error = true;
	if ((operador == simplificar) && (opcion == '1'))
		error = true;
	if (opcion == '2')
	{
		if(operador == raiz)
		error = true;
		if(operador == fact)
		error = true;
	}
	//if ((opcion == '2') && (operador == fact))
	//	error = true;
	
if (operador == borrar)
	{num1 = 0; den1 = 1;}
else if (operador == finalizar )
	{terminar = true;}
else if (operador == sumar)
	{
	leerFraccion(num2, den2);
	opSumar(num1, den1, num2, den2);
	}
else if (operador == restar)
	{
	leerFraccion(num2, den2);
	opRestar(num1, den1, num2, den2);
	}
else if (operador == multiplicar)
	{
	leerFraccion(num2, den2);
	opMultiplicar(num1, den1, num2, den2);
	}
else if (operador == dividir)
	{
	leerFraccion(num2, den2);
	opDividir(num1, den1, num2, den2);
	}
else if (operador == expo)
	{
	leerFraccion(num2, den2);
	opPotencia(num1, den1, num2, den2, error);
	}
else if (operador == fact)
	{opFactorial(num1, den1, error);}
else if (operador == raiz)
	{
	if (num1 > 0)
		{num1 = sqrt(num1 / den1);}
	else
		{error = true;}
	}
else if (operador == simplificar)
	{opSimplificar(num1, den1);}
}


int main()
{
	//Declaraciones
	char borrar = 'c', finalizar = 'x', sumar = '+', restar = '-', dividir = '/', multiplicar = '*';
	char fact = '!', expo = '^', raiz = 'r', simplificar = 's';
	double num1 = 0, num2 = 0;
	int den1 = 1, den2 = 1;
	char opcion, operador, peek;
	bool acabar = false, terminar = false, error = false;
	ofstream logout;

	system("cls");
	//Cargar Configuracion
	cargarConfiguracion(borrar, finalizar, sumar, restar, multiplicar, dividir, fact, expo, raiz, simplificar);
	//Se abre el archivo del LOG
	logout.open("log.txt");
	//Mostrar Menu
	do
	{
		mostrarMenu(opcion);
		switch(opcion) //Elección en el menú principal.
		{
		case '1':	//Opcion toma valor 1 = Calculadora Normal
		case '2':	//Opcion toma valor 2 = Calculadora Fraccional
			{
				mostrarOperandos(sumar, restar, multiplicar, dividir, fact, expo, raiz, borrar, simplificar, opcion);
				do	//Repite la realización de una !NUEVA! operación.
				{
					cout << "Introduce una expresion:  (Para salir pulse " << finalizar << ")" << endl << ">> ";
					logout << ">>> ";
					peek = cin.peek();	//Comprueba que viene numero para tomarlo como el primer Numero de la operacion
					if (isdigit(peek))
					{
						leerFraccion(num1, den1);
						if (opcion == '1')	//Calculadora Normal
							logout << (num1 / den1);
						if (opcion == '2')	//Calculadora Fraccional
							logout << num1 << "|" << den1;
					}

					do	//Repite la realización de los operandos.
					{
						calculadoraNormal(num1, den1, num2, den2, terminar, operador, error, borrar, finalizar, sumar, restar, multiplicar, dividir, fact, expo, raiz, simplificar, opcion);
						logout << operador; //Escribe el operador en el log.txt

						if((operador != simplificar) && (operador != raiz) && (operador != fact) && (!terminar) && (!error) && (operador != '\n')) //Operadores que no usan un segundo numero
						{
							if(opcion == '1')	//Calculadora Normal
								{logout << (num2 / den2);}
							if(opcion == '2')	//Calculadora Fraccional
								{logout << num2 << '|' << den2;}
						}
							
					}while((operador != '\n') && (!terminar)); //Finalizacion bucle que repite operador en la misma operacion
					
					cout << endl;
					if (!terminar)	//Una vez finalizado el bucle y no terminado de hacer operaciones
					{
						if(!error) //Mostrar resultado NO error
						{
							if (opcion == '1')	//Calculadora Normal 
							{
								cout << (num1 / den1) << endl << endl;
								logout << (num1 / den1) << endl << endl;
							}
							if (opcion == '2')	//Calculadora Fraccional
							{
								cout << num1 << '|' << den1 << endl << endl;
								logout << endl << num1 << "|" << den1 << endl << endl;
							}
						}
						if (error)	//Mostrar resultado CON error
						{
							cout << "Error" << endl;
							logout << endl << "*** ERROR ***" << endl << endl;
							error = false;
						}
					}
					
				}while(!terminar);	//Finaliza el bucle de realizacion de operaciones
				terminar = false;
				logout << endl;
				system("cls");	//Limpia pantalla
			}
			break;
		case '3':	//Configuracion
			{
				cambiarConfiguracion(borrar, finalizar, sumar, restar, multiplicar, dividir, fact, expo, raiz, simplificar);	//Cambia variables operadores
			}
			break;
		case '4':	//Terminar
			{
			guardarConfiguracion(borrar, finalizar, sumar, restar, multiplicar, dividir, fact, expo, raiz, simplificar);	//Escribe las nuevas variables en el conf.txt
			acabar = true;
			}
			break;
		}	//Finaliza Menu (Switch)



	}while(!acabar);	//Termina el programa
	logout.close();		//Se cierra el archivo del LOG
return 0;
}