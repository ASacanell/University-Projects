#ifndef GENERO_H
#define GENERO_H

typedef enum {cienciaFiccion, fantasia, comedia, terror, drama, comediaRomantica} tGenero;
//Funcion: Pide un genero al usuario y devuelve una variable de tipo tGenero con el género leído.
//Entrada: Recibe una variable tGenero sin inicializar
//Salida: Devuelve la variable tGenero inicializada
void leerGenero(tGenero &genero);
//Funcion: Dada una variable tGenero escribe el nombre del genero en la consola
//Entrada: Recibe una variable tGenero con los datos necesarios
void escribirGenero(tGenero genero);

#endif