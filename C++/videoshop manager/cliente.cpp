#include<iostream>
#include<string>
using namespace std;
#include "Cliente.h"

tCliente leerCliente(){
	tCliente cliente;
	bool correcto = false;
	char intro;
	cout<<"Escriba el nombre del cliente: ";
	cin.sync();
	getline(cin, cliente.nombre);
	cin.sync();
	cout<<"Escriba los apellidos: ";
	getline(cin, cliente.apellidos);
	cin.sync();
	cout<<"Introduce su genero favorito: ";
	leerGenero(cliente.genero);
	while(!correcto){
		cout<<"Introduzca el nif (8 numeros y letra)";
		cin>> cliente.nif;
		if(cliente.nif.length() == 9){
			correcto = true;
			int i = 0;
			while(i < 9 && correcto){
				if(i < 8){
					if(isalpha(cliente.nif[i])) correcto = false;
				}
				else{
					if(isdigit(cliente.nif[i])) correcto = false;
					cliente.nif[i] = toupper(cliente.nif[i]);
				}
				i++;
			}
		}
	}
	cliente.visionados.contador = 0;

	return cliente;
}

void mostrarCliente(tCliente cliente, bool mostrarDetalles, tListaPeliculas listaP){
	bool encontrado = false;
	int posicion;
	cout<<"Nombre: "<<cliente.nombre<<endl;
	cout<<"Apellidos: "<<cliente.apellidos<<endl;
	cout<<"NIF: "<<cliente.nif<<endl;
	cout<<"Genero favorito: ";
	escribirGenero(cliente.genero);
	cout<<endl;
	cout<<"Numero de peliculas visionadas: "<<cliente.visionados.contador<<endl;
	if(mostrarDetalles){
		for(int i = 0; i <cliente.visionados.contador; i++){
			buscarPeliculas(listaP, cliente.visionados.elementos[i].codigo, encontrado, posicion);
			if(encontrado){
				cout<<listaP.elementos[posicion].titulo<<"  -  ";
				escribirGenero(listaP.elementos[posicion].genero);
				cout<<"  -  ";
				if(cliente.visionados.elementos[i].gusto) cout<<"Si le ha gustado"<<endl;
				else cout<<"No le ha gustado"<<endl;
			}
		}
	}
}

void editarCliente(tCliente &cliente){
	cin.sync();
	cout<<endl<<"Nuevo nombre del cliente (intro para mantener): ";
	if(cin.peek() != '\n')
		{getline(cin, cliente.nombre);}
	cin.sync();;
	cout<<endl<<"Nuevos apellidos (intro para mantener): ";
	cin.sync();
	if(cin.peek() != '\n')
		{getline(cin, cliente.apellidos);}
	cin.sync();
	
	cout<<endl<<"Escriba el nuevo genero (intro para mantener): ";
	cin.sync();
	leerGenero(cliente.genero);

	
}

void altaVisionado(int codigo, int gusto, tCliente &cliente){
	cliente.visionados.elementos[cliente.visionados.contador].codigo = codigo;
	if(gusto == 1)cliente.visionados.elementos[cliente.visionados.contador].gusto = true;
	else cliente.visionados.elementos[cliente.visionados.contador].gusto = false;
	cliente.visionados.contador++;
}

void datosVisionado(tCliente cliente, int codigo, int &numeroVisionados, int &numeroGustos){
	numeroVisionados = 0;
	numeroGustos = 0;
	for(int i = 0; i< cliente.visionados.contador; i++){
		if(cliente.visionados.elementos[i].codigo == codigo){
			numeroVisionados++;
			if(cliente.visionados.elementos[i].gusto == true) numeroGustos++;
		}
	}
}