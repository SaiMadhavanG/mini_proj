/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
#include"Comparator.h"
using namespace std;
//It will sort the products in increasing order of prices
// if prices is same it will sort according to the productID
bool Comparator::SortByPrice(Product p1,Product p2)
{
    if(p1.getPrice()==p2.getPrice())
        return (p1.getProductID().compare(p2.getProductID()))<0;
    return p1.getPrice()<p2.getPrice();
}
//It will sort the products in increasing order of names
//If names are same we will sort according to the prices
bool Comparator::SortByName(Product p1,Product p2)
{
    if(p1.getName()==p2.getName())//Sorting by price incase the names are the same
    {
        return SortByPrice(p1,p2);
    }
    return (p1.getName().compare(p2.getName()))<0;
}