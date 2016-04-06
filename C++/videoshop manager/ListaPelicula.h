#ifndef LISTAPELICULA_H
#define LISTAPELICULA_H

#include "Pelicula.h"

const int listaPeliculasMax = 30;
typedef tPelicula tElementosP[listaPeliculasMax];
typedef struct{
	int contador;
	tElementosP elementos;
}tListaPeliculas;
//Funcion:	Comprueba si la lista de peliculas esta llena 
//Entrada:	Recibe una variable tListaPeliculas para comprobarlo
//Salida:	Devuelve un booleano indicando si esta llena (true) o no (false)
bool listaPeliculasLlena(tListaPeliculas listaPeliculas);
//Funcion:	Busca una pelicula de una lista de peliculas e indica si se ha encontrado o no
//Entrada:	Recibe un codigo de una pelicula y una variable tListaPeliculas donde buscarla
//Salida:	Devuelve un booleano que indica si se ha encontrado y un variable que indica en que posicion
void buscarPeliculas(tListaPeliculas listaPeliculas, int codigo, bool &encontrado, int &posicion);
//Funcion:	Inserta una pelicula en la lista de peliculas
//Entrada:	Recibe una variable tListaPeliculas, una tPelicula y la posicion donde insertarla 
//Salida:	Devuelve una variable tListaPeliculas con la pelicula insertada
void insertarPelicula(tListaPeliculas &listaPeliculas, tPelicula pelicula, int posicion);
//Funcion:	Busca una pelicula en una lista y si esta la pone como no disponible
//Entrada:	Recibe el codigo de una pelicula y una variable tListaPeliculas donde buscarla
//Salida:	Devuelve un booleano que indica si se ha podido realizar la operacion o no
bool bajaPelicula(tListaPeliculas listaPeliculas, int codigo, bool &encontrado);

#endif