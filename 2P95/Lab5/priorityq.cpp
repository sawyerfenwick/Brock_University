//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab5Exercise

#include <iostream>
#include "precord.h"

using namespace std;

int main(){
	PQueue q;										//Priority Queue Linked List
	int i = 1;
	int priority;
	string name;
	while(i != 0){
		menu();									//display menu
		cin >> i;									//get menu choice
		switch(i){
			case 1:
				cerr << "Enter Name: ";
				cin >> name;							//get name
				cerr << "Enter Priority: ";
				cin >> priority;						//get priority
				while(priority <= 0 or cin.fail()){				//in case negative integer or string is given
					cerr << "Error: Please Enter an Integer > 0: ";
					cin.clear();
					cin.ignore(256,'\n');
					cin >> priority;
				}
				q.enqueue(name, priority);					//add to list
				break;
			case 2:
				q.dequeue();							//remove from list
				break;
			case 3:
				q.display();							//display list
				break;
			case 0:
				cerr << "Goodbye.\n";
		}
	}
}//main
