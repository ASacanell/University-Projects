#include<iostream>
using namespace std;
#include "Pelicula.h"


tPelicula leerPelicula(){
	tPelicula pelicula;
	char c;
	cout<<"Escriba el codigo de la pelicula: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El codigo debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin >> pelicula.codigo;

	cout<<"Escriba el titulo: ";
	cin.sync();
	getline(cin, pelicula.titulo);
	cout<<"Escriba el genero: ";
	leerGenero(pelicula.genero);
	cout<<"Escriba la duracion: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"La duracion debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin>>pelicula.duracion;
	cout<<"Escriba el precio: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El precio debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin>>pelicula.precio;
	pelicula.disponible = true;
	
	return pelicula;
}

void mostrarPelicula(tPelicula pelicula){
	cout<<"Codigo pelicula: "<< pelicula.codigo<<endl;
	cout<<"Titulo: "<<pelicula.titulo<<endl;
	cout<<"Genero: ";
	escribirGenero(pelicula.genero);
	cout<<endl;
	cout<<"Duracion: "<<pelicula.duracion<<" minutos"<<endl;
	cout<<"Precio: "<<pelicula.precio<<" euros"<<endl;
	cout<<"Disponible: ";
	if(pelicula.disponible == true) cout<<"Si"<<endl<<endl;
	else cout<<"No"<<endl<<endl;
}

void editarPelicula(tPelicula &pelicula)
{
	char c;
	cin.sync();
	cout<<endl<<"Escriba el nuevo titulo (intro para mantener): ";
	if(cin.peek() != '\n'){
		getline(cin, pelicula.titulo);
	}
	cin.sync();
	cout << endl << "Escriba el nuevo genero (intro para mantener): ";
	leerGenero(pelicula.genero);
	cin.sync();
	cout << endl << "Escriba la nueva duracion (intro para mantener): ";
	if(cin.peek() != '\n'){
		do{
			cin.sync();
			c = cin.peek();
			if(!isdigit(c)){
				cout <<"La duracion debe ser un numero: ";
			}
		}while(!isdigit(c));
		cin>>pelicula.duracion;
	}
	cin.sync();
	cout << endl << "Escriba el nuevo precio (intro para mantener): ";
	if(cin.peek() != '\n'){
		do{
			cin.sync();
			c = cin.peek();
			if(!isdigit(c)){
				cout <<"El precio debe ser un numero: ";
			}
		}while(!isdigit(c));
		cin>>pelicula.precio;
	}
	cin.sync();
}