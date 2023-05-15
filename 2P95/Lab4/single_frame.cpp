//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab4Exercise

#include <iostream>
#include <cmath>
#include <fstream>
#include <iomanip>
#include "pgm.h"

using namespace std;

double* arr = 0;
double* xArr = 0;
double* yArr = 0;
int z = zMin;
int g; //graduations
char c[] = {'f','i','l','e','0','0','0','1','.','p','g','m'}; //filename

int main(){
	ofstream file;
	
	file.open(c);		//open file
	
	if(file.is_open()){
		function();							//calculate values
		file << "P2\n" << g << "	" << g << "\n" << "255\n"; 	//add pgm modifiers 
		for(int i = 0; i < g*g; i++){	
			file << arr[i];					//write to file
			if((i+1) % g == 0){
				file << "\n";					//write to file
			}
			else{
				file << "	";				//write to file
			}
		}
	}
	else{
		cerr << "Error opening file\n";				//print error
	}
	
	file.close();		//close file
	return 0;
}//main

void grad(){
	cerr << "Number of graduations per axis: ";
}//grad

void createArray(){
	if(arr != 0)
		delete [] arr;		//garbage collection
		
	if(xArr != 0)
		delete [] xArr;	//garbage collection
		
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

void function(){		//cos(z) * 1/2 sin(x) + 1/2 cos(y)
	int k = 0;
	grad(); cin >> g;	//print prompt, retrieve input 
	createArray();	//build the arrays based on graduations
	
	for(int i = 0; i < g; i++){		//Y
		for(int j = 0; j < g; j++){	//X
			double x = xArr[j];	//retrieve x and y values 
			double y = yArr[i];
			double v = cos(z)*((0.5*sin(x))+(0.5*cos(y)));
			v = trunc((v+1)*127.5);	//scaling	
			arr[k] = v; k++;	//calculate value of array[k], move to next array position
		}
	}
}//function
