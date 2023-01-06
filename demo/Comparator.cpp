
#include"Comparator.h"

using namespace std;
bool Comparator::SortByPrice(Product p1,Product p2)
{
    if(p1.getPrice()==p2.getPrice())
        return (p1.getProductID().compare(p2.getProductID()))<0;
    return p1.getPrice()<p2.getPrice();
}
bool Comparator::SortByName(Product p1,Product p2)
{
    if(p1.getName()==p2.getName())//Sorting by price incase the names are the same
    {
        return SortByPrice(p1,p2);
    }
    return (p1.getName().compare(p2.getName()))<0;
}
