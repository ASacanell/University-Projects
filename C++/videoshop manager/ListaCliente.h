#ifndef LISTACLIENTE_H
#define LISTACLIENTE_H

#include "Cliente.h"

const int listaClientesMax = 20;

typedef tCliente tLista[listaClientesMax];

typedef struct{
	tLista cliente;
	int contador;
}tListaCliente;
//Funcion:	Comprueba si la lista de clientes esta llena 
//Entrada:	Recibe una variable tListaClientes para comprobarlo
//Salida:	Devuelve un booleano indicando si esta llena (true) o no (false)
bool listaClienteLlena(tListaCliente cliente);
//Funcion:	Busca un cliente de una lista de clientes e indica si se ha encontrado o no
//Entrada:	Recibe el nif de un cliente y una variable tListaCliente donde buscarle
//Salida:	Devuelve un booleano que indica si se ha encontrado y un variable que indica en que posicion
void buscarCliente(tListaCliente listaCliente, string nif, bool &encontrado, int &posicion);
//Funcion:	Inserta un cliente en una lista de clientes
//Entrada:	Recibe una variable tListaCliente, un tCliente y la posicion donde insertarlo
//Salida:	Devuelve una variable tListaCliente con el cliente insertado
void insertarCliente(tCliente cliente, tListaCliente &listaCliente, int posicion);
//Funcion:	Busca un cliente en una lista y si esta lo elimina de la misma
//Entrada:	Recibe el nif de un cliente y un variable tListaCliente donde buscarle
void bajaCliente(string nif, tListaCliente &listaCliente);

#endif