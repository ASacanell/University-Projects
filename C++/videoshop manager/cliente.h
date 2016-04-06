#ifndef CLIENTE_H
#define CLIENTE_H

#include "Genero.h"
#include "ListaPelicula.h"
const int peliculasVisionadas = 40;

typedef struct{
	int codigo;
	bool gusto;
}tRegistro;

typedef tRegistro tElementosC[peliculasVisionadas];

typedef struct{
	int contador;
	tElementosC elementos;
}tVisionados;

typedef struct{
	string nif;
	string nombre;
	string apellidos;
	tGenero genero;
	tVisionados visionados;
}tCliente;

//Funcion:	Lee la informacion de un cliente de la consola
//Salida:	Devuelve una variable tCliente debidamente inicializada
tCliente leerCliente();
//Funcion:	Muestra los datos de un cliente por consola
//Entrada:	Recibe una variable tCliente ya inicializada con los datos, una variable bool que indica si se muestran o no los detalles que estan en la variable tListaPeliculas
void mostrarCliente(tCliente cliente, bool mostrarDetalles, tListaPeliculas listaP);
//Funcion:	Permite al usuario cambiar ciertos datos de una variable tCliente
//Entrada:	La variable tCliente a modificar
//Salida:	La variable tCliente modificada
void editarCliente(tCliente &cliente);
//Funcion:	Incluye un nuevo visionado en los ficha de un cliente
//Entrada:	Las variables codigo y gusto con la informacion de la pelicula y la variable tCliente donde incluirla
//Salida:	La variable tCliente modificada
void altaVisionado(int codigo, int gusto, tCliente &cliente);
//Funcion:	Indica cuantas veces vio y cuantas veces le gusto una pelicula a un cliente
//Entrada:	Las variables codigo y gusto con la informacion de la pelicula y la variable tCliente donde buscarla
//Salida:	Dos variables int, una con el numero de visionados de la pelicula y otra con cuantas veces le gusto
void datosVisionado(tCliente cliente, int codigo, int &numeroVisionados, int &numeroGustos);


#endif