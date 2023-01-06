#ifndef DEMO_PORTAL_H
#define DEMO_PORTAL_H
#include "../ecomm/Portal.h"
#include <string>
#include <iostream>
#include <fstream>
#include <map>
#include <set>
using namespace std;

class DemoPortal : public Portal
{

public:
    DemoPortal(string);
    void processUserCommand(string command);
    void checkResponse();
    int req_no;
    vector<string> split(string);
    void processListing();

private:
    ifstream Fileio;
    string portal_id;
    map<string, string> request_map;
    int line;
    bool list_in_process;
    string list_rid;
    vector<string> listing;
    string prev_rid;
    set<string> categories;
    set<string> productset;
};
#endif
