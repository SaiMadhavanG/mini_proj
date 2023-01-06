/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
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
    prev_rid = "";
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
        if (categories.find(totalcommand[1]) == categories.end())
        {
            cout << "Invalid Category name, type 'Start' to get a list of categories" << endl;
        }
        else if (totalcommand[2] != "Name" && totalcommand[2] != "Price")
        {
            cout << "Invalid Sort Order, Sorting can be done according to 'Name' or 'Price'" << endl;
        }
        else
        {
            outfile << portal_id << " " << requestID << " "
                    << "List"
                    << " " << totalcommand[1] << endl;
            request_map[requestID] = totalcommand[2];
        }
    }
    else if (totalcommand[0] == "Buy")
    {
        if (productset.find(totalcommand[1]) == productset.end())
        {
            cout << "Invalid product ID, type 'List <CategoryName> <SortOrder>' to get a sorted list of products belonging to that category" << endl;
        }
        else if (stoi(totalcommand[2]) < 1)
        {
            cout << "Quantity must be greater than 0" << endl;
        }
        else
        {
            outfile << portal_id << " " << requestID << " "
                    << "Buy"
                    << " " << totalcommand[1] << " " << totalcommand[2] << endl;
            request_map[requestID] = "Buy";
        }
    }
    else if (totalcommand[0] == "Check")
    {
        checkResponse();
    }
    outfile.close();
}

// A function that reads the respones from PlatformToPortal.txt and prints out in the terminal
void DemoPortal::checkResponse()
{
    ifstream fi("PlatformToPortal.txt");
    string r, prev = "";
    // 'line' is a variable that maintains count of how many lines have been already read from the PlatforToPortal.txt file
    // This loop makees sure we don't read those lines again
    for (int i = 0; i < line; i++)
    {
        getline(fi, r);
    }
    // This while loop ensures we read until the ean od the file
    while (true)
    {
        getline(fi, r);
        // Prev holds the previous line
        // At end of file, the last line starts repeating when getline() is called. So, we terminate the while loop when we start seeing empty strings or repetitions
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
        // We split the response string into words for easier processing
        vector<string> responses = split(r);
        string pid = responses[0];
        string rid = responses[1];
        // We ignore the response if the portalID doesn't match
        if (pid != portal_id)
            continue;
        // If the previous command was a list command, we enter this block
        if (list_in_process)
        {
            // If the current line is a response to the same list request, we add it to a vector that contains all the responses to this particular list request
            if (rid == list_rid)
            {
                listing.push_back(r);
                continue;
            }
            // Otherwise, we flip the the 'list_in_process' flag and call the processListing() method
            else
            {
                list_in_process = false;
                processListing();
            }
        }
        // prev_rid holds the value of the previous request ID. If we are seeing the same line again, we skip it
        if (prev_rid == rid)
        {
            continue;
        }
        // request_map is a map that contains the type of request corresponding to each requestID
        // We process a response depending on which type of request it was
        string nature = request_map[rid];
        // If we get a response for a Start request, we print out the categories
        if (nature == "Start")
        {
            cout << "The categories being offered are: -\n";
            for (int j = 2; j < responses.size(); j++)
            {
                cout << "* " << responses[j] << endl;
                categories.insert(responses[j]);
            }
        }
        // If we get a response for a List request, we set the list_in_process flag true and add the line to the listing vector
        else if (nature == "Name" || nature == "Price")
        {
            list_in_process = true;
            list_rid = rid;
            listing.push_back(r);
        }
        // If we get a response for a Buy request, we print out a success or failure message
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
        prev_rid = rid;
    }
    line--;
}

// A function to split a string into its corresponding words
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

// A function that processes, sorts and prints the responses in listing vector at the end of a list response
void DemoPortal::processListing()
{
    vector<Product> productsList;
    string rid;
    // We create Product objects and add them to the productsList vector
    for (string s : listing)
    {
        vector<string> responses = split(s);
        rid = responses[1];
        Product product(responses[2], responses[3], stof(responses[4]), stoi(responses[5]));
        productsList.push_back(product);
        productset.insert(responses[3]);
    }
    listing.clear();
    string nature = request_map[rid];
    // We sort the list with the help of a comparator
    if (nature == "Price")
    {
        sort(productsList.begin(), productsList.end(), Comparator::SortByPrice);
    }
    else if (nature == "Name")
    {
        sort(productsList.begin(), productsList.end(), Comparator::SortByName);
    }
    // Printing  each of the elelments of the vector to the terminal
    cout << "\nHere are the products sorted with respect to " << nature << ": -" << endl;
    printf("| %20s | %70s | %10s | %10s |\n", "Product ID", "Name", "Price", "Quantity");
    for (Product product : productsList)
    {
        printf("| %20s | %70s | %10s | %10s |\n", product.getProductID().c_str(), product.getName().c_str(), to_string(product.getPrice()).substr(0, to_string(product.getPrice()).size() - 4).c_str(), to_string(product.getQuantity()).c_str());
    }
    cout << endl;
}
