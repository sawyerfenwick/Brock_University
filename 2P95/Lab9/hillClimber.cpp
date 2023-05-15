//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 LabExercise9

#include <iostream>
#include <signal.h>
#include <pthread.h>
#include <unistd.h>
#include <cstdlib>
#include <cmath>
#include <random>
#include "hillClimber.h"

using namespace std;

//-1 * (y+47) * sin(sqrt(abs((x/2)+y+47))) - (x*sin(sqrt(abs(x-y-47)))) egg holder function
int main(){
	pthread_t ct[NUMTHREADS]; //child threads
	cout << "Commencing PID: " << getpid() << endl;	//print out pid so you can KILL it
	if (signal(SIGINT,interrupted)==SIG_ERR) {
		cout << "Unable to change signal handler." << endl;
		return 1;
	}
	
	if (signal(SIGUSR1,peek)==SIG_ERR) {
		cout << "Unable to change signal handler." << endl;
		return 1;
	}

	while(true){
		threads = menu();
		if(threads == 0) break;
		continuing = true;
		for (int i=0;i<threads;i++) {
			pthread_mutex_lock(&lock);//reserve lock
			pthread_create(&ct[i], NULL, &climb, NULL);
			occupied++;
			pthread_mutex_unlock(&lock);//release lock
		}
		while (occupied>0) sleep(1);
	}
	cout<<"Execution complete."<<endl;	
}//main

void printMenu(){
	cerr << "Please enter number of threads (0 to quit) : ";
}//printMenu

int menu(){
	int input;
	printMenu();
	cin >> input;
	while(input > 8 or input < 0 or cin.fail()){
		cin.clear();
		cin.ignore(256,'\n');
		printMenu();
		cin >> input;
	}
	if(input == 0){
		cerr << "Goodbye." << endl;
	}
	return input;
}//menu

double computeResult(double x, double y){
	double currMin = 10000000;	//arbitrarily large minimum value
	double difference, egg;	//helpers 
	
	for(int i = 0; i < 4; i++){
		srand(time(NULL)); //seed the random variable so its not the same each time
		x = LEFT_RANGE + ((double)rand() /RAND_MAX) * (RIGHT_RANGE - LEFT_RANGE);	//incrememnt x and y by a random amount between -5 and 5
		y = LEFT_RANGE + ((double)rand() /RAND_MAX) * (RIGHT_RANGE - LEFT_RANGE);
		//Check if x and y are outside of domain, if outside, clamp to end points
		if(x > RIGHT_DOMAIN){
			difference = x - RIGHT_DOMAIN;
			x = x - difference;
		}
		if(x < LEFT_DOMAIN){
			difference = x + abs(LEFT_DOMAIN);
			x = x + abs(difference);
		}
		if(y < LEFT_DOMAIN){
			difference = y + abs(LEFT_DOMAIN);
			y = y + abs(difference);
		}
		if(y > RIGHT_DOMAIN){
			difference = y - RIGHT_DOMAIN;
			y = y - difference;
		} 
		egg = -1 * (y+47) * sin(sqrt(abs((x/2)+y+47))) - (x*sin(sqrt(abs(x-y-47))));	//calculate egg holder function 
		if(egg < currMin){
			currMin = egg;	//if this is best value, update 
		}
	}
	return currMin;	//return smallest of 4 values
}//computeResult

void* climb(void* unnecessary){
	double result, x, y;
	srand(time(NULL));	//seed the random variable so its not the same each time
	x = LEFT_RANGE + ((double)rand() / RAND_MAX) * (RIGHT_RANGE-LEFT_RANGE);	//compute x and y values
	y = LEFT_RANGE + ((double)rand() / RAND_MAX) * (RIGHT_RANGE-LEFT_RANGE);
	while(continuing){
		result = computeResult(x,y);
		pthread_mutex_lock(&minVal_lock);	//going to access lowestMin, lock others from manipulating
		if(result < lowestMinimum){		//i found a new best minimum
			lowestMinimum = result;	//update all BEST variables
			lowestX = x;
			lowestY = y;
			cout << "New Best Found : Lowest Minimum Value = " << lowestMinimum << " Found at (" << lowestX << "," << lowestY << ")" << endl;	//display new lowest values
		}
		else{
			srand(time(NULL)); //seed the random variable so its not the same each time
			x = LEFT_RANGE + ((double)rand() / RAND_MAX) * (RIGHT_RANGE-LEFT_RANGE);	//compute x and y values
			y = LEFT_RANGE + ((double)rand() / RAND_MAX) * (RIGHT_RANGE-LEFT_RANGE);
		}
		pthread_mutex_unlock(&minVal_lock);	//release lock
	}
	pthread_mutex_lock(&lock);	//lock
	occupied--;			//'kill thread'
	cout << "Exiting thread." << endl;
	pthread_mutex_unlock(&lock);	//unlock
}

void peek(int sig){
	cout << "Current Progress : Lowest Minimum Value = " << lowestMinimum << " Found at (" << lowestX << "," << lowestY << ")" << endl;	//display current best without killing "SIGUSR1"
}

void interrupted(int sig){	//halt threads and print current best
	cout << "\nComputations complete.\nHalting now..." << endl;
	cout << "Lowest Minimum Value = " << lowestMinimum << " Found at (" << lowestX << "," << lowestY << ")" << endl;
	continuing = false;	
}
