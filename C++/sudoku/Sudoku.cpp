//Apellidos: Sacanell Nogales	//Nombre: Alejandro	//DNI: 05435094 X
//Apellidos: Peinado Delgado	//Nombre: Guillermo	//DNI: 76088775 Z
//Grupo: 28		//Laboratorio: 2


   /********/	
  /*Sudoku*/
 /********/
/*
	Este código es un sudoku, empezará solicitando los nombres de los archivos de entrada de los cuales 
	cargará las casillas iniciales y la solución.
	Este sudoku tiene varias opciones, que vienen descritas en la función main, tales como saber que números 
	son posibles soluciones o que se rellenen automáticamente algunas casillas.
*/						


#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstdlib>
#include <time.h>

using namespace std;

	const int tamFILAS=9;
	const int tamCOLUMNAS=9;
	const int tamPOSIBLES=10;

	typedef int tTabla[tamFILAS][tamCOLUMNAS];
	typedef bool tBool[tamFILAS][tamCOLUMNAS];
	typedef int tPosibles[tamPOSIBLES];

	typedef struct{
		tTabla sudoku;
		tBool booles;
		int contador;
	}tTablero;


	
		//Prototipos
	
	//Muestra el menú por pantalla.
	//Entrada:
	//Salida:	Por pantalla
void mostrarMenu();		
	
	//Dibuja la línea horizontal del sudoku.
	//Entrada:
	//Salida:
void lineaHorizontal();

	//Lee los dígitos del array "tablero.sudoku" y dibuja el sudoku en la pantalla.
	//Entrada:	Datos del array tablero.sudoku.
	//Salida:	Salida por pantalla.
void escribirSudoku(const tTablero tablero);	

	//Solicita la opción a escoger del menú, y la devuelve con return.
	//Entrada:
	//Salida:	Devuelve la opción escogida.
char pedirOpcion();		

	//Opcion 0, sale del programa sin haber completado el sudoku.
	//Entrada:	Estado inicial del booleano salir(false).
	//Salida:	Booleano salir, para finalizar el bucle en caso true.
void salirPrograma(bool &salir);	

	//Inicializa arrays "sudoku" e "inicio", leyendo del archivo, inicializa también el contador, y si hay error en el archivo devuelve error.
	//Entrada:	arrays sudoku, booles, contador (todos de tTablero) y el nombre del archivo del cual leer el sudoku inicial y el booleano error para saber si hay fallo en la inicialización.
	//Salida:	devuelve ya modificados con los nuevos datos los arrays sudoku y booles, modificado el contador y el booleano por si ha existido el fallo de inicialización.
void leerSudoku(tTablero &tablero, string archivo, bool &error);	

	//Modifica los nombres de los archivos a los que se accede para inicializar el sudoku.
	//Entrada:	Cadenas con los nombres de los archivos y cadena nombre.
	//Salida:	Cadenas modificadas, o por defecto.
void solicitarNombres(string &sudoku, string &solucion, string &nombre);	

	//Inicializa el array "sudokuSolucion", con los datos del archivo solucion, y si hay error al abrir el archivo devuelve error.
	//Entrada:	array sudokuSolucion, cadena solucion para saber de que archivo extrae la solución y booleano error, por un posible fallo de inicialización.
	//Salida:	array sudokuSolucion con la solución cargada, y booleano error por si ha existido el fallo de inicialización.
void leerSolucion(tTabla sudokuSolucion, string solucion, bool &error);	

	//Elimina todos los valores del sudoku que tenian cero en el array booleano "inicio"
	//Entrada:	tTablero
	//Salida:	arrays sudoku y booles modificados a su estado inicial, al igual que el contador
void reiniciarTablero(tTablero &tablero);		

	//Comprueba los valores que pueden colocarse en una posición según las reglas del sudoku, y te las muestra por consola. Si el valor estaba de inicio (inicio[X][Y]!=0) da valor no modificable.
	//Entrada:	arrays sudoku y booles	
	//Salida:	array aux por pantalla, indicando los valores erroneos pertenecientes al sudoku.
void posiblesValores(tTablero tablero);		

	//Inserta un valor en una columna solo si este valor comprueba los datos posibles.
	//Entrada:	array sudoku y el contador
	//Salida:	array sudoku con el nuevo valor, y el contador con su correspondiente incremento.
void colocarValor(tTablero &tablero);	

	//Solicita un valor, hasta que este esté entre 1 y 9.
	//Entrada:	
	//Salida:	Entero que marcará la fila, columna o valor
int solicitarValor();	

	//Elimina una casilla solicitada siempre y cuando el valor no estuviese de inicio, y minimiza el contador si lo ha borrado.
	//Entrada:	tTablero
	//Salida:	Array sudoku modificado, y el contador con su correspondiente decremento.
void borrarValor(tTablero &tablero);	

	//Comparando con el array sudoku con el array solución, muestra cuales son incorrectos por consola.
	//Entrada:	arrays sudoku y sudokuSolucion
	//Salida:	muestra por pantalla los valores que no son correctos.
void valoresIncorrectos(tTablero tablero, tTabla sudokuSolucion);		

	//Dibuja los números de las columnas del sudoku
	//Entrada:
	//Salida:	Por pantalla
void numerosHorizontal();		

	//Comprueba casillas donde solo haya una opción posible y las rellena, además incrementa el contador.
	//Entrada:	array sudoku y contador
	//Salida:	se modifica el array sudoku añadiendo los nuevos valores añadidos, y el contador se incrementa el número de nuevas casillas.
void casillasSimples(tTablero &tablero);	

	//Varios if's que determinan en que región esta la casilla correspondiente.
	//Entrada:	enteros fila y columna, para determinar una i y una j correspondientes a la región definida.
	//Salida:	enteros i y j, para calcular los posibles valores de una región.
void conocerRegion(int fila, int columna, int &i, int &j);

	//Pausa el programa hasta que se pulsa intro.
	//Entrada:
	//Salida:
void pausarPrograma();	

	//Comprueba que un valor introducido no se repita en la misma fila.
	//Entrada:	el entero fila, el array sudoku y el array posibleFila.
	//Salida:	array posibleFila con los posibles valores para una casilla
void comprobarFila(int fila, tTablero tablero, tPosibles posibleFila);		

	//Comprueba que un valor introducido no se repita en la misma columna.
	//Entrada:	el entero columna, el array sudoku y el array posibleColumna.
	//Salida:	array posibleColumna con los posibles valores para una casilla
void comprobarColumna(int columna, tTablero tablero, tPosibles posibleColumna);	

	//Comprueba que un valor introducido no se repita en la misma región.
	//Entrada:	los enteros fila y columna, el array sudoku y el array total.
	//Salida:	array total con los posibles valores para una casilla
void comprobarRegion(int fila, int columna, tTablero tablero, tPosibles total);	

	//Inicializa el array a {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	//Entrada:	array final
	//Salida:	array final inicializado de 0, a 9.
void inicializaFinal(tPosibles final);		

	//Inicializa el array a {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	//Entrada:	array posible
	//Salida:	array posible inicializado todo a 0.
void inicializaPosible(tPosibles posible);	

	//Comprueba que posibilidades hay en una casilla determinada.
	//Entrada:
	//Salida:
void comprobarPosibles(int fila, int columna, tTablero tablero, tPosibles total);

	//Inicializa la tabla de bool a false.
	//Entrada:	array booles
	//Salida:	Inicializa el array booles todo a false.
void inicializaBool(tTablero &tablero);

	//Inicializa el sudoku a 0.
	//Entrada:	array sudoku
	//Salida:	array sudoku todo a 0.
void inicializaSudoku(tTablero &tablero);

	//Comprueba si el sudoku que estamos haciendo coincide con la solución y finaliza el programa
	//Entrada:	arrays sudoku y sudokuSolucion, y booleano finalizado
	//Salida:	bool finalizado, para saber si el sudoku esta correctamente finalizado.
void comprobarFinalizado(tTablero tablero, tTabla sudokuSolucion, bool &finalizado);

	//Solicita los datos de la casilla que se quiere modificar, repitiendolo en un bucle hasta que sean correctos.
	//Entrada:	enteros fila y columna.
	//Salida:	fila y columna ya comprobados.
void solicitaCasilla(int &fila, int &columna);

	//Incrementa el contador de los posibles valores que tiene una casilla
	//Entrada:	array final
	//Salida:	entero con el valor de los valores posibles
int incrementaContador(tPosibles final);

	//Introduce en el array final los posibles valores de una casilla
	//Entrada:	arrays posible y final
	//Salida:	array final con los posibles valores, listo para procesarlo.
void asociarCasillasSimples(tPosibles posible, tPosibles final);

	//Si el contador de posibles valores en esa casilla es 1, la rellena automáticamente
	//Entrada:	enteros cambios, fila y columna, y arrays final y sudoku.
	//Salida:	el entero cambios, con el número de casillas rellenadas, y el array sudoku con los nuevos valores también incorporados.
void rellenarSimples(int &cambios, int fila, int columna, tPosibles final, tTablero &tablero);

	//Calcula las horas, minutos y segundos que duró la partida.
	//Entrada:	Tiempo, con la cantidad de segundos que duró la partida.
	//Salida:	Por pantalla.
void duracionTiempo(int tiempo);





int main()
{

	tTablero tablero;
	bool salir = false, finalizado = false, error = false;
	string archivo = "sudoku1.txt", solucion1 = "solsdk1.txt", nombre;
	int fila=0, columna = 0;
	tablero.contador = 0;
	int tiempo;
	clock_t t_ini, t_fin;

	inicializaSudoku(tablero);
	inicializaBool(tablero);
	tTabla sudokuSolucion = {0};

	solicitarNombres(archivo, solucion1, nombre);							
	leerSudoku(tablero, archivo, error);									
	leerSolucion(sudokuSolucion, solucion1, error);		
	if(error == true)
	{
		cout << "Error en la inicializacion del Sudoku"<< endl;
		pausarPrograma();
	}else
	{
		t_ini = clock();
		do{
			escribirSudoku(tablero);
			mostrarMenu();
	switch(pedirOpcion())
			{
				case '0':	//Salir												
				{
					salirPrograma(salir);
				break;}
				case '1':	//Ver posibles valores de casilla					
				{
					posiblesValores(tablero);	
				break;}
				case '2':	//Colocar un valor en una casilla					
				{
					colocarValor(tablero);	//Falta comprobar si es válido
				break;}
				case '3':	//Borrar el valor de una casilla				
				{
					borrarValor(tablero);	
				break;}
				case '4':	//Mostrar valores incorrectos					
				{
					valoresIncorrectos(tablero, sudokuSolucion);		
				break;}
				case '5':	//Reiniciar tablero							
				{
					reiniciarTablero(tablero);
				break;}
				case '6':	//Completar casillas simples					
				{
					casillasSimples(tablero);
				break;}
			}
			comprobarFinalizado(tablero, sudokuSolucion, finalizado);
		}while(!salir && !finalizado);
		if(finalizado)
		{
		t_fin = clock();
		tiempo =(t_fin - t_ini)/1000;
		cout << "!!ENHORABUENA " << nombre << "!!" << endl << "Has completado el sudoku correctamente." << endl;
		duracionTiempo(tiempo);
		pausarPrograma();
		}
	}
	return 0;
}




		//Implementaciones

	void lineaHorizontal()
	{
		cout << setw(5);
		for(int i=1;i<32;i++)
			cout << "-";
		cout << endl;
	}

	void escribirSudoku(tTablero tablero)
	{
		system("cls");		
		int k=0, l=0;


		numerosHorizontal();
		for(int f=0;f<tamFILAS;f++)
		{
			if(l%3==0)		//Cada 3 filas, linea horizontal
			{
				lineaHorizontal();
			}
			cout << setw(2) << f+1;	
			for(int c=0;c<tamCOLUMNAS;c++)
			{	
				if(k%3==0)		//Cada 3 columnas, linea vertical
				{
					cout << "  | ";
				}
				if(tablero.sudoku[f][c]==0)
					{cout << "  ";}
				else
					{cout << " " << tablero.sudoku[f][c];}
				k++;
				
			}
			cout << "  | " << endl;
			l++;
		}
		lineaHorizontal();
	}

	void mostrarMenu()
	{
		cout << "0.- SALIR" << endl;
		cout << "1.- Ver posibles valores de casilla" << endl;
		cout << "2.- Colocar un valor en una casilla" << endl;
		cout << "3.- Borrar el valor de una casilla" << endl;
		cout << "4.- Mostrar valores incorrectos" << endl;
		cout << "5.- Reiniciar tablero" << endl;
		cout << "6.- Completar casillas simples" << endl;
	}

	void leerSudoku(tTablero &tablero, string archivo, bool &error)
{
	ifstream inicial;
	int fila = 0, columna = 0, valor;

	inicial.open(archivo);
	if(inicial.is_open())		//Si el archivo se abre correctamente(si existe)
	{
		inicial >> fila;
		while(fila != -1)
		{
			inicial >> columna;
			inicial >> valor;
			columna--;
			fila--;
			if(fila > 8 || fila < 0 || columna > 8 || columna < 0 || valor < 1 || valor > 9)		//Valor incorrecto
				{error=true;}
			else
			{
				tablero.sudoku[fila][columna] = valor;		//Array sudoku con el valor inicial
				tablero.booles[fila][columna] = true;		//Array booleano en true
				if(tablero.booles[fila][columna])
					tablero.contador++;						//Aumenta contador de casillas rellenas
				inicial >> fila;
			}
		}
	inicial.close();
	}
	else
		error = true;
		

}

	char pedirOpcion()
	{
		char opcion = 9;

		do{
		cout << "Opcion: ";
		cin >> opcion;
		cout << endl;
		}while((opcion < 0) && (opcion > 6));

		return opcion;
	}

	void salirPrograma(bool &salir)
	{
		salir = true;
		cout << "Pulse Intro para terminar";
		pausarPrograma();
	}

	void solicitarNombres(string &archivo, string &solucion, string &nombre)
	{
		cout << "Introduzca su nombre: ";
		{getline(cin, nombre);}
		cout << endl;
		
		cout << "Nombre del sudoku (Intro -> archivo por defecto 'sudoku1.txt'): ";
		{getline(cin, archivo);}
		if(archivo == "\0")		//Por defecto
			archivo = "sudoku1.txt";
		cout << endl;
		cin.sync();

		cout << "Nombre de la solucion (Intro -> archivo por defecto 'solsdk1.txt'): ";
		{getline(cin, solucion);}
		if(solucion == "\0")	//Por defecto
			solucion = "solsdk1.txt";
		cout << endl;
		cin.sync();
	}

	void leerSolucion(tTabla sudokuSolucion, string solucion, bool &error)
	{
		int fila=0, columna=0;
		int aux;
		ifstream archivoSolucion;
		
		archivoSolucion.open(solucion);
		if(archivoSolucion.is_open())		//Si el archivo se abre correctamente
		{
			do
			{
				for(int columna=0;columna<tamCOLUMNAS;columna++)
				{
					archivoSolucion >> aux;
					sudokuSolucion[fila][columna] = aux;
				}
				fila++;
			}while((fila<tamFILAS));

		archivoSolucion.close();
		}else
		{
			error=true;
		}
	}

	void reiniciarTablero(tTablero &tablero)
	{
		int fila=0, columna=0;
		
		tablero.contador = 0;
		for(fila=0;fila<tamFILAS;fila++)
		{	for(columna=0;columna<tamCOLUMNAS;columna++)
			{
				if(!tablero.booles[fila][columna])		//Si array bool es false, el sudoku array cambia a 0.
					{tablero.sudoku[fila][columna] = 0;}
				else{tablero.contador++;}
			}
		}
	}

	void posiblesValores(tTablero tablero)
	{
		int fila, columna;

		tPosibles total = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		solicitaCasilla(fila, columna);
		if(tablero.booles[fila][columna])		//Si la casilla tenia valor inicial
		{
			cout << "Casilla no vacia";
		}
		else
		{

			comprobarPosibles(fila, columna, tablero, total);

			for(int k=0;k<tamPOSIBLES;k++)		//Escribe los valores posibles de uan casilla
			{
				if(total[k] != 0)
				{
					cout << k << " ";
				}
			}

		}

		pausarPrograma();
	}

	void colocarValor(tTablero &tablero)
	{
		int fila, columna, valor;
		tPosibles posible = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		bool repetirValor = false;
		
		solicitaCasilla(fila, columna);
	
		if(tablero.sudoku[fila][columna] == 0)
		{
			cout << "Introduce el valor [1..9]: ";
			do{
				if(repetirValor)
				{cout << "Introduce de nuevo el valor: ";}
				valor = solicitarValor();
				repetirValor=true;
			}while(valor < 1 || valor > 9);
			comprobarPosibles(fila, columna, tablero, posible);

			if(posible[valor]==valor)		//Si el valor es un valor posible
			{
				tablero.sudoku[fila][columna] = valor;
				tablero.contador++;
			}else if(posible[valor]!=valor)		//Si el valor no es un valor posible
			{
				cout << "Valor repetido";
				pausarPrograma();
			}
			
		}else if(tablero.sudoku[fila][columna] != 0)
		{
			cout << "Casilla no vacia";
			pausarPrograma();
		}
	}

	int solicitarValor()
	{
		int valor;

		cin >> valor;

		return valor;
	}

	void borrarValor(tTablero &tablero)
	{
		int fila, columna;

		solicitaCasilla(fila, columna);

		if(tablero.booles[fila][columna])		//Si era casilla inicial
		{
			cout << "NO MODIFICABLE";
			pausarPrograma();
		}		
		else
		{
			if(tablero.sudoku[fila][columna]==0)	//Si la casilla estaba vacía
			{
				cout <<"Casilla vacia";
				pausarPrograma();
			}else
			{
				tablero.sudoku[fila][columna]=0;
				tablero.contador--;
			}
		}
	}

	void valoresIncorrectos(tTablero tablero, tTabla sudokuSolucion)
	{		
		tBool aux = {0};
		bool error=false;

		for(int fila=0;fila<tamFILAS;fila++)
		{
			for(int columna=0;columna<tamCOLUMNAS;columna++)
			{
				if((tablero.sudoku[fila][columna] != sudokuSolucion[fila][columna]) && (tablero.sudoku[fila][columna] != 0))
				{
					aux[fila][columna]= true;
					error = true;
				}
			}
		}
		if(error)
		{
			for(int fila=0;fila<tamFILAS;fila++)
			{
				for(int columna=0;columna<tamCOLUMNAS;columna++)
				{
					if(aux[fila][columna])
					{
						cout << "[" << fila+1 << "][" << columna+1 << "] = " << tablero.sudoku[fila][columna] << endl;
					}
				}
			}
		}
		else
		{
			cout << "No hay errores";
		}
	pausarPrograma();
	}			

	void numerosHorizontal()
	{
		int aux=1;

		cout << setw(8) << aux;
		for(int aux=2;aux<10;aux++)
		{
			if(aux==4 || aux == 7)
			{
				cout << setw(4) << " ";
			}
		cout << setw(2) <<aux;
		}
		cout << endl;
	}

	void conocerRegion(int fila, int columna, int &i, int &j)
	{
	
		if(fila >= 0 && fila <=2 )
		{
			if(columna >= 0 && columna <= 2)
			{i=0; j=0;}
			else if(columna >= 3 && columna <= 5)
			{i=0; j=3;}
			else if(columna >= 6 && columna <= 8)
			{i=0; j=6;}
		}
		else if(fila >= 3 && fila <=5 )
		{
			if(columna >= 0 && columna <= 2)
			{i=3; j=0;}
			else if(columna >= 3 && columna <= 5)
			{i=3; j=3;}
			else if(columna >= 6 && columna <= 8)
			{i=3; j=6;}
		}
		else if(fila >= 6 && fila <=8 )
		{
			if(columna >= 0 && columna <= 2)
			{i=6; j=0;}
			else if(columna >= 3 && columna <= 5)
			{i=6; j=3;}
			else if(columna >= 6 && columna <= 8)
			{i=6; j=6;}
		}
	}

	void pausarPrograma()
	{
		cin.sync();
		cin.get();
		cin.sync();
	}

	void comprobarFila(int fila, tTablero tablero, tPosibles total)
	{
		int aux;

		for(int k=0;k<tamCOLUMNAS;k++)
		{
			aux = tablero.sudoku[fila][k];
			total[aux] = 0;
		}
	}

	void comprobarColumna(int columna, tTablero tablero, tPosibles total)
	{
		int aux;

		for(int k=0;k<tamFILAS;k++)
		{
			aux = tablero.sudoku[k][columna];
			total[aux] = 0;
		}
	}

	void comprobarRegion(int fila, int columna, tTablero tablero, tPosibles total)
	{
		int i,j, aux=0,k,l;
		
		conocerRegion(fila, columna, i, j);

		k=i+2;
		l=j+2;

		while(i<=k)
		{
			while(j<=l)
			{
				aux = tablero.sudoku[i][j];
				total[aux] = 0;
				j++;
			}
		i++;
		j=l-2;
		}
	
	
	}

	void casillasSimples(tTablero &tablero)
	{
		tPosibles posible = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		tPosibles final={0};
		int cambios=0;

		for(int fila=0;fila<tamFILAS;fila++)
		{
			for(int columna=0;columna<tamCOLUMNAS;columna++)
			{
				comprobarPosibles(fila, columna, tablero, posible);

				if(tablero.sudoku[fila][columna]==0)
				{
					asociarCasillasSimples(posible, final);
					rellenarSimples(cambios, fila, columna, final, tablero);
				}
			inicializaPosible(posible);
			inicializaFinal(final);
			}

		}
		escribirSudoku(tablero);
		cout << "Se han rellenado "<< cambios << " celdas" << endl;
		pausarPrograma();
	}

	void inicializaFinal(tPosibles final)
	{
		for(int fila=0;fila<tamPOSIBLES;fila++)
			final[fila] = 0;
	}

	void inicializaPosible(tPosibles posible)
	{
		for(int fila=0;fila<tamPOSIBLES;fila++)
			posible[fila] = fila;
	}

	void comprobarPosibles(int fila, int columna, tTablero tablero, tPosibles total)
	{
		comprobarFila(fila, tablero, total);
		comprobarColumna(columna, tablero, total);
		comprobarRegion(fila, columna, tablero, total);
	}

	void inicializaBool(tTablero &tablero)
	{
		for(int fila=0;fila<tamFILAS;fila++)
		{
			for(int columna=0;columna<tamCOLUMNAS;columna++)
			{
				tablero.booles[fila][columna] = false;
			}
		}

	}

	void inicializaSudoku(tTablero &tablero)
	{
		for(int fila=0;fila<tamFILAS;fila++)
		{
			for(int columna=0;columna<tamCOLUMNAS;columna++)
			{
				tablero.sudoku[fila][columna] = 0;
			}
		}
	}

	void comprobarFinalizado(tTablero tablero, tTabla sudokuSolucion, bool &finalizado)
	{
		int incorrectos = 0;
		for(int fila=0;fila<tamFILAS;fila++)
		{
			for(int columna=0;columna<tamCOLUMNAS;columna++)
			{
				if((tablero.sudoku[fila][columna] != sudokuSolucion[fila][columna]))	//Si no es igual sudoku y solucion
				{
					incorrectos++;
				}
			}
		}
		if(incorrectos == 0)		//Si no hay ningun valor incorrecto
			finalizado = true;
	}

	void solicitaCasilla(int &fila, int &columna)
	{
		bool repetir = false;
		
		cout << "Introduce fila y columna [1..9]: ";
		do{
			if(repetir)
			{cout << "Introduce de nuevo los valores: ";}
			fila = solicitarValor();
			columna = solicitarValor();
			fila = --fila;
			columna = --columna;
			repetir = true;
		}while(fila > 8 || fila < 0 || columna > 8 || columna < 0);
	}

	int incrementaContador(tPosibles final)
	{
		int contador = 0;
		for(int k=0;k<tamPOSIBLES;k++)
		{
			if(final[k] != 0)
			{
				contador++;
			}
		}
	return contador;
	}

	void asociarCasillasSimples(tPosibles posible, tPosibles final)
	{
		for(int k=0;k<tamPOSIBLES;k++)
		{
			if(posible[k] != 0)
			{
				final[k]=posible[k];
			}
		}
	}

	void rellenarSimples(int &cambios, int fila, int columna, tPosibles final, tTablero &tablero)
	{
		if(incrementaContador(final)==1)
		{
			for(int k=0;k<tamPOSIBLES;k++)
			{
				if(final[k]!=0)
				{
					tablero.sudoku[fila][columna]=final[k];
					cambios++;
					tablero.contador++;
				}
			}
		}
	}

	void duracionTiempo(int tiempo)
	{
		int hora, segundo, minuto;
		int resto;
		
		
		hora = tiempo / 3600;
		resto = tiempo % 3600;
		minuto = resto / 60;
		segundo = resto % 60;


		cout << "Duracion de la partida: " << hora << ":" << minuto << ":" << segundo;
	}