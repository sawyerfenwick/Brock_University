//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab Exercise 7

#include <iostream>
#include "PriorityQueue.h"

using namespace std;

//displays menu
void menu(){
	cout<<"1.Enqueue\n2.Dequeue\n3.Peek\n4.Count\n0.Quit"<<endl;
}//menu

int main(){
	PriorityQueue<string, 10> pq;	//create a PQ with length 10
	int i = 1;
	long priority;
	string name;
	while(i != 0){
		menu();
		cin >> i;
		switch(i) {
			case 1:
				cerr << "Enter Name: ";	//add an element
				cin >> name;
				cerr << "Enter Priority: ";
				cin >> priority;
				while(priority <= 0 or cin.fail()){
					cerr << "Error: Please Enter an Integer > 0: ";
					cin.clear();
					cin.ignore(256, '\n');
					cin >> priority;
				}
				pq.enqueue(name, priority);
				break;
			case 2:
				if(pq.getCount() == 0){	//remove top element
					cerr << "Nothing to remove!\n";
				}
				else{
					cerr << "Removed: " << pq.dequeue() << endl;
				}
				break;
			case 3:
				if(pq.getCount() == 0){	//display top element 
					cerr << "Nothing to see here!\n";
				}
				else{
					cerr << "Top Element: " << pq.peek() << endl;
				}
				break;
			case 4:
				cerr << "Count: " << pq.getCount() << endl;	//display count
				break;
			case 0:
				cerr << "Goodbye\n";
		}
	}
}//main
