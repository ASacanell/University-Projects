#include <string>
using namespace std;
#include "ListaCliente.h"

bool listaClienteLlena(tListaCliente cliente){
	bool llena = false;
	if(cliente.contador == listaClientesMax) llena = true;
	return llena;
}

void buscarCliente(tListaCliente listaCliente, string nif, bool &encontrado, int &posicion){
	int ini=0, fin = listaCliente.contador, mitad;
	encontrado = false;
	while((ini <= fin) && !encontrado){
		mitad = (ini + fin) / 2;
		if(nif == listaCliente.cliente[mitad].nif) encontrado = true;
		else if (nif < listaCliente.cliente[mitad].nif) fin = mitad - 1;
		else ini = mitad + 1;
	}
	if(encontrado)posicion = mitad;
	else{
		if(fin<0) posicion = 0;
		else if(fin == listaCliente.contador) posicion = listaCliente.contador;
		else posicion = fin +1;
	}
}

void insertarCliente(tCliente cliente, tListaCliente &listaCliente, int posicion){
	for(int i = listaCliente.contador; i >= posicion; i--){
		listaCliente.cliente[i+1] = listaCliente.cliente[i];
	}
	listaCliente.cliente[posicion] = cliente;
	listaCliente.contador++;
	
}

void bajaCliente(string nif, tListaCliente &listaCliente){
	bool encontrado;
	int posicion;
	buscarCliente(listaCliente, nif, encontrado, posicion);
	for(int i = posicion +1; i <= listaCliente.contador; i++){
			listaCliente.cliente[i-1] = listaCliente.cliente[i];
	}
	listaCliente.contador--;
}