#include <iostream>
//Reminder: http://en.cppreference.com/w/cpp/language/operators

class Set {
private:
	bool elements[256];
	Set(const bool elements[256]); //Helpful for immutable types
public:
	Set();
	
	Set operator+(const Set &other) const; //Union
	Set operator+(const int &other) const; //Technically union; effectively 'add element'
	Set operator-(const Set &other) const; //Difference
	Set operator-(const int &other) const; //Effectively 'remove element if present'
	Set operator^(const Set &other) const; //Intersection
	Set operator^(const int &other) const; //Intersection with element
	Set operator~() const; //Complement
	
	Set operator+() const; //Set of universe
	Set operator-() const; //Empty set
	
	bool operator<=(const Set &other) const; //Subset
	bool operator<(const Set &other) const; //Strict subset
	bool operator>=(const Set &other) const; //Superset
	bool operator>(const Set &other) const; //Strict superset
	bool operator==(const Set &other) const; //Test for set equality
	bool operator!=(const Set &other) const; //Test for set inequality
	bool operator!() const; //Test for empty set
	
	int operator()() const; //Cardinality of set. i.e. |Set|
	bool operator[](const int &pos) const; //Test for 'is element of'
	
	friend std::ostream& operator<<(std::ostream &out, const Set &set);
	friend std::istream& operator>>(std::istream &in, Set &set);
};
