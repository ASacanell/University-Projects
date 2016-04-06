#include "Generator.h"

using namespace std;


		char cadena[36]={'1','2','3','4','5','6','7','8','9','0',
				'A','B','C','D','E','F','G','H','I','J','K','L',
				'M','N','O','P','Q','R','S','T','U','V','W','X',
				'Y','Z'};

void generate5Digits(int minor, char code[])
	{
		char digit;			//the digit inicialization;

		for(int min=minor;min<minor+3;min++)
		{
			digit = cadena[rand() % 35]; //numero aleatorio entre 0 y 35
			code[min] = digit;
		}

		for(int min=minor+3;min<minor+5;min++)
		{
			digit = cadena[rand()%9];
			code[min] = digit;
		}

	}

	void showCode(char code[])
	{
		for(int times = 0; times < LENGTH; times++)
		{
			cout << code[times];
			if(times == 4 || times == 9)
			{
				cout << "-";
			}
		}
		cout << endl;
	}

	void logCode(char code[])
	{
		ofstream archive; 	//the log variable

		archive.open("codes.txt",ofstream::app);	//Open the archive and writes and the end of it content
		if(archive.is_open())						//Verification that the archive is well opened
		{
			for(int times = 0; times < LENGTH; times++)
			{
				archive << code[times];
			}
			archive << endl;
		}
		archive.close();
	}

	void generateVerification(int minor, char code[])
	{
		code[minor] = INT_TO_CHAR(toOneDigit(code[4]*code[3]*code[8]-code[9]));
		code[minor+1] = INT_TO_CHAR(toOneDigit(code[9]+150-code[3]));
		code[minor+2] = INT_TO_CHAR(toOneDigit(code[8]*code[8]-17));
		code[minor+3] = '1';
		code[minor+4] = '7';
	}

	void generateCode(int number)
	{
		srand(time(NULL));	//makes the random selection completely aleatory
		char code[LENGTH];	//the code array

		for(int amount = 0;amount < number; amount++)		//Loop for making the number of codes that you want
		{
			generate5Digits(0,code);			//Generates the 0-5 digits
			generate5Digits(5,code);			//Generates the 5-10 digits
			generateVerification(10,code);		//Generates the 10-15 verification digits
			showCode(code);						//Shows the code
			logCode(code);						//Export the codes into a txt
		}
		pauseProgram();
		system("cls");
	}

	int numberRequest()
	{
		int number;		//the number of codes that you want

		showOpt1();
		while (((cout << "How many codes do you want to get?" << endl << "Press 0 to exit the program" << endl) && !(cin >> number)) || (number < 0) || cin.peek() != '\n')
		{
			cout << "The number of codes must be whole positive number!" << endl;
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			pauseProgram();
			system("cls");
		}

		return number;
	}
