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
		if ((k != ' ') && (freq.find(k) == freq.end()) ) {
			freq[k] = 1;
		}
		else freq[k]++;
	}
	
	for (auto it:freq) cout << it.first << " " << it.second << endl; 
	return 0;
}
