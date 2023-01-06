/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
#ifndef COMPARATOR_H
#define COMPARATOR_H
#include<bits/stdc++.h>
#include"Product.h"
#include<string>
//Creating comparator class
class Comparator
{
    //Datamembers
    private:
        string typeofcomp;
        string type;
    //We will sort according 2 parameters only Price and Name
    public:
        static bool SortByPrice(Product p1,Product p2);
        static bool SortByName(Product t1,Product t2);
};
#endif