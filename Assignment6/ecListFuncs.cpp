/*  Name: Da Zhang
 *  USC NetID: zhan234
 *  CS 455 Fall 2017
 *
 *  See ecListFuncs.h for specification of each function.
 */

#include <iostream>

#include <cassert>

#include "ecListFuncs.h"

using namespace std;

/* Calculates the number of runs.
 * Uses int * type to hold the temp number rather than a int type,
 * because the number could be the number in the list, which could cause an error.
 * Algorithm complexity: O(n).
 * 
 * @params
 *          list  the pointer point to the original list
 */
int numRuns(ListType list) {
  int num = 0;
  int count = 0;
  ListType p = list;
  int *data = NULL;
  while(p != NULL){
    if(data == NULL){
      data = &p->data;
      p = p->next;
      continue;
    }
    if(*data == p->data){
      count++;
      p = p->next;
    }
    else if(*data != p->data){
      if(count > 0){
        num++;
      }
      data = &p->data;
      count = 0;
      p = p->next;
    }
    if(p == NULL){
      if(count > 0){
        num++;
      }
    }
  }
  return num;  
}

/* Reverses and copy the original list.
 * Inserts the elements in original list into the front of new list.
 * Algorithm complexity: O(n).
 * 
 * @params
 *          list  the pointer point to the original list
 */
ListType reverseCopy(ListType list) {
  ListType newList = NULL;
  ListType p = list;
  while(p != NULL){
    ListType q = new Node(p->data);
    q->next = newList;
    newList = q;
    p = p->next;
  }
  return newList; 
}

/* Removes the middle element of the list.
 * Note the number of the list is even or odd.
 * Algorithm complexity: O(n).
 * 
 * @params
 *          list  the reference of the original list
 */
void removeMiddleElmt(ListType &list) {
  if(list == NULL){
    return;
  }
  ListType r = list;
  ListType p = list;
  int size = 0;
  int mid = -1;
  while(p != NULL){
    size++;
    p = p->next;
  }
  if(size == 0 || size == 1){
    list = NULL;
    return;
  }
  if(size % 2 == 0){
    mid = size / 2 - 1;
  }
  else{
    mid = size / 2;
  }
  if(mid == 0){
    list = list->next;
    return;
  }
  else{
    for(int loc = 0; r != NULL; loc++){
      if(loc == mid - 1){
        ListType q = r->next;
        r->next = q->next;
        delete q;
        break;
      }
      r = r->next;
    }
  }

}

/* Splits the list by the value.
 * Deletes the node when it is useless.
 * Algorithm complexity: O(n).
 * 
 * @params
 *          list      the reference of the original list
 *          splitVal  the number which the list would be splited by
 *          a         the head reference of the list before splitVal
 *          b         the head reference of the list after splitVal
 */
void splitOn(ListType &list, int splitVal, ListType &a, ListType &b) {
  a = list;
  b = NULL;
  if(list == NULL){
    return;
  }
  if(list->next == NULL){
    if(list->data == splitVal){
      a = NULL;
      delete list->next;
    }
    list = NULL;
    return;
  }
  if(list->data == splitVal){
    a = NULL;
    b = list->next;
    delete list;
    list = NULL;
    return;
  }
  a = list;
  b = NULL;
  ListType p = list;
  ListType q = list->next;
  while(q != NULL){
    if(q->data == splitVal){
      p->next = NULL;
      b = q->next;
      delete q;
      break;
    }
    p = p->next;
    q = q->next;
  }
  list = NULL;
}
