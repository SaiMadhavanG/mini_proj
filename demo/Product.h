/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
#ifndef PRODUCT_H
#define PRODUCT_H
#include<bits/stdc++.h>
using namespace std;
class Product//Creating class Product
{
    //Datamembers of the class
    private:
        string Name;
        string productID;
        float price;
        int quantity;
    //Member methods
    public:
        Product();
	    string getName();
	    string getProductID();
	    float getPrice();
	    int getQuantity();
};
#endif