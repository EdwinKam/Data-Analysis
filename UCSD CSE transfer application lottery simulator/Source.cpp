#include <iostream>
#include <ctime>
#include <random>
using namespace std;

int main() {
	srand(time(0));
	double count;

	for (int i = 1; i < 100; i++) {
		int a = rand() % 133 + 1;
		cout << i << "th try" << endl;
		cout << "first draw number " << a << endl;
		if (a < 56)
		{
			cout << "+++++++++++++++congrat u got in!!\n";
			cout << endl;
			count++;
		}
		else {
			int b = rand() % 133 + 1;
			cout << "second draw number " << b << endl;
			if (b < 56)
			{
				count++;
				cout << "+++++++++++++++congrat u got in!!\n";
				cout << endl;
			}
			else {
				cout << "+++++++++++++++ nice you fucked up!!\n\n";
			}

		}
	}cout << " you got in " << count << " times";




}