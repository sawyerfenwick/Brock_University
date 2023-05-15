//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab2Exercise

#include <iostream>
using namespace std;

int main(){

//int stdNum[7] = {6,0,0,5,0,1,1};

int stdNum = 6005011;
int input[7]; 
int stdArr[7];
int i = 0;

while(stdNum!=0){
	input[i] = stdNum%10; //put the number into the array
	stdNum=stdNum/10; //move to next number
	i++;
}

for(int j = 0; j < i; j++) 
	stdArr[j] = input[i-j-1]; //numbers were added in reverse, reverse them back

for(int x : stdArr) 
	if(x%2 == 0)//if x mod 2 is 0, even
		cout<<x/2;
	else
		if(x*2 >= 10) //if product > 10
			cout<<x*2 - 10; //remove the tens column, show only the ones column
		else
			cout<<x*2;
cout<<"\n";
	
double monkeys; //monkeys in
double million = 1000000; //for decimal presicion of monkeys

cout<<"How many monkeys do I have? "; cin>>monkeys;
cout<<"I have "<<(monkeys>=million?monkeys/million:monkeys)<<(monkeys!=1?" monkeys":" monkey")<<"\n"; //ternary operators instead of if statement

//dangling else, added brackets to fix.
int choice1,choice2;
cout<<"Do you have room for tiramisu? (1:yes, 0:no)"; cin>>choice1;
cout<<"Do you actually like tiramisu? (1:yes, 0:no)"; cin>>choice2;
if (choice1){
	if (choice2){
		cout<<"Ready, willing, and able to enjoy tiramisu!\n";
	}
}
else{
	cout<<"It doesn't matter if I like it or not, I don't have room for dessert!\n";
}
}//main
