#include "Menu.h"
#include <stdio.h>
#include <stdlib.h>

using namespace std;

	void showIntro()
	{
		cout << "**		******************************************		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		**    	   --Code  Generator--          **		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		******************************************		**" << endl;
	}

	void mainMenu()
	{
		cout << "**		******************************************		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		** 1 - Generate codes                   **		**" << endl;
		cout << "**		** 2 - Check codes                      **		**" << endl;
		cout << "**		** 0 - Exit                             **		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		******************************************		**" << endl;
	}

	int optionRequest(int minor, int major)
	{
		int option;		//the number of codes that you want

		while (((cout << "Option: " << endl) && !(cin >> option)) || (option < minor) || (option > major) || cin.peek() != '\n')
		{
			cout << "The option must be between " << minor << " and " << major << "!" << endl;
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			pauseProgram();
			system("cls");
			mainMenu();
		}

		return option;
	}

	void showOpt1()
	{
		system("cls");

		cout << "**		******************************************		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		**    	   --Code  Generator--          **		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		******************************************		**" << endl;
		cout << endl << endl;
	}

	void showOpt2()
	{
		system("cls");

		cout << "**		******************************************		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		**     	    --Code  Checker--           **		**" << endl;
		cout << "**		**                                      **		**" << endl;
		cout << "**		******************************************		**" << endl;
		cout << endl << endl;
	}
