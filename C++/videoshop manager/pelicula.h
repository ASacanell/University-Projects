#ifndef PELICULA_H
#define PELICULA_H
#include<string>
using namespace std;
#include "genero.h"

typedef struct{
	int codigo;
	string titulo;
	tGenero genero;
	int duracion;
	double precio;
	bool disponible;
}tPelicula;

//Funcion:	Lee la informacion de una pelicula de la consola
//Salida:	Devuelve una variable tPelicula debidamente inicializada
tPelicula leerPelicula();
//Funcion:	Muestra los datos de una pelicula por consola
//Entrada:	Recibe una variable tPelicula ya inicializada con los datos necesarios
void mostrarPelicula(tPelicula pelicula);
//Funcion:	Permite al usuario cambiar ciertos datos de una variable tPelicula
//Entrada:	La variable tPelicula a modificar
//Salida:	La variable tPelicula modificada
void editarPelicula(tPelicula &pelicula);

#endif