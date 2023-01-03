//IMT2021538
#ifndef COMPARATOR_H
#define COMPARATOR_H
#include<bits/stdc++.h>
#include"Product.h"
class Comparator
{
    private:
        string typeofcomp;
        string type;
    public:
        static bool SortByPrice(Product* p1,Product* p2);
        static bool SortByName(Product* t1,Product* t2);
};
#endif