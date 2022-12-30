#include<bits/stdc++.h>
#include"Product.h"
using namespace std;
Product::Product()
{
    Name=" ";
    productID=" ";
    price=0;
    quantity=0;
}
string Product::getName()
{
    return Name;
}
string Product::getProductID()
{
    return productID;
}
float Product::getPrice()
{
    return price;
}
int Product::getQuantity()
{
    return quantity;
}