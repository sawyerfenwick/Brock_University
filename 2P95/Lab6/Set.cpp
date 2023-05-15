//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 LabExercise6

#include <iostream>
#include <sstream>
#include "Set.h"

using namespace std;

Set::Set(const bool elements[256]) {		//Set this array to be equal to what was passed
	for(int i = 0; i < 256; i++){
		this->elements[i] = elements[i];
	}
}

Set::Set() {
	for (int i = 0; i < 256; i++){	//Initialize empty array
		elements[i] = false;
	}
}

Set Set::operator+(const Set &other) const {	//Union
	bool arr[256];
	for(int i = 0; i < 256; i++){
		if((*this).elements[i] | other.elements[i]){
			arr[i] = true;
		}
		else{
			arr[i] = false;
		}
	}
	return Set(arr);
}

Set Set::operator+(const int &other) const {	//Technically union; effectively 'add element'
	bool arr[256];
	for(int i = 0; i < 256; i++){
		if((*this).elements[i]){
			arr[i] = true;
		}
		else{
			arr[i] = false;
		}
	}
	arr[other] = true;
	return Set(arr);
}

Set Set::operator-(const Set &other) const {	//Difference
	bool arr[256];
	for(int i = 0; i < 256; i++){
		if(elements[i] && !(other.elements[i])){
			arr[i] = true;
		}
		else{
			arr[i] = false;
		}
	}
	return Set(arr);
}

Set Set::operator-(const int &other) const {	//Effectively 'remove element if present'
	bool arr[256];
	for(int i = 0; i < 256; i++){
		arr[i] = (*this).elements[i];
	}
	arr[other] = false;
	return Set(arr);
}

Set Set::operator^(const Set &other) const {	//Intersection
	return (((*this)+other) - ((*this)-other)) - (other-(*this));
}

Set Set::operator^(const int &other) const {	//Intersection with element
	return (*this) ^ (-(*this) + other);
}

Set Set::operator~() const {			//Complement
	return (+(*this)) - (*this);
}

Set Set::operator+() const {			//Set of Universe
	bool arr[256];
	for(int i = 0; i < 256; i++){
		arr[i] = true;
	}
	return Set(arr);
}

Set Set::operator-() const {			//Empty set
	return Set();
}

bool Set::operator<=(const Set &other) const {//Subset
	for(int i = 0; i < 256; i++){
		if(elements[i] && !(other.elements[i])){
			return false;
		}
	}
	return true;
}

bool Set::operator<(const Set &other) const { //Strict Subset
	return(*this<=other && !(*this==other));
}

bool Set::operator>=(const Set &other) const {//Superset
	return other<=(*this);
}

bool Set::operator>(const Set &other) const {//Strict Superset
	return (other<=(*this)) && !(other==(*this));
}

bool Set::operator==(const Set &other) const {//Test for set equality
	for(int i = 0; i < 256; i++){
		if(!((*this).elements[i] == other.elements[i])){	//If at any point they dont match, return false
			return false;
		}
	}
	return true;
}

bool Set::operator!=(const Set &other) const {//Test for set inequality
	return !((*this)==other);
}

bool Set::operator!() const {			//Test for empty set 
	return (*this)==(-(*this));		
}

int Set::operator()() const {			//Cardinality of set i.e. |Set|
	int count = 0;
	for (int i = 0; i < 256; i++){	//Go through each element in the array
		if(elements[i]){		//If marked true, increase count
			count++;
		}
	}
	return count;				//return count
}

bool Set::operator[](const int &pos) const {	//Test for 'is element of'
	return elements[pos];			//Element either is present, or isn't 
}

std::ostream& operator<<(std::ostream &out, const Set &set) {
	std::stringstream s;
	std::string output;
	s<<'{';				//Begin the same way the input requests it 
	for (int i = 0; i < 256; i++) {	//If Element[i] is true, that means the index number i belongs to the set
		if (set.elements[i]) {		//print it out
			s<<i<<',';
		}
	}
	s >> output;
	if (output.length() > 1)
		output = output.substr(0, output.size()-1);
	output+='}';				//End the line
	out<<output<<std::endl;
	return out;				//Display the line
}

std::istream& operator>>(std::istream &in, Set &set) {
	bool arr[256];
	char open;
	in>>open;
	if(in.fail()||open!='{'){
		in.setstate(std::ios::failbit);
		return in;
	}
	for(int i=0; i<256; i++){
		arr[i] = false;
	}
	std::string buff;
	std::getline(in,buff,'}');
	std::stringstream ss(buff);
	std::string field;
	while(true){
		std::getline(ss,field,',');
		if(ss.fail()){
			break;
		}
		int el;
		std::stringstream se(field);
		se>>el;
		if(el>=0&&el<256){
			arr[el] = true;
		}
	}
	set = Set(arr);
}
