//definition of a graph for Lab Exercise 8
class Graph { 
	int vert;      // No. of vertices
	int top; 	//to determine if topological search possible 
	bool **adjMatrix; 		// adj[u][v] is true if there is an edge
	bool *visited;			//track if edge is visited
	int  *indegree;		//track the indegrees
	int  *indegreeTwo;		//track the indegrees
	std::string *vertices;		//the name of the edge ex. Math 1p01
	
public: 
	Graph(int vert, std::string verticesArray[]);   // Constructor 
	
	void addEdge(int u, int v)  { adjMatrix[u][v] = 1; indegree[v] += 1; indegreeTwo[v] += 1; }	//adds edges to the graph 
	void printVertices(); 			//prints the vertices of the graph
	void printEdges();			//prints the edges of the graph
	void topological(int k);		//prints out the topological search 
	void callTopological();		//helper method to call topological methods
	void topologicalSearch(int k);	//checks if topological search possible
	void resetVisited();	//resets the visited array to not visited (unvisits the elements)
}; 
