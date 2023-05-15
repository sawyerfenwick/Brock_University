//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 LabExercise6

#include <iostream>
#include <sstream>
#include "Set.h"
#include "Test.h"

using namespace std;

Set A, B;					//Our two sets that we will be doing operations on

void Union(Set S1, Set S2){			//Union
	Set S3 = S1+S2;
	cout << "A ∪ B: ";
	outputSet(S3);
}

void addElement(Set S1, int i){		//Technically union; effectively 'add element'
	Set S2 = S1+i;
	cout << "A ∪ " << i << ": ";
	outputSet(S2);
}

void remove(Set S1, Set S2){
	Set S3 = S1-S2;
	cout << "A - B: ";
	outputSet(S3);
}

void removeElement(Set S1, int i){		//Effectively 'remove element if present' 
	Set S2 = S1-i;
	cout << "A - " << i << ":";
	outputSet(S2);
}

void intersection(Set S1, Set S2){		//Intersection
	Set S3 = S1^S2;
	cout << "A ∩ B: ";
	outputSet(S3);
}

void intersectionElement(Set S1, int i){	//Intersection with Element
	Set S2 = S1^i;
	cout << "A ∩ " << i << ": ";
	outputSet(S2);
}

void difference(Set S1, Set S2){		//Difference
	Set S3 = S1-S2;
	cout << "A\\B:";
	outputSet(S3);
}

void element(Set S1, int a){			//Element of 
	bool S2 = S1[a];
	string tf = "";
	if(S2){
		tf = "True; 3 is an element of set A";
	}
	else{
		tf = "False; 3 is not an element of set A";
	}
	cout << "Checking if 3 is in set A..." << endl;
	cout << "A[b]: " << tf << endl;
}

void subset(Set S1, Set S2){			//Subset 
	bool S3 = S1<=S2;
	string tf = "";
	if(S3){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A ⊆ B: " << tf << endl;
}

void strictSubset(Set S1, Set S2){		//Strict Subset
	bool S3 = S1<S2;
	string tf = "";
	if(S3){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A ⊊ B: " << tf << endl;
}

void superset(Set S1, Set S2){		//Superset
	string tf = "";
	if(S1>=S2){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A ⊇ B: " << tf << endl;
}

void strictSuperset(Set S1, Set S2){		//Strict Superset
	string tf = "";
	if(S1>S2){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A ⊋ B: " << tf << endl;
}

void equality(Set S1, Set S2){		//Equality
	string tf;
	if(S1==S2){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A = B: " << tf << endl;
}

void inequality(Set S1, Set S2){		//Inequality
	string tf;
	if(S1!=S2){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A ≠ B: " << tf << endl;
}

void complement(Set S1){			//Complement
	Set S2 = ~S1;
	cout << "U\\A:";
	outputSet(S2); 
}

void cardinality(Set S1){			//Cardinality
	int S2 = S1();
	cout << "|A|: " << S2 << endl;
}

void universe(Set S1){				//Universe
	Set S2 = +S1;
	cout << "U";
	outputSet(S2);
}

void emptySet(Set S1){				//Empty Set
	Set S2 = -S1;
	cout << "Ø";
	outputSet(S2);
}

void emptyTest(Set S1){			//Empty Test
	bool S2 = !S1;
	string tf = "";
	if(S2){
		tf = "True";
	}
	else{
		tf = "False";
	}
	cout << "A=Ø: " << tf << endl;
}

void inputSets(){				//Input Stream
	cout << "Please input the values for Set A: ";
	cin >> A;
	cout << "Please input the values for Set B: ";
	cin >> B;
	cout << "\n";
}

void outputSets(Set S1, Set S2){		//Output Stream
	cout << "A:" << S1;
	cout << "B:" << S2;
}

void outputSet(Set S){				//Output Stream
	cout << S;
}

int main(){					//Test for all cases
	inputSets();
	outputSets(A,B);
	Union(A,B);
	addElement(A, 10);
	removeElement(A, 5);
	intersection(A,B);
	intersectionElement(A,2);
	difference(A,B);
	element(A,3);
	subset(A,B);
	strictSubset(A,B);
	superset(A,B);
	strictSuperset(A,B);
	equality(A,B);
	inequality(A,B);
	cardinality(A);
	emptySet(A);
	emptyTest(A);
	complement(A);
	universe(A);
}
