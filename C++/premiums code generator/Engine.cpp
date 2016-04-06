#include "Engine.h"

using namespace std;

	void pauseProgram()
	{
		cin.sync();
		cin.get();
	}

	void finishProgram(bool &exit)
	{
		exit = true;
		cout << endl << "Press any key...";
		cin.sync();
		cin.get();
	}

	int toOneDigit(int number)
	{
		if(number >= 0 && number < 10)
		{
			return number;
		}
		else {
			int simplification = number;

			while(simplification>9)
			{
				simplification = selfSum(simplification);
			}
			return simplification;
		}
	}

	int selfSum(int number)
	{
		int simplification = 0;

		while(number > 0)
		{
			simplification = simplification + number%10;
			number = number/10;
		}
		return simplification;
	}
