#include<iostream>
#include<string>
using namespace std;
#include "Genero.h";

void leerGenero(tGenero &genero){
	bool error = false;
	char tmp;
	cout << endl;
	cout << "(1) - Ciencia ficcion" << endl;
	cout << "(2) - Fantasia" << endl;
	cout << "(3) - Comedia" << endl;
	cout << "(4) - Terror" << endl;
	cout << "(5) - Drama" << endl;
	cout << "(6) - Comedia romantica" << endl;
		
	do{
		if(error){ 
			error = false;
			cout << "Introduzca de nuevo el genero: ";
		}
		tmp = cin.peek();

		if(tmp != '\n'){
			switch(tmp){
				case '1': genero = cienciaFiccion; break;
				case '2': genero = fantasia; break;
				case '3': genero = comedia; break;
				case '4': genero = terror; break;
				case '5': genero = drama; break;
				case '6': genero = comediaRomantica; break;
				default:  error = true; break;
			}
		}
		cin.sync();
	}while(error);
}
void escribirGenero(tGenero genero){
	string gen;
	switch(genero){
	case cienciaFiccion: gen = "Ciencia-ficcion"; break;
	case fantasia: gen = "Fantasia"; break;
	case comedia: gen = "Comedia"; break;
	case terror: gen = "Terror"; break;
	case drama: gen = "Drama"; break;
	case comediaRomantica: gen ="Comedia-romantica"; break;
	}

	cout << gen;
}