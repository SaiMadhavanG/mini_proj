#include <bits/stdc++.h>
#include "Product.h"
using namespace std;
Product::Product()
{
    Name = " ";
    productID = " ";
    price = 0;
    quantity = 0;
}
Product::Product(string name, string productid, int pri, int q)
{
    Name = name;
    productID = productid;
    price = pri;
    quantity = q;
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
