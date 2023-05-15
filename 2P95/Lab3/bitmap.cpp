//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab3Exercise

#include <iostream>
#include <cmath>
#include "bitmap.h"

using namespace std;

double* arr = 0;
double* xArr = 0;
double* yArr = 0;
int g; //graduations
int opt; //user option

int main(){
	int input = 1;
	while(input != 0){		//loop until user quits
		menu();		//print menu
		cin >> input;
		switch(input){
			case 1:
				functionOne();
				break;
			case 2:
				functionTwo();
				break;
			case 3:
				functionThree();
				break;
			case 4:
				functionFour();
				break;
			case 0:
				break;
			default:
				cerr<<"Enter an appropriate response please\n";	//incase of tomfoolery
		}
	}
}//main

void menu(){		//prints the menu
	cerr<<"Select your function:\n1. sin(x)cos(y)\n2. sin(x)+cos^2(y/2)-x/y\n3. 1/2 sin(x) + 1/2 cos(y)\n4. 1/2 sin(x) + xcos(3y)\n0. Quit\n";
}//menu

void grad(){		//asks for number of graduations
	cerr<<"Number of graduations per axis: ";
}//grad 

void bitval(){		//asks for bitmap or values 
	cerr<<"(0) Bitmap or (1) Values? ";
}//bitval

void createArrays(){	//creates 3 arrays, fills x and y arrays based on number of graduations 'g'
	if(arr != 0)
		delete [] arr; 	//garbage collection
		
	if(xArr != 0)
		delete [] xArr; 	//garbage collection
	
	if(yArr != 0)
		delete [] yArr;	//garbage collection

	arr = new double [g*g];	//create arrays
	xArr = new double [g];
	yArr = new double [g];
	
	for(int i = 0; i < g; i++){	//fill x array from xMin to xMax with 'g' intermediate steps
		if(i == 0){
			xArr[i] = xMin;
		}
		else{
			xArr[i] = xArr[i-1] + (10.0 / (g-1));   
		}
	}
	
	for(int i = 0; i < g; i++){	//fill y array from yMin to yMax with 'g' intermediate steps
		if(i == 0){
			yArr[i] = yMin;
		}
		else{
			yArr[i] = yArr[i-1] + (17.0 / (g-1));
		}
	}
}//createArray

void printArray(){	//prints the array in bitmap or values form based on user input 'opt'
	if(opt == 0){
		for(int i = 0; i < g*g; i++){ 	//print X for negative values
			if(arr[i] < 0){
				cout<<"X";
			}
			else{
				cout<<"O";		//print Y for positive values
			}
			if((i+1) % g == 0){		//print newline if end of 'row' reached 
				cout<<"\n";
			}
		}
	}
	else{
		for(int i = 0; i < g*g; i++){		//print the values straight from the array
			cout<<arr[i];
			if((i+1) % g == 0){		//print newline if end of 'row' reached 
				cout<<"\n";
			}
			else{
				cout<<" , ";		//add a comma between values for readability
			}
		}
	}
	cout<<"\n";
}//printArray

void functionOne(){		//sin(x)cos(y)
	int k = 0;
	grad(); cin >> g;	//print prompt, retrieve input
	createArrays();	//build the arrays based on graduations
		
	for(int i = 0; i < g; i++){ //Y
		for(int j = 0; j < g; j++){ //X
			double x = xArr[j];		//retrieve x and y values from their arrays
			double y = yArr[i];
			
			arr[k] = sin(x)*cos(y); k++;	//calculate value of array[k], move to next array position
		}
	}
	
	bitval(); cin >> opt;	//print prompt, retrieve input
	printArray();		//print the array based on option
}//functionOne

void functionTwo(){		//sin(x)+cos^2(y/2)-x/y
	int k = 0;
	grad(); cin >> g;	//print prompt, retrieve input
	createArrays();	//build the arrays based on graduations
		
	for(int i = 0; i < g; i++){ //Y
		for(int j = 0; j < g; j++){ //X
			double x = xArr[j];		//retrieve x and y values from their arrays
			double y = yArr[i];
			
			arr[k] = sin(x)+pow(cos(y/2.0),2)-(x/y); k++;	//calculate value of array[k], move to next array position		
		}
	}
	
	bitval(); cin >> opt;	//print prompt, retrieve input
	printArray();		//print the array based on option
}//functionThree

void functionThree(){		//1/2 sin(x) + 1/2 cos(y)
	int k = 0;
	grad(); cin >> g;	//print prompt, retrieve input
	createArrays();	//build the arrays based on graduations
		
	for(int i = 0; i < g; i++){ //Y
		for(int j = 0; j < g; j++){ //X
			double x = xArr[j];		//retrieve x and y values from their arrays		
			double y = yArr[i];
			
			arr[k] = (0.5*sin(x)) + (0.5*cos(y)); k++;	//calculate value of array[k], move to next array position			
		}
	}
	
	bitval(); cin >> opt;	//print prompt, retrieve input
	printArray();		//print the array based on option
}//functionThree

void functionFour(){		//1/2 sin(x) + xcos(3y)
	int k = 0;
	grad(); cin >> g;	//print prompt, retrieve input
	createArrays();	//build the arrays based on graduations
	
	for(int i = 0; i < g; i++){ //Y
		for(int j = 0; j < g; j++){ //X
			double x = xArr[j];		//retrieve x and y values from their arrays
			double y = yArr[i];
			
			arr[k] = (0.5*sin(x)) + (x*(cos(3*y))); k++;	//calculate value of array[k], move to next array position			
		}
	}
	
	bitval(); cin >> opt;	//print prompt, retrieve input
	printArray();		//print the array based on option
}//functionFour
