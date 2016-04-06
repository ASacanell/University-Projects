#include <iostream>
#include "Menu.h"
#include "Generator.h"
#include "Check.h"
#include "Engine.h"
using namespace std;

	int main()
	{
		bool exit = false;

		showIntro();
		pauseProgram();
		system("cls");

		do{
			mainMenu();

			switch(optionRequest(0,2))
			{
			case 0:{
				finishProgram(exit);
				break;}
			case 1:{
				generateCode(numberRequest());
				break;}
			case 2:{
				checkCode();
				break;}
			}
		}while(!exit);

		return 0;
	}
