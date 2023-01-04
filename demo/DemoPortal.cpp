#include <bits/stdc++.h>
#include "DemoPortal.h"
#include "Product.h"
#include "Comparator.h"
#include <algorithm>
using namespace std;

DemoPortal::DemoPortal(string portal_id)
{

    Fileio.open("PlatformToPortal.txt");
    this->portal_id = portal_id;
    req_no = 1;
}

void DemoPortal::processUserCommand(string command)
{
    string requestID = "Request_" + portal_id.substr(7) + '_' + to_string(req_no++);
    ofstream outfile;
    outfile.open("PortaltoPlatform.txt", ios::app);
    vector<string> totalcommand;
    string word;
    string seller;
    for (auto x : command)
    {
        if (x == ' ')
        {
            totalcommand.push_back(word);
            word = "";
        }
        else
        {
            word = word + x;
        }
    }
    totalcommand.push_back(word);
    if (totalcommand[0] == "Start")
    {
        outfile << portal_id << " " << requestID << " "
                << "Start" << endl;
        request_map[requestID] = "Start";
    }
    else if (totalcommand[0] == "List")
    {
        outfile << portal_id << " " << requestID << " "
                << "List"
                << " " << totalcommand[1] << endl;
        request_map[requestID] = totalcommand[2];
    }
    else if (totalcommand[0] == "Buy")
    {
        outfile << portal_id << " " << requestID << " "
                << "Buy"
                << " " << seller << "-" << totalcommand[1] << " " << totalcommand[2] << endl;
        request_map[requestID] = "Buy";
    }
    else if (totalcommand[0] == "Check")
    {
        checkResponse();
    }
    outfile.close();
}
void DemoPortal::checkResponse()
{
    string portalidcheck;
    string requestidcheck;
    string nature;
    string nextval;
    vector<Product> product_list;
    string productname;
    string productid;
    int price;
    int quantity;

    if (Fileio.is_open())
    {

        Fileio >> portalidcheck;

        while (!portalidcheck.empty())
        {
            if (portal_id == portalidcheck)
            {
                Fileio >> requestidcheck;
                nature = request_map[requestidcheck];
                Fileio >> nextval;
                if (nature == "Start")
                {
                    while (nextval != portal_id)
                    {
                        cout << nextval << endl;

                        Fileio >> nextval;
                    }
                    portalidcheck = nextval;
                    continue;
                }
                else if (nature == "Buy")
                {
                    cout << nextval;
                    Fileio >> portalidcheck;
                    continue;
                }
                else
                {
                    while (nextval != portal_id)
                    {
                        productname = nextval;
                        Fileio >> productid;
                        Fileio >> price;
                        Fileio >> quantity;
                        Product temp = *(new Product(productname, productid, price, quantity));
                        product_list.push_back(temp);

                        Fileio >> nextval;
                    }

                    if (nature == "Name")
                    {
                        sort(product_list.begin(), product_list.end(), Comparator::SortByName);
                    }
                    else if (nature == "Price")
                    {
                        sort(product_list.begin(), product_list.end(), Comparator::SortByPrice);
                    }

                    for (Product a : product_list)
                    {
                        cout << a.getName() << " " << a.getProductID() << " " << a.getPrice() << " " << a.getQuantity();
                    }
                    portalidcheck = nextval;
                    continue;
                }
            }
        }
    }
}