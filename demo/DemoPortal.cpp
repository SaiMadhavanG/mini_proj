#include <bits/stdc++.h>
#include "DemoPortal.h"
#include "Product.h"
#include "Comparator.h"
#include <algorithm>
using namespace std;

DemoPortal::DemoPortal(string portal_id)
{
    line = 0;
    this->portal_id = portal_id;
    req_no = 1;
    list_in_process = false;
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
                << " " << totalcommand[1] << " " << totalcommand[2] << endl;
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
    ifstream fi("PlatformToPortal.txt");
    string r, prev = "";
    for (int i = 0; i < line; i++)
    {
        getline(fi, r);
    }
    while (true)
    {
        getline(fi, r);
        if (r == prev || r == "")
        {
            if (list_in_process)
            {
                list_in_process = false;
                processListing();
                line++;
            }
            break;
        }
        line++;
        vector<string> responses = split(r);
        string pid = responses[0];
        string rid = responses[1];
        if (pid != portal_id)
            continue;
        if (list_in_process)
        {
            if (rid == list_rid)
            {
                listing.push_back(r);
                continue;
            }
            else
            {
                list_in_process = false;
                processListing();
            }
        }
        string nature = request_map[rid];
        if (nature == "Start")
        {
            cout << "The categories being offered are: -\n";
            for (int j = 2; j < responses.size(); j++)
            {
                cout << "* " << responses[j] << endl;
            }
        }
        else if (nature == "Name" || nature == "Price")
        {
            list_in_process = true;
            list_rid = rid;
            listing.push_back(r);
        }
        else if (nature == "Buy")
        {
            if (responses[2] == "Success")
            {
                cout << "Product(s) purchased successfully!" << endl
                     << endl;
            }
            else if (responses[2] == "Failure")
            {
                cout << "Sorry, purchase of product failed." << endl
                     << endl;
            }
        }
        prev = r;
    }
    line--;
}

vector<string> DemoPortal::split(string s)
{
    vector<string> temp;
    stringstream ss(s);
    string word;
    while (ss >> word)
    {
        temp.push_back(word);
    }
    return temp;
}

void DemoPortal::processListing()
{
    vector<Product> productsList;
    string rid;
    for (string s : listing)
    {
        vector<string> responses = split(s);
        rid = responses[1];
        Product product(responses[2], responses[3], stof(responses[4]), stoi(responses[5]));
        productsList.push_back(product);
    }
    listing.clear();
    string nature = request_map[rid];
    if (nature == "Price")
    {
        sort(productsList.begin(), productsList.end(), Comparator::SortByPrice);
    }
    else if (nature == "Name")
    {
        sort(productsList.begin(), productsList.end(), Comparator::SortByName);
    }
    cout << "\nHere are the products sorted with respect to " << nature << ": -" << endl;
    printf("| %20s | %70s | %10s | %10s |\n", "Product ID", "Name", "Price", "Quantity");
    for (Product product : productsList)
    {
        printf("| %20s | %70s | %10s | %10s |\n", product.getProductID().c_str(), product.getName().c_str(), to_string(product.getPrice()).substr(0, to_string(product.getPrice()).size() - 4).c_str(), to_string(product.getQuantity()).c_str());
    }
    cout << endl;
}
