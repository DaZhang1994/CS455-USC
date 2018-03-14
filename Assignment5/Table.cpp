// Name: Da Zhang
// USC NetID: zhan234
// CSCI 455 PA5
// Fall 2017

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
  hashSize = HASH_SIZE;
  table = new ListType[hashSize];
  for(int i = 0; i < hashSize; i++){
    table[i] = NULL;
  }
}

Table::Table(unsigned int hSize) {
  hashSize = hSize;
  table = new ListType[hashSize];
  for(int i = 0; i < hashSize; i++){
    table[i] = NULL;
  }
}

int * Table::lookup(const string &key) {
  ListType list = table[hashCode(key)];
  ListType result = search(key, list);
  if(result == NULL){
    return NULL;
  }
  return &(result->value);   // dummy return value for stub
}

bool Table::remove(const string &key) {
  ListType &list = table[hashCode(key)];
  if(discard(key, list)){
    return true;
  }
  else{
    return false;
  }
}

bool Table::insert(const string &key, int value) {
  ListType &list = table[hashCode(key)];
  return add(key, value, list);  // dummy return value for stub
}

int Table::numEntries() const {
  int sum = 0;
  for(int i = 0; i < hashSize; i++){
    sum += count(table[i]);
  }
  return sum;  // dummy return value for stub
}

void Table::printAll() const {
  for(int i = 0; i < hashSize; i++){
    print(table[i]);
  }
}

void Table::hashStats(ostream &out) const {
  int nonEmpty = 0;
  int longest = 0;
  int entries = 0;
  for(int i = 0 ; i < hashSize; i++){
    int amount = count(table[i]);
    if(amount > 0){
      nonEmpty++;
    }
    longest = longest > amount ? longest : amount;
    entries += amount;
  }
  out << "number of buckets: " << hashSize << endl;
  out << "number of entries: " << entries << endl;
  out << "number of non-empty buckets: " << nonEmpty << endl;
  out << "longest chain: " << longest << endl;
}


// add definitions for your private methods here
