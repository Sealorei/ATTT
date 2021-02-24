#include <bits/stdc++.h>
using namespace std;

int main(){
	map<char, int> freq;
	char str1[1000];
	string str;
	char k;

	ifstream f("ciphertext.txt");
	if(f) {
		ostringstream ss;
		ss << f.rdbuf();
		str = ss.str();
	}
	
	for (int i = 0; str[i] != '\0'; i++) {
		k = str[i];
		if (k == 'r') str[i] = 'e';
		else if (k == 'b') str[i] = 't';
		else if (k == 'm') str[i] = 'a';
		else if (k == 'k') str[i] = 'n';
		else if (k == 'j') str[i] = 'o';
		else if (k == 'w') str[i] = 'i';
		else if (k == 'i') str[i] = 's';
		else if (k == 't') str[i] = 'y';
		else if (k == 'x') str[i] = 'f';
		else if (k == 'u') str[i] = 'r';
		else if (k == 'n') str[i] = 'u';
		else if (k == 'q') str[i] = 'k';
		else if (k == 'p') str[i] = 'h';
		else if (k == 'v') str[i] = 'c';
		else if (k == 'l') str[i] = 'b';
		else if (k == 's') str[i] = 'p';
		else if (k == 'h') str[i] = 'l';
		else if (k == 'y') str[i] = 'm';
		else if (k == 'o') str[i] = 'g';
		else if (k == 'a') str[i] = 'x';
		else if (k == 'c') str[i] = 'w';
		else if (k == 'e') str[i] = 'v';
		else if (k == 'f') str[i] = 'q';
	}
	
	ofstream out("mystery.txt");
	out << str;
	out.close();
	
	return 0;
}
