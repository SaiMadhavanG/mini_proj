#include<bits/stdc++.h>
#include"Portal.h"
using namespace std;
void Portal::processUserCommand(string command)
{
    // portalID=100;
    int requestID=10;
    ofstream outfile;
    outfile.open("PortaltoPlatform.txt");
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
    if(totalcommand[0]=="Start")
    {
        outfile<<portalID<<" "<<requestID<<" "<<"Start"<<endl;
    }
    else if(totalcommand[0]=="List")
    {
        outfile<<portalID<<" "<<requestID<<" "<<"List"<<" "<<totalcommand[1]<<endl;
    }
    else if(totalcommand[0]=="Buy")
    {
        outfile<<portalID<<" "<<requestID<<" "<<"Buy"<<" "<<seller<<"-"<<totalcommand[1]<<" "<<totalcommand[2]<<endl;
    }
    else if(totalcommand[0]=="Check")
    {
        outfile<<portalID<<" "<<requestID<<" "<<"Check"<<endl;
    }
    outfile.close();
}
void Portal::checkResponse()
{
    
}
int main()
{
    processrequests();
    return 0;
}
