#include "ListaPelicula.h"

bool listaPeliculasLlena(tListaPeliculas listaPeliculas){
	bool llena;
	if(listaPeliculas.contador < listaPeliculasMax) llena = false;
	else llena = true;
	return llena;
}

void buscarPeliculas(tListaPeliculas listaPeliculas, int codigo, bool &encontrado, int &posicion){
	int ini = 0, fin = listaPeliculas.contador, mitad;
	encontrado = false;
	while((ini <= fin) && !encontrado){
		mitad = (ini + fin) / 2;
		if(codigo == listaPeliculas.elementos[mitad].codigo) encontrado = true;
		else if (codigo < listaPeliculas.elementos[mitad].codigo) fin = mitad - 1;
		else ini = mitad + 1;
	}
	if(encontrado)posicion = mitad;
	else{
		if(fin<0) posicion = 0;
		else if(fin == listaPeliculas.contador) posicion = listaPeliculas.contador;
		else posicion = fin +1;
	}
}

void insertarPelicula(tListaPeliculas &listaPeliculas, tPelicula pelicula, int posicion){
		listaPeliculas.contador++;
		for(int i = listaPeliculas.contador; i >= posicion; i--){
			listaPeliculas.elementos[i+1] = listaPeliculas.elementos[i];
		}
		listaPeliculas.elementos[posicion] = pelicula;	
	
}

bool bajaPelicula(tListaPeliculas listaPeliculas, int codigo){
	bool encontrado = false, posible;
	int posicion = 0;
	buscarPeliculas(listaPeliculas, codigo, encontrado, posicion);
	if (encontrado == true){
		listaPeliculas.elementos[posicion].disponible = false;
		posible = true;
	}
	else posible = false;

	return posible;
}