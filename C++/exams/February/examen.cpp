//Apellidos: Sacanell Nogales	//Nombre: Alejandro	//DNI: 05435094 X
//Grupo: 28		//Laboratorio: 2

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

const int N=80;

typedef struct{
	int dni;
	double practica;
	double examen;
}tAlumnos;

typedef tAlumnos tArray[N];

typedef struct{
	tArray alumno;
	int contador;
}tLista;

		//Prototipos
bool archivoExiste();
void datosAlumnos(tLista &alumnos);
void mostrarAlumnos(tLista alumnos);
void comprobarMedia(int aux, tLista alumnos);
void ordenarLista(tLista &alumnos);

int main()
{
	tLista alumnos;
	alumnos.contador = 0;

	if(archivoExiste())
	{
		datosAlumnos(alumnos);
		cout << "***NO ORDENADA***" << endl << endl;
		mostrarAlumnos(alumnos);
		cout << endl << endl << "***ORDENADA***" << endl << endl;
		ordenarLista(alumnos);
		mostrarAlumnos(alumnos);
	}

	return 0;
}


		//Implementaciones

bool archivoExiste()
{
	ifstream notas;
	bool existe=false;

	notas.open("notas.txt");
	if(notas.is_open())
		{existe = true;}

	notas.close();
	return existe;
}

void datosAlumnos(tLista &alumnos)
{
	ifstream notas;
	int aux=0;

	notas.open("notas.txt");

		while(aux <= N && alumnos.alumno[aux].dni != -1)
		{
		notas >> alumnos.alumno[aux].dni;
		if(alumnos.alumno[aux].dni != -1)
			{
				notas >> alumnos.alumno[aux].practica;
				notas >> alumnos.alumno[aux].examen;
				alumnos.contador++;
				aux++;
			}
		}
	
	notas.close();
}

void mostrarAlumnos(tLista alumnos)
{
	for(int aux=0;aux < alumnos.contador;aux++)
	{
		cout << alumnos.alumno[aux].dni << " - ";
		cout << "(P: " << setprecision(3) << alumnos.alumno[aux].practica << " - ";
		cout << " E: " << setprecision(3) << alumnos.alumno[aux].examen << " - ";
		comprobarMedia(aux, alumnos);
		cout << endl;

	}
}

void comprobarMedia(int aux, tLista alumnos)
{
	double nota;


	nota = ((alumnos.alumno[aux].practica * 20) + (alumnos.alumno[aux].examen * 80));
	

	if(nota >= 500)
	{cout << "Aprobado)";}
	else
	{cout << "Suspenso)";}
}

void ordenarLista(tLista &alumnos)		//Utilizado insercion por intercambio, estable y natural;
{
	for(int i=1;i<alumnos.contador;i++)
	{
		int pos = i;
		while((pos > 0) && (alumnos.alumno[pos-1].examen < alumnos.alumno[pos].examen))
		{
			int tmpdni,tmpexamen, tmppractica;
			tmpdni = alumnos.alumno[pos].dni;
			tmpexamen = alumnos.alumno[pos].examen;
			tmppractica = alumnos.alumno[pos].practica;
			alumnos.alumno[pos] = alumnos.alumno[pos-1];
			alumnos.alumno[pos-1].dni = tmpdni;
			alumnos.alumno[pos-1].practica = tmppractica;
			alumnos.alumno[pos-1].examen = tmpexamen;
			pos--;
		}
	}
}