//Forward Declarations for hillClimber.cpp
//variables 
static const int NUMTHREADS = 8;	//assuming 8 is the MAXIMUM number of threads
int threads;				//number of threads
double currPos;			//current position within the domain
volatile bool continuing;		//if not interupted
volatile int occupied;			//check if mutex available
volatile double lowestX = 0;		//x value the lowestMin was found at
volatile double lowestY = 0;		//y value the lowestMin was found at
volatile double lowestMinimum = 100000; 	//lowest value found initialized to something large
const double LEFT_DOMAIN= -512;		//left most domain
const double RIGHT_DOMAIN = 512;		//right most domain 
const double LEFT_RANGE = -5;		//range for random increment
const double RIGHT_RANGE = 5;		//range for random increment
pthread_mutex_t lock;			//mutual exclusion locks for thread creation/updating values
pthread_mutex_t minVal_lock;		

//methods 
void* climb(void* unnecessary);
int menu();
void printMenu();
void peek(int sig);
void interrupted(int sig);
double computeResult(double x, double y);
double getRandom();
