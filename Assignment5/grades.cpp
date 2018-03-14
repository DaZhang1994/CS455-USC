// Name: Da Zhang
// USC NetID: zhan234
// CSCI 455 PA5
// Fall 2017

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

void printHelp();

//Main function to receive the parameter,
//Display and manipulate the table according the user's input
int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	    << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    grades = new Table();
  }

  grades->hashStats(cout);

  std::string cmd;
  std::string name;
  int score;

  while(1){
    cout << "cmd> ";
    cin >> cmd;
    if(cmd == "insert"){
      cin >> name;
      cin >> score;
      if(grades->insert(name, score)){
        cout << "Added!" << endl;
      }
      else{
        cout << "Name Already Exists!" << endl;
      }
    }
    else if("change" == cmd){
      cin >> name;
      cin >> score;
      int *oldScore = grades->lookup(name);
      if(oldScore != NULL){
        *oldScore = score; 
        cout << "Updated!" << endl;
      }
      else{
        cout << "Name Not Found!" << endl;
      }
    }
    else if("lookup" == cmd){
      cin >> name;
      int* score = grades->lookup(name);
      if(score == NULL){
        cout << "Name Not Found!" << endl;
      }
      else{
        cout << name << " : " << *score << endl;
      }
    }
    else if ("remove" == cmd){
      cin >> name;
      if(grades->remove(name)){
        cout << "Deleted!" << endl;
      }
      else{
        cout << "Name Not Found!" << endl;
      }
    }
    else if ("print" == cmd){
      grades->printAll();
    }
    else if ("size" == cmd){
      cout << "Size : " << grades->numEntries() << endl;
    }
    else if ("stats" == cmd){
      grades->hashStats(cout);
    }
    else if ("help" == cmd){
      printHelp();
    }
    else if ("quit" == cmd){
      break;
    }
    else {
      cout << "ERROR: invalid command" << endl;
      printHelp();
    }
  }

  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*

  return 0;
}

//Helper function to print the menu
void printHelp(){
  cout << "insert name score     --- Insert this name and score in the grade table." << endl;
  cout << "change name newscore  --- Change the score for name." << endl;
  cout << "lookup name           --- Lookup the name, and print out his or her score." << endl;
  cout << "remove name           --- Remove this student." << endl;
  cout << "print                 --- Prints out all names and scores." << endl;
  cout << "size                  --- Prints out the number of entries." << endl;
  cout << "stats                 --- Prints out statistics about the hash table at this point." << endl;
  cout << "help                  --- Prints out a brief command summary." << endl;
  cout << "quit                  --- Exits the program." << endl;
}
