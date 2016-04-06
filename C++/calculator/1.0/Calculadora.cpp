//Apellidos: Sacanell Nogales	//Nombre: Alejandro	//DNI: 05435094 X
//Apellidos: Peinado Delgado	//Nombre: Guillermo	//DNI: 76088775 Z
//Grupo: 28		//Laboratorio: 2

#include <iostream>
#include <cmath>
using namespace std;

int main ()
{
	//Declaraciones
	double num1 = 0;
	double num2, resul;
	int factorial;
	char operador, opcion, opcion2, peek;
	char borrar = 'c', finalizar = 'x';
	bool terminar = false, error = false;
	
	do{
		cout << " 1. - Calculadora" << endl;
		cout << " 2. - Configuracion" << endl;
		cout << " 3. - Terminar" << endl;
		cout << "Opcion ";
		cin >> opcion;
		cin.sync();	//Elimina el carácter '\n'
		switch(opcion) //Eleccion en el menú principal
		{
			case '1' :
			{		//	CALCULADORA
				do{ //Repite la realización de una !NUEVA! operación.
					cout << "Introduce una expresion: " << endl;
					peek = cin.peek();
					if (isdigit(peek))
						{cin >> num1;}
						do{ //Repite la realizacion de los operandos
							cin.get(operador);
							if (operador == borrar)
								{resul = 0;}
							if (operador == finalizar)
								{terminar = true;}
								
								switch (operador)
									{
									
									case '+' : {
									cin >> num2;
									resul = num1 + num2;
									break;}
									
									case '-' : {
									cin >> num2;
									resul = num1 - num2;
									break;}
									
									case '*' : {
									cin >> num2;
									resul = num1 * num2;
									break;}
									
									case '/' : {
									cin >> num2;
									resul = num1 / num2;
									break;}
									
									case 'r' : {
										if (num1 > 0) //Comprobacion de que es positivo
											resul = sqrt(num1);
										else
											{error = true;}
										break;}
										
									case '^' : {
									cin >> num2;
										if ((num2 == int(num2)) && (num2 > 0)) //Comprobacion de que es natural
											{resul = num1;
											while (num2 > 1)
												{
												resul = resul * num1;
												num2--;
													if (num2 == 1)
														break;
												}
											}
										else
											{error = true;}
											break;}
											
									case '!' : {
										if ((num1 == int(num1)) && (num1 > 0)) //Comprobacion de que es natural
											{
												resul = num1;
												factorial = --num1;
												while (factorial > 1)
												{
													resul = resul * factorial;
													factorial--;
													if (factorial == 1)
													{break;}
												}
											}
										else
										{error = true;}
									break;}
									
									}
							
								{num1 = resul;}		//Asocia a num1 el valor de la última operación
						}while((operador != '\n') && (!terminar));  //Finalizacion bucle realizacion operandos
											
							if ((!error) && (!terminar))		//Si no da error y no es la última operación
								{cout << ">>> " << resul << endl;}
							if (error)		//Si da error en alguna operación
								{cout << "Error" << endl;
								error = false;}
														
				}while(!terminar);	// Finalizacion bucle realizar operaciones nuevas
				break;
			}		//Finaliza el caso 'Calculadora'
				
			case '2' :		//	CONFIGURACION
			{
			cout << "1- Cambiar Borrar : " << borrar << endl;
			cout << "2- Cambiar Finalizar : " << finalizar << endl;
			cout << "Opcion ";
			cin >> opcion2;
				switch(opcion2)		//Menu de configuración
				{
					case '1' :{
					cout << "Cambiar la funcion borrar por la letra: ";
					cin >> borrar;
					break;}
					
					case '2' :{
					cout << "Cambiar la funcion finalizar por la letra: ";
					cin >> finalizar;
					break;}
					
				}
			break;
			}		//Finaliza el caso 'Configuración'
			
			case '3' :{		//	TERMINAR
			terminar = true;
			break;}			//Finaliza 'terminar'
		}
			
	}while (!terminar);		//Termina el programa
	return 0;
}