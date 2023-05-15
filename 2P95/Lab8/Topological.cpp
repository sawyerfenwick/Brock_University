//Sawyer Fenwick
//6005011
//sf15zx@brocku.ca
//COSC 2P95 LabExercise8

#include <iostream>
#include <fstream>
#include <cstring>
#include "Topological.h"
using namespace std;

Graph::Graph(int vert, string verticesArray[]) 
{ 
	this->top = vert;
	this->vert = vert; 
	this->vertices = verticesArray;
	// Create a dynamic array of pointers 
	adjMatrix = new bool* [vert]; 
	visited = new bool [vert];
	indegree = new int [vert];
	indegreeTwo = new int [vert];	//because im lazy, have mercy George I'm a very busy man
	// Create a row for every pointer 
	for (int i=0; i<vert; i++) { 
			
			adjMatrix[i] = new bool[vert]; 
			
			//set all to false initally, and indegrees to 0 
			memset(adjMatrix[i], false, vert*sizeof(bool)); 
			visited[i] = false;
			indegree[i] = 0;
			indegreeTwo[i] = 0;
	} 
} 

//print vertices  
void Graph::printVertices(){ 
	for (int u=0; u<vert; u++) { 
		cerr << "[" << u << ":" << vertices[u] << "]";
		if(u < vert - 1){
			cerr << ", ";
		}
		if(u % 6 == 0 & u != 0){
			cerr<< "\n";
		}
	} 
}

//reset the visited array
void Graph::resetVisited(){ 
	for (int u=0; u<vert; u++) { 
		visited[u] = false;
	} 
} 

//because im REALLY lazy (also very busy, just making it work, have mercy if you look at this George).
void Graph::topological(int k){
	visited[k] = true;
	cout << vertices[k] << " ";
	if(k % 6 == 0 & k != 0){
		cout << "\n";
	}
	for(int i = 0; i < vert; i++){
		if(adjMatrix[k][i] == true){
			if(visited[i] == false){
				indegreeTwo[i] -= 1;
				if(indegreeTwo[i] == 0){
					topological(i);
				}
			}
		}
	}
}

//perform a topological search
void Graph::topologicalSearch(int k){
	visited[k] = true;
	for(int i = 0; i < vert; i++){
		if(adjMatrix[k][i] == true){
			if(visited[i] == false){
				indegree[i] -= 1;
				if(indegree[i] == 0){
					topologicalSearch(i);
				}
			}
		}
	}
}

void Graph::callTopological(){
	
	bool dependency = false;		//used to check if cyclic dependencies exist
	
	for(int i = 0; i < vert; i++){
		if(!visited[i] & indegree[i] == 0){
			topologicalSearch(i);	//perform the search
		}
	}
	
	for(int i = 0; i < vert; i++){
		if(visited[i] == false){
			dependency = true;	//if all the nodes were not visited, the topological search failed
			break;
		}
	}
	
	if(dependency){	//fail
		cerr << "Topological Search Not Possible. Cyclic Dependency Encountered.\n";
	}
	else{			//success
		resetVisited();	//reset the visited array so we can do the topological search again, this time printing it out
	
		cout << "Topological Search Found!\n";
		for(int i = 0; i < vert; i++){
			if(!visited[i] & indegreeTwo[i] == 0){	//find a node that has an indegree of 0 that we have not been to yet and search
				topological(i);
			}
		}
	}
}

//print edges of vertices matrix
void Graph::printEdges(){
	int num = 0;
	cout << "Edges:\n";
		for(int i = 0; i < vert; i++){
			cout << vertices[i] << " -> ";
			for(int j = 0; j < vert; j++){
				if(adjMatrix[i][j] == 1){
					if(num == 0){
						cout << vertices[j];
					}
					else{
						cout << "," << vertices[j];
					}
					num = 1;
				}
			}
			num = 0;
			cout << "\n";
		}
}

int main() {
	string s,in,out;
	int dft = 0;
	int numVert,edges,from,to, indegree;
	string fileName;
	int vertexIndex;
	ifstream infile;
	
	cerr << "Graph filename: ";
	cin >> in;
	
	infile.open(in);	//open file
	
	if(infile.is_open()){	//check if its open
		cerr << "Using " << "\033[1;31m" << in << "\033[0m\n" << "\nFile loaded.\nLoaded graph.\n\n";	//print file name in red
		infile >> numVert;		//read the first item which is vertices 
		string vertices[numVert];
		cout << "Vertices:\n";
		for(int i = 0; i < numVert; i++){	//get the vertices names
			infile >> s;
			vertices[i] = s;
		}
	
		Graph g(numVert, vertices);	//create the graph structure
		
		infile >> edges;	//get num of edges from file
		
		for (int i = 0; i < edges; i++){
			infile >> from;	//from edge B ->
			infile >> to;		//to edge -> C
			g.addEdge(from, to);	//add edge
		}
	
		g.printVertices();		//print the vertices
		cerr << "\n\n";
		g.printEdges();		//print the edges
		g.callTopological();		//begin topological search proccess
		cout << "\n";
		infile.close();		//close file
	}
	else{
		cerr << "Error opening file.\n";	//print error
	}
}
