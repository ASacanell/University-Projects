#include "menus.h"

void mostrarMenuPrincipal()
{
	system("cls");
	cout << "***************************" << endl;
	cout << "* 1.- Gestionar peliculas *" << endl;
	cout << "* 2.- Gestionar clientes  *" << endl;
	cout << "* 3.- Gestion general     *" << endl;
	cout << "* 0.- Salir               *" << endl;
	cout << "***************************" << endl;
	cout << "Opcion: ";
}

int pedirOpcionPrincipal()
	{
		int opcion = 10;

		do{
		cin >> opcion;
		}while((opcion < 0) || (opcion > 3));

		return opcion;
	}

void mostrarMenuPeliculas()
{
	system("cls");
	cout << "************************************" << endl;
	cout << "* 1.- Alta de pelicula             *" << endl;
	cout << "* 2.- Datos de pelicula            *" << endl;
	cout << "* 3.- Datos de todas las peliculas *" << endl;
	cout << "* 4.- Edicion de pelicula          *" << endl;
	cout << "* 5.- Baja de pelicula             *" << endl;
	cout << "* 0.- Volver al menu anterior      *" << endl;
	cout << "************************************" << endl;
	cout << "Opcion: ";
}

void mostrarMenuClientes()
{
	system("cls");
	cout << "***********************************" << endl;
	cout << "* 1.- Nuevo cliente               *" << endl;
	cout << "* 2.- Datos de cliente            *" << endl;
	cout << "* 3.- Datos de todos los clientes *" << endl;
	cout << "* 4.- Editar cliente              *" << endl;
	cout << "* 5.- Baja de cliente             *" << endl;
	cout << "* 0.- Volver al menu anterior     *" << endl;
	cout << "***********************************" << endl;
	cout << "Opcion: ";
}

void mostrarMenuGestion()
{
	system("cls");
	cout << "***********************************" << endl;
	cout << "* 1.- Nuevo visionado             *" << endl;
	cout << "* 2.- Exportar lista de  clientes *" << endl;
	cout << "* 3.- Estadisticas de peliculas   *" << endl;
	cout << "* 0.- Volver al menu anterior     *" << endl;
	cout << "***********************************" << endl;
	cout << "Opcion: ";
}

int pedirOpcionPeliculas()
	{
		int opcion = 10;

		do{
		cin >> opcion;
		}while((opcion < 0) || (opcion > 5));

		return opcion;
	}

int pedirOpcionClientes()
	{
		int opcion = 10;

		do{
		cin >> opcion;
		}while((opcion < 0) || (opcion > 5));

		return opcion;
	}

int pedirOpcionGeneral()
	{
		int opcion = 10;

		do{
		cin >> opcion;
		}while((opcion < 0) || (opcion > 3));

		return opcion;
	}