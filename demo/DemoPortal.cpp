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
    outfile.open("PortalToPlatform.txt", ios::app);
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



    string response;
    vector<string> responses;
    string portalidcheck;
    string requestidcheck;
    string nature;
    string nextval;
    vector<Product> product_list;
    string productname;
    string productid;
    string  parameter;
    int price;
    int quantity;
    string pref="";

    if (Fileio.is_open())
    {
        while(true){
        getline(Fileio,response);
        if(response==pref){
            break;
        }
        responses=split(response," ");

        portalidcheck=responses[0];
        requestidcheck=responses[1];
        nextval=responses[2];
        nature=request_map[requestidcheck];
        if(nature=="Start"){
            cout<<"Here are the products we offer right now"<<endl;
            for(int i=2;i<responses.size();i++){
                cout<<responses[i];
            }
            cout<<endl;
        }else if(nature=="Buy"){
            cout<<"Here is result of your Transactions"<<endl;
            cout<<responses[2];
        }else{
            parameter=nature;
            while(requestidcheck==responses[1]){
            productname = responses[2];
            productid=response[3];
            price=response[3];
            quantity=response[3];
            Product temp = *(new Product(productname, productid, price, quantity));
            product_list.push_back(temp);
            getline(Fileio,response);

            }
            if (nature == "Name")
            {
                sort(product_list.begin(), product_list.end(), Comparator::SortByName);
            }
                else if (nature == "Price")
                {
                sort(product_list.begin(), product_list.end(), Comparator::SortByPrice);
                }

            cout<<"Here are your products sorted by "<<parameter<<endl;
                    for (Product a : product_list)
                    {
                        cout << a.getName() << " " << a.getProductID() << " " << a.getPrice() << " " << a.getQuantity()<<endl;
                    }


        }
        getline(Fileio,response);
        pref=response;
        }

        }




        }

vector<string> DemoPortal::split(string s, string del){
            vector<string> temp;
            int start, end = -1*del.size();
            do {
                start = end + del.size();
                end = s.find(del, start);
                temp.push_back(s.substr(start, end - start));
            } while (end != -1);

    return temp;
        }
