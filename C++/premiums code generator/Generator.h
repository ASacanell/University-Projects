#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctime>
#include "Engine.h"
#include <fstream>
#include "Menu.h"
#define  INT_TO_CHAR(number) 0x30+number


		//Set the possibilities for the digits





		//Generates the digits for the code
	void generate5Digits(int minor, char code[]);

		//Shows the code in console
	void showCode(char code[]);

		//Exports the code into the archive codes.txt
	void logCode(char code[]);

		//Generates the verification digits for the code
	void generateVerification(int minor, char code[]);

		//Generates the code
	void generateCode(int number);

		//Request the number of codes that the user wants
	int numberRequest();

