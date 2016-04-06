//Grupo 28-41
//Alejandro Sacanell Nogales --- 05435094X
//Diego Sierra Liras --- 70257191B

#include<iostream>
#include<string>
#include<iomanip>
#include<fstream>
#include<cctype>
#include"ListaPelicula.h"
#include"ListaCliente.h"
#include "menus.h"

using namespace std;


//Funcion:	Pausa el programa
//Entrada:
//Salida:
void pausarPrograma();

//Funcion:	Carga las peliculas en el array listaP
//Entrada:	Array listaP vacío.
//Salida:	Array listaP con las películas cargadas
void cargarPeliculas(tListaPeliculas &listaP);

//Funcion:	Ordena el array listaP por codigo
//Entrada:	Array listaP no ordenado
//Salida:	Array listaP ordenado
void ordenarPelis(tListaPeliculas &listaP);

//Funcion:	Carga los clientes en el array listaC
//Entrada:	Array listaC vacío.
//Salida:	Array listaC con los clientes cargados
void cargarClientes(tListaCliente &listaC);

//Funcion:	Ordena el array listaP por DNI
//Entrada:	Array listaC no ordenado
//Salida:	Array listaC ordenado
void ordenarClientes(tListaCliente &listaC);

//Funcion:	Muestra el menu interno, y realiza las opciones
//Entrada:	Lista de peliculas cargadas en el array
//Salida:	Array modificado después de la opción elegida
void gestionPeliculas(tListaPeliculas &listaP);

//Funcion:	Muestra el menu interno, y realiza las opciones
//Entrada:	Lista de clientes cargadas en el array, al igual que el de peliculas
//Salida:	Array listaC modificado después de la opcion elegida
void gestionClientes(tListaCliente &listaC, tListaPeliculas listaP);

//Funcion:	Muestra el menu interno, y realiza las opciones
//Entrada:	Lista de clientes y lista de peliculas cargadas
//Salida:	Array de clientes modificado despues de la opcion elegida.
void gestionGeneral(tListaCliente &listaC, tListaPeliculas listaP);

//Funcion:	Añade una pelicula nueva al array
//Entrada:	Array listaP
//Salida:	Array listaP con la nueva película añadida
void altaPelicula(tListaPeliculas &listaP);

//Funcion:	Muestra por pantalla los datos de una pelicula
//Entrada:	Array de peliculas
//Salida:	
void datosPelicula(tListaPeliculas listaP);

//Funcion:	Muestra por pantalla los datos de todas las peliculas
//Entrada:	Array de peliculas
//Salida:	
void datosListaPelis(tListaPeliculas listaP);

//Funcion:	Modifica algunos datos de la pelicula elegida
//Entrada:	Array peliculas
//Salida:	Array peliculas con la pelicula en cuestión modificada
void edicionPelicula(tListaPeliculas &listaP);

//Funcion:	Cambia la opción de pelicula a no disponible
//Entrada:	Lista de peliculas
//Salida:	Lista de peliculas con la pelicula dada de baja con el bool disponible en false
void eliminarPelicula(tListaPeliculas &listaP);

//Funcion:	Añade un nuevo cliente a la lista de clientes
//Entrada:	Array clientes
//Salida:	Array de clientes con un nuevo cliente, insertado en el lugar que le corresponde(debido a la ordenación por dni)
void nuevoCliente(tListaCliente &listaC);

//Funcion:	Muestra por pantalla los datos de un cliente
//Entrada:	Lista de clientes y lista de peliculas
//Salida:
void datosCliente(tListaCliente listaC, tListaPeliculas listaP);

//Funcion:	Muestra por pantalla los datos de todos los clientes
//Entrada:	Lista de clientes y lista de peliculas
//Salida:	
void datosListaCliente(tListaCliente listaC, tListaPeliculas listaP);

//Funcion:	Modifica los datos de un cliente en concreto
//Entrada:	Lista de clientes
//Salida:	Lista de clientes con el cliente modificado
void modificarCliente(tListaCliente &listaC);

//Funcion:	Elimina los datos de un cliente
//Entrada:	Lista de clientes
//Salida:	Lista de clientes sin el cliente que se ha eliminado
void eliminarCliente(tListaCliente &listaC, tListaPeliculas listaP);

//Funcion:	Muestra por pantalla las estadisticas de una pelicula vista y cuantas veces ha gustado o no
//Entrada:	Lista de clientes y de peliculas
//Salida:	Por pantalla
void estadisticasPeliculas(tListaPeliculas listaP, tListaCliente listaC);

//Funcion:	Exporta la lista de clientes y el numero de peliculas visionadas, todo ello ordenado por apellidos
//Entrada:	Array de clientes
//Salida:	
void exportarClientes(tListaCliente listaC);

//Funcion:	Añade una pelicula vista a un cliente
//Entrada:	Lista de peliculas y de clientes
//Salida:	Lista de clientes modificada, con el contador de visionado aumentado, y los datos de la pelicula visionada.
void nuevoVisionado(tListaCliente &listaCliente, tListaPeliculas listaPeliculas);

//Funcion:	Exporta las peliculas preparadas para la lectura en una nueva inicializacion del programa
//Entrada:	Array de peliculas
//Salida:	
void volcadoPeliculas(tListaPeliculas listaP);

//Funcion:	Exporta los clientes preparados para la lectura en una nueva inicializacion del programa
//Entrada:	Array de clientes
//Salida:	
void volcadoClientes(tListaCliente listaC);


int main()
{
	tListaCliente listaC;
	tListaPeliculas listaP;
	tGenero enumGenero = drama;
	bool final = false;
	

	cargarPeliculas(listaP);
	ordenarPelis(listaP);
	cargarClientes(listaC);
	ordenarClientes(listaC);
	cout << "--------------------------------------------------------------------------------";
	pausarPrograma();

	
	do
	{
		mostrarMenuPrincipal();
		switch(pedirOpcionPrincipal())
		{
			case 0:
				{
					final = true;
					cout << "Pulse intro para finalizar...";
					pausarPrograma();
				break;}	  
			case 1:
				{
					gestionPeliculas(listaP);	
				break;}
			case 2:
				{
					gestionClientes(listaC, listaP);
				break;}
			case 3: 
				{
					gestionGeneral(listaC, listaP);
				break;}
			}
	}while(!final);
	
	volcadoPeliculas(listaP);
	volcadoClientes(listaC);

	return 0;
}


void pausarPrograma()
	{
		cin.sync();
		cin.get();
		cin.sync();
	}

void gestionPeliculas(tListaPeliculas &listaP)
{
	bool principal = false;
	
	while(!principal)
	{
		mostrarMenuPeliculas();
		switch(pedirOpcionPeliculas())
		{
		case 0:
			{
			principal = true;
			break;}
		case 1:
			{
			altaPelicula(listaP);
			break;}
		case 2:
			{
			datosPelicula(listaP);
			break;}
		case 3:
			{
			datosListaPelis(listaP);
			break;}
		case 4:
			{
			edicionPelicula(listaP);
			break;}
		case 5:
			{
			eliminarPelicula(listaP);
			break;}
		}
	}
}

void gestionClientes(tListaCliente &listaC, tListaPeliculas listaP)
{
	bool principal = false;
	
	while(!principal)
	{
		mostrarMenuClientes();
		switch(pedirOpcionClientes())
		{
		case 0:
			{
			principal = true;	
			break;}
		case 1:
			{
			nuevoCliente(listaC);
			break;}
		case 2:
			{
			datosCliente(listaC, listaP);
			break;}
		case 3:
			{
			datosListaCliente(listaC, listaP);
			break;}
		case 4:
			{
			modificarCliente(listaC);
			break;}
		case 5:
			{
			eliminarCliente(listaC, listaP);
			break;}
		}
	}
}

void cargarPeliculas(tListaPeliculas &listaP)
{
	ifstream archivoP;
	string intro;
	int genero;
	char gusto;
	tPelicula pelicula;

	listaP.contador = 0;

	archivoP.open("pelis.txt");
	if(archivoP.is_open()){
		while (archivoP >> pelicula.codigo && pelicula.codigo != -1 && listaP.contador <= listaPeliculasMax){
			getline(archivoP, intro);
			getline(archivoP,pelicula.titulo);
			archivoP >> genero;
			switch(genero){
				case 0: pelicula.genero = cienciaFiccion; break;
				case 1: pelicula.genero  = fantasia; break;
				case 2: pelicula.genero  = comedia; break;
				case 3: pelicula.genero  = terror; break;
				case 4: pelicula.genero  = drama; break;
				case 5: pelicula.genero  = comediaRomantica; break;
			}
				
			archivoP>> pelicula.duracion;
			archivoP >>pelicula.precio>> gusto;
			if(gusto == 'S') pelicula.disponible = true;
			else pelicula.disponible = false;
			listaP.elementos[listaP.contador].codigo = pelicula.codigo;
			listaP.elementos[listaP.contador].titulo = pelicula.titulo;
			listaP.elementos[listaP.contador].genero = pelicula.genero;
			listaP.elementos[listaP.contador].duracion = pelicula.duracion;
			listaP.elementos[listaP.contador].precio = pelicula.precio;
			listaP.elementos[listaP.contador].disponible = pelicula.disponible;
			listaP.contador++;	
		}
		cout << "Archivo de peliculas cargado con exito (" << listaP.contador << " peliculas)." << endl;
	}
	else 
	{
		cout << "No se ha encontrado el archivo peliculas, empezando con una lista vacia." << endl;
	}
	archivoP.close();
}

void cargarClientes(tListaCliente &listaC)
{
	ifstream archivoC;
	int genero;
	string intro;
	char gusto;
	tCliente cliente;
	tRegistro registro;

	listaC.contador = 0;

	archivoC.open("clientes.txt");
	if(archivoC.is_open()){

		while(getline(archivoC,cliente.nif) && cliente.nif != "X" && listaC.contador <= listaClientesMax){
			getline(archivoC, cliente.nombre);
			getline(archivoC, cliente.apellidos);
			archivoC >> genero;
			switch(genero){
				case 0: cliente.genero = cienciaFiccion; break;
				case 1: cliente.genero  = fantasia; break;
				case 2: cliente.genero  = comedia; break;
				case 3: cliente.genero  = terror; break;
				case 4: cliente.genero  = drama; break;
				case 5: cliente.genero  = comediaRomantica; break;
			}
			
			listaC.cliente[listaC.contador].visionados.contador = 0;
			while(archivoC >> registro.codigo && registro.codigo != -1){
				archivoC >> gusto;
				if (gusto == 'S') registro.gusto = true;
				else if (gusto == 'N') registro.gusto = false;
				listaC.cliente[listaC.contador].visionados.elementos[listaC.cliente[listaC.contador].visionados.contador].codigo = registro.codigo;
				listaC.cliente[listaC.contador].visionados.elementos[listaC.cliente[listaC.contador].visionados.contador].gusto  = registro.gusto;
				listaC.cliente[listaC.contador].visionados.contador++;
			}
		getline(archivoC , intro);
		listaC.cliente[listaC.contador].nif = cliente.nif;
		listaC.cliente[listaC.contador].nombre = cliente.nombre;
		listaC.cliente[listaC.contador].apellidos = cliente.apellidos;
		listaC.cliente[listaC.contador].genero = cliente.genero;

		listaC.contador++;
		}
		cout << "Archivo de clientes cargado con exito (" << listaC.contador << " clientes)." << endl;
	}
	else
	{
		cout << "No se ha encontrado el archivo clientes, empezando con una lista vacia." << endl;
	}
	archivoC.close();
}
	
void ordenarPelis(tListaPeliculas &listaP)
{
	for(int i = 0; i <listaP.contador-1; i++){
		for(int j = listaP.contador-1; j > i; j--){
			if(listaP.elementos[j].codigo < listaP.elementos[j-1].codigo){
				tPelicula tmp;
				tmp = listaP.elementos[j];
				listaP.elementos[j]= listaP.elementos[j-1];
				listaP.elementos[j-1] = tmp;
			}
		}
	}
}

void ordenarClientes(tListaCliente &listaC)
{
	for(int i = 0; i <listaC.contador-1; i++){
		for(int j = listaC.contador-1; j > i; j--){
			if(listaC.cliente[j].nif< listaC.cliente[j-1].nif){
				tCliente tmp;
				tmp = listaC.cliente[j];
				listaC.cliente[j]= listaC.cliente[j-1];
				listaC.cliente[j-1] = tmp;
			}
		}
	}
}

void gestionGeneral(tListaCliente &listaC, tListaPeliculas listaP)
{
	bool principal = false;
	
	while(!principal)
	{
		mostrarMenuGestion();
		switch(pedirOpcionGeneral())
		{
		case 0:
			{
			principal = true;
			break;}
		case 1:
			{
			nuevoVisionado(listaC, listaP);
			break;}
		case 2:
			{
			exportarClientes(listaC);
			break;}
		case 3:
			{
			estadisticasPeliculas(listaP, listaC);
			break;}
		}
	}
}

void altaPelicula(tListaPeliculas &listaP)
{
	bool encontrado = false;
	int posicion = 0;
	tPelicula auxiliar;
	auxiliar = leerPelicula();
	buscarPeliculas(listaP,auxiliar.codigo, encontrado, posicion);
	if(encontrado) cout <<"No ha sido posible incluir la nueva pelicula, ya hay una con ese codigo."<<endl<<endl;
	else if(!listaPeliculasLlena(listaP)){
		insertarPelicula(listaP, auxiliar, posicion);
		cout << "Pelicula dada de alta correctamente" << endl;
	}

	pausarPrograma();
}

void datosPelicula(tListaPeliculas listaP)
{
	bool encontrado = false;
	int posicion = 0;
	int codigo;
	char c;

	cout<<"Introduzca el codigo de la pelicula: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El codigo debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin >> codigo;
	buscarPeliculas(listaP, codigo, encontrado, posicion);
	if(encontrado == true) mostrarPelicula(listaP.elementos[posicion]);
	else cout <<"No existe esa pelicula"<<endl;

	pausarPrograma();
}

void datosListaPelis(tListaPeliculas listaP)
{
	int op;

	cout<<"(1)- Ver todas las peliculas"<<endl;
	cout<<"(2)- Ver solo las disponibles"<<endl;
	cin >> op;
	cout<<"-------------------------------------"<<endl<<endl;
	cout<<"-        LISTADO DE PELICULAS       -"<<endl<<endl;
	cout<<"-------------------------------------"<<endl;
	if(op == 1){
		for(int i = 0; i < listaP.contador; i++){
			mostrarPelicula(listaP.elementos[i]);
			cout<<"-------------------------------------"<<endl;
			}
		}
	else if(op == 2){
		for(int i = 0; i < listaP.contador; i++){
			if(listaP.elementos[i].disponible){
				mostrarPelicula(listaP.elementos[i]);
				cout<<"-------------------------------------"<<endl;
			}
		}
	}
	else cout <<"Error, opcion no valida"<<endl;

	pausarPrograma();
}

void edicionPelicula(tListaPeliculas &listaP)
{
	int codigo, posicion = 0;
	bool encontrado = false;
	char c;

	cout<<"Introduzca el codigo de la pelicula: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El codigo debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin >> codigo;
	buscarPeliculas(listaP, codigo, encontrado, posicion);
	if(encontrado == true) 
	{
		editarPelicula(listaP.elementos[posicion]);
		cout << "Pelicula editada" << endl;
	}
	else cout <<"No existe esa pelicula"<<endl;

	pausarPrograma();
}

void eliminarPelicula(tListaPeliculas &listaP)
{
	int codigo, posicion = 0;
	bool encontrado = false;
	char c;

	cout<<"Introduzca el codigo de la pelicula: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El codigo debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin >> codigo;
	buscarPeliculas(listaP, codigo, encontrado, posicion);
	if(encontrado == true)
	{
		listaP.elementos[posicion].disponible = false;
		cout << "Pelicula dada de baja" << endl;
	}
	else cout <<"No existe esa pelicula"<<endl;

	pausarPrograma();
}

void nuevoCliente(tListaCliente &listaC)
{
	if(!listaClienteLlena(listaC))
	{
		bool encontrado = false;
		int posicion = 0;
		tCliente auxiliar;
		auxiliar = leerCliente();
		buscarCliente(listaC, auxiliar.nif, encontrado, posicion);
		if(encontrado) cout <<"No ha sido posible incluir el nuevo cliente, ya hay uno con ese nif."<<endl<<endl;
		else
		{
			insertarCliente(auxiliar, listaC, posicion);
			cout << "Cliente dado de alta" << endl;
		}

		pausarPrograma();
	}
}

void datosCliente(tListaCliente listaC, tListaPeliculas listaP)
{
	string nif;
	bool encontrado = false;
	int posicion = 0;
	bool mostrarDetalles;
	
	cout<<"Escriba el NIF del usuario: ";
	cin>> nif;
	nif[8] = toupper(nif[8]);
	buscarCliente(listaC, nif, encontrado, posicion);
	if(encontrado){
		mostrarDetalles = true;
		mostrarCliente(listaC.cliente[posicion], mostrarDetalles, listaP);
	}
	else cout<<"Error, no hay ningun usuario con ese NIF"<<endl;

	pausarPrograma();
}

void datosListaCliente(tListaCliente listaC, tListaPeliculas listaP)
{
	bool mostrarDetalles = false;

	cout<<"-------------------------------------"<<endl<<endl;
	cout<<"-        LISTADO DE CLIENTES        -"<<endl<<endl;
	cout<<"-------------------------------------"<<endl;
	for(int i = 0; i < listaC.contador; i++){
		mostrarCliente(listaC.cliente[i], mostrarDetalles, listaP);
		cout<<"-------------------------------------"<<endl;
	}

	pausarPrograma();
}

void modificarCliente(tListaCliente &listaC)
{
	string nif;
	int posicion = 0;
	bool encontrado;
	
	cout<<"Escriba el NIF del usuario: ";
	cin>> nif;
	nif[8] = toupper(nif[8]);
	buscarCliente(listaC, nif, encontrado, posicion);
	if(encontrado){
		editarCliente(listaC.cliente[posicion]);
		cout << "Datos del cliente editados" << endl;
	}
	else cout<<"Error, no hay ningun usuario con ese NIF"<<endl;

	pausarPrograma();
}

void eliminarCliente(tListaCliente &listaC, tListaPeliculas listaP)
{
	string nif;
	bool encontrado = false;
	int posicion = 0;
	bool mostrarDetalles;
	
	cout<<"Escriba el NIF del usuario: ";
	cin>> nif;
	nif[8] = toupper(nif[8]);
	buscarCliente(listaC, nif, encontrado, posicion);
	if(encontrado){
		int opcion;
		cout<<"Datos del cliente"<<endl<<endl;
		mostrarDetalles = false;
		mostrarCliente(listaC.cliente[posicion], mostrarDetalles, listaP);
		cout<<endl<<endl<<"¿Desea confirmar la baja del cliente?"<<endl<<"(1)- Si"<<endl<<"(2)- No"<<endl<<"Opcion: ";
		cin>>opcion;
		if(opcion == 1)
		{
			bajaCliente(nif, listaC);
			cout << "Cliente eliminado" << endl;
		}
	}
	else cout<<"Error, no hay ningun usuario con ese NIF"<<endl;

	pausarPrograma();
}

void nuevoVisionado(tListaCliente &listaCliente, tListaPeliculas listaPeliculas)
{	
	bool encontradoC, encontradoP, disponible;
	int codigo, gusto, posicionC, posicion;
	string nif;
	char c;
	cout<<"Escriba el codigo de la pelicula: ";
	do{
		cin.sync();
		c = cin.peek();
		if(!isdigit(c)){
			cout <<"El codigo debe ser un numero: ";
		}
	}while(!isdigit(c));
	cin >> codigo;

	buscarPeliculas(listaPeliculas, codigo, encontradoP, posicion);
	disponible = listaPeliculas.elementos[posicion].disponible;
	if(encontradoP && disponible)
	{
		cout<<"Escriba el NIF del cliente: ";
		cin >> nif;
		nif[8] = toupper(nif[8]);
		buscarCliente(listaCliente, nif, encontradoC, posicionC);
		if(encontradoC)
		{
			cout<<"¿Le gusto la pelicula?"<<endl<<"(1)-Si"<<endl<<"(2)-No"<<endl;
			cin >> gusto;
			altaVisionado(codigo, gusto, listaCliente.cliente[posicionC]);
			cout << "Visionado completado";
		}else
		{cout << "Cliente no existente";}
	}else
	{cout << "Pelicula no existente o no disponible";}

	pausarPrograma();
}

void exportarClientes(tListaCliente listaC)
{
	ofstream archivo;
 	string nombre;

	cout << "Nombre del archivo: ";
	cin >> nombre;

	archivo.open(nombre + ".txt");
	if(archivo.is_open())
	{
		for(int i = 0; i <listaC.contador-1; i++){
			for(int j = listaC.contador-1; j > i; j--){
				if(listaC.cliente[j].apellidos< listaC.cliente[j-1].apellidos){
					tCliente tmp;
					tmp = listaC.cliente[j];
					listaC.cliente[j]= listaC.cliente[j-1];
					listaC.cliente[j-1] = tmp;
				}
			}
		}

		for(int cliente=0;cliente<listaC.contador;cliente++)
		{
			archivo << listaC.cliente[cliente].apellidos << ", ";
			archivo << listaC.cliente[cliente].nombre;
			archivo << " (" << listaC.cliente[cliente].nif << ")" << endl;
			archivo << "		Ha visto " <<listaC.cliente[cliente].visionados.contador << " peliculas."<< endl << endl;
		}
		cout << "Log creado correctamente";
	}
	archivo.close();
	pausarPrograma();
}

void estadisticasPeliculas(tListaPeliculas listaP, tListaCliente listaC)
{
	int contadorVisionado = 0, contadorGustado= 0, tmp1 = 0, tmp2 = 0;

	cout << "------------------------------------" << endl;
	for(int pelicula=0;pelicula<listaP.contador;pelicula++)
	{
		contadorVisionado = 0;
		contadorGustado = 0;

		cout << "Titulo: " << listaP.elementos[pelicula].titulo << endl;
		for(int cliente=0;cliente < listaC.contador;cliente++)
		{
			datosVisionado(listaC.cliente[cliente], listaP.elementos[pelicula].codigo, tmp1, tmp2);
			contadorVisionado += tmp1;
			contadorGustado += tmp2;
		}
		cout << "Visionada: " << contadorVisionado << " veces" << endl;
		cout << "Gustado: " << contadorGustado << " veces"<< endl;
		cout << "Valoracion: ";
		if(contadorVisionado > 0)
			cout << (contadorGustado)*100/(contadorVisionado) << "%" << endl;
		else
			cout << "0%" << endl;
		cout << "------------------------------------" << endl;
	}
	pausarPrograma();
}
	
void volcadoPeliculas(tListaPeliculas listaP)
{
	ofstream archivo;

	archivo.open("pelis.txt");
	if(archivo.is_open())
	{
		for(int peli=0;peli<listaP.contador;peli++)
		{
			archivo << listaP.elementos[peli].codigo << endl;
			archivo << listaP.elementos[peli].titulo << endl;
			archivo << listaP.elementos[peli].genero << endl;
			archivo << listaP.elementos[peli].duracion << endl;
			archivo << listaP.elementos[peli].precio << endl;
			if(listaP.elementos[peli].disponible)
			{
				archivo << "S" << endl;
			}
			else
			{
				archivo << "N" << endl;
			}
		}
		archivo << "-1";
	}

	archivo.close();
}

void volcadoClientes(tListaCliente listaC)
{
	ofstream archivo;
	int aux;

	archivo.open("clientes.txt");
	if(archivo.is_open())
	{
		for(int cliente=0;cliente<listaC.contador;cliente++)
		{
			archivo << listaC.cliente[cliente].nif << endl;
			archivo << listaC.cliente[cliente].nombre << endl;
			archivo << listaC.cliente[cliente].apellidos << endl;
			archivo << listaC.cliente[cliente].genero << endl;
			for(int visionadas=0;visionadas<listaC.cliente[cliente].visionados.contador;visionadas++)
			{
				archivo << listaC.cliente[cliente].visionados.elementos[visionadas].codigo << endl;
				if(listaC.cliente[cliente].visionados.elementos[visionadas].gusto)
				{
					archivo << "S" << endl;
				}
				else
				{
					archivo << "N" << endl;
				}
			}

			archivo << "-1" << endl;
		}

		archivo << "X";
	}

	archivo.close();
}