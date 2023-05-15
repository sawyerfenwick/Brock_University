//Forward Declarations for priorityq.cpp, definition of a record and a priority queue

using namespace std;

//defines a PRecord, with name, priority and next (node)
struct PRecord {
	string name;
	int priority;
	PRecord* next;
	PRecord(string n, int p){
		name = n;
		priority = p;
		next = NULL;
	}
};//PRecord 

//defines a PQueue (linked list of PRecord nodes)
struct PQueue {
	PRecord *head, *tail;
	PQueue(){
		head = tail = NULL;
	}
	
	//method to add a record to the list
	void enqueue(string name, int priority){
		PRecord* temp = new PRecord(name, priority);
		PRecord* p = head;
		
		if(tail == NULL){
			head = tail = temp;			//if list is empty
			return;
		}
		
		if(head->priority > temp->priority){		//if new node has higher priority than head 
			temp->next = head;
			head = temp;
			return;
		}
		
		while(p->next != NULL && p->next->priority <= temp->priority){	//find appropriate place to insert 
			p = p->next;				//move right
		}
	
		temp->next = p->next;				//insert record
		p->next = temp;
	}//enqueue
	
	//removes the first element from the list
	void dequeue(){
		if(head == NULL){
			cerr << "Nobody is in line.\n";
			return;
		}
		
		PRecord* temp = head;
		head = head->next;
		
		if(head == NULL)
			tail = NULL;
		
		delete temp;					//delete from memory
	}//dequeue
	
	//displays the names and priorities of the records
	void display(){
		if(head == NULL){		//nothing added yet
			cerr << "Nobody is in line.\n";
			return;
		}
		
		PRecord* p = head;
		PRecord* temp = p;
		
		cerr << "Name		Priority\n";
		cerr << p->name << "		" << p->priority << "\n";
		while(p->next != NULL){	//keep going until queue empty
			p = temp->next;	//move right
			cerr << p->name << "		" << p->priority << "\n";
			temp = p;
		}
	}//display
};//PQueue

//writes the menu to the console
void menu(){
	cerr << "1.Add\n2.Remove\n3.Display\n0.Quit\n";
};//menu
