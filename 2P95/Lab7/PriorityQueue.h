//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 Lab Exercise 7

//Template for a Priority Queue
#include <iostream>

template <typename T, int capacity>		//capactiy as expression parameter
class PriorityQueue {
private:
	T arr[capacity];
	long priorityArr[capacity];
	int front, back, count;
public:
	PriorityQueue() : front(0), back(0), count(0) {
		for(int i = 0; i < capacity; i++){
			priorityArr[i] = 0;	//set all priorities to 0
		}
	}
	void enqueue(T item, long priority) {
		int index = 0;
		if(isEmpty()){	//if empty, add to front
			arr[index] = item;
			priorityArr[index] = priority;
		}
		else{
			for(int i = 0; i < count; i++){
				if(priorityArr[i] > priority){
					index = i;	//index found
					break;	
				}
			}
			for(int i = count; i > index; i--){
				arr[i] = arr[i-1];
				priorityArr[i] = priorityArr[i-1];
			}
			arr[index] = item;	//set appropriate location
			priorityArr[index] = priority;
		}
		back++; count++;	//increase count and back track
	}
	
	T dequeue() {
		T pop = peek();	//get the top element
		for(int i = 0; i < back - 1; i++){
			arr[i] = arr[i+1];	//shift other elements to new spots
			priorityArr[i] = priorityArr[i+1];
		}
		back--; count--;	//reduce count and back track
		return pop;		//return the element
	}
	
	bool isEmpty() {
		return count == 0;	//returns true if empty, false if not
	}
	
	T peek() {
		return arr[0];	//returns the next item to remove 
	}

	int getCount() {
		return count;	//returns the current count
	}
};
