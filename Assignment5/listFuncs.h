// Name: Da Zhang
// USC NetID: zhan234
// CSCI 455 PA5
// Fall 2017


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//Helper function to search the element,
//If the element is not exist, return NULL
ListType search(const string &theKey, ListType list);

//Helper function to remove the element,
//If the element is not in the table, return false
bool discard(const string &theKey, ListType & list);

//Helper function to add element,
//If the element is already there, return false
bool add(const string &theKey, int theValue, ListType & list);

//Helper function to count the number of entries
int count(ListType list);

//Helper function to display the entries in the bucket
void print(ListType list);

//Helper function to update an element, 
//If the element is not in the list, return false
bool update(const string &theKey, int theValue, ListType list);











// keep the following line at the end of the file
#endif
