/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
#include <bits/stdc++.h>
#include "Product.h"
using namespace std;
//Default constructor of Product
Product::Product()
{
    Name = " ";
    productID = " ";
    price = 0;
    quantity = 0;
}
//Parametrised constructor of Product
Product::Product(string name, string productid, float pri, int q)
{
    Name = name;
    productID = productid;
    price = pri;
    quantity = q;
}
//getter functions of the class
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