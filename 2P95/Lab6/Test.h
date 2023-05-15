//Forward declarations for Test.cpp
#include <iostream>
#include <sstream>

using namespace std;

void inputSets();
void outputSets(Set S1, Set S2);
void outputSet(Set S);
void Union(Set S1, Set S2);
void addElement(Set S, int i);
void removeElement(Set S, int i);
void intersection(Set S1, Set S2);
void difference(Set S1, Set S2);
void element(Set S1, int i);
void subset(Set S1, Set S2);
void strictSubset(Set S1, Set S2);
void strictSuperset(Set S1, Set S2);
void equality(Set S1, Set S2);
void inequality(Set S1, Set S2);
void complement(Set S1);
void cardinality(Set S1);
void universe(Set S1);
void emptySet(Set S1);
void emptyTest(Set S1);
