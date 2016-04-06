#include "Check.h"

using namespace std;

	void checkCode()
	{
		char code[15];

		showOpt2();
		codeInput(code);
		checkVerification(code);
	}

	void codeInput(char code[])
	{
		cout << "Insert the code that you want to check: " << endl;
		cin >> code;
	}

	void checkVerification(char code[])
	{
		bool error1 = false;
		bool error2 = false;
		bool error3 = false;
		bool error4 = false;
		bool error5 = false;

		error1 = checkNumberOne(code);
		error2 = checkNumberTwo(code);
		error3 = checkNumberThree(code);
		error4 = checkNumberFour(code);
		error5 = checkNumberFive(code);

		if(!error1 && !error2 && !error3 && !error4 && !error5)
		{
			cout << "The code is valid!!";
		}
		else
		{
			cout << "Error, this code is not valid!!";
		}
		pauseProgram();
		system("cls");
	}

	bool checkNumberOne(char code[])
	{
		char number = code[10];
		char verify = (INT_TO_CHAR(toOneDigit(code[4]*code[3]*code[8]-code[9])));

		if(number == verify)
			return false;
		else
			return true;
	}

	bool checkNumberTwo(char code[])
	{
		char number = code[11];
		char verify = (INT_TO_CHAR(toOneDigit(code[9]+150-code[3])));

		if(number == verify)
			return false;
		else
			return true;
	}

	bool checkNumberThree(char code[])
	{
		char number = code[12];
		char verify = (INT_TO_CHAR(toOneDigit(code[8]*code[8]-17)));

		if(number == verify)
			return false;
		else
			return true;
	}

	bool checkNumberFour(char code[])
	{
		char number = code[13];
		char verify = '1';

		if(number == verify)
			return false;
		else
			return true;
	}

	bool checkNumberFive(char code[])
	{
		char number = code[14];
		char verify = '7';

		if(number == verify)
			return false;
		else
			return true;
	}
