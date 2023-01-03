#include<bits/stdc++.h>
#include"Product.h"
#include"Comparator.h"
using namespace std;
bool Comparator::SortByPrice(Product *p1,Product *p2)
{
    if(p1->getPrice()==p2->getPrice())
        return p1->getProductID()<p2->getProductID();
    return p1->getPrice()<p2->getPrice();
}
bool Comparator::SortByName(Product *p1,Product *p2)
{
    if(p1->getName()==p2->getName())//Sorting by price incase the names are the same
    {
        return SortByPrice(p1,p2);
    }
    return p1->getName()<p2->getName();
}