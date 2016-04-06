#include <stdio.h>
#include <stdlib.h>
//#include <cctype>
//#include <iomanip>
#include <iostream>
#include "Menu.h"
#include "Engine.h"
#define  INT_TO_CHAR(number) 0x30+number





		/*PROTOTYPES*/

		//Checks if a code is valid
	void checkCode();

		//
	void codeInput(char code[]);

		//
	void checkVerification(char code[]);

		//
	bool checkNumberOne(char code[]);

		//
	bool checkNumberTwo(char code[]);

		//
	bool checkNumberThree(char code[]);

		//
	bool checkNumberFour(char code[]);

		//
	bool checkNumberFive(char code[]);
