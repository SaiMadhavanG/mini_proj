#ifndef DEMO_PORTAL_H
#define DEMO_PORTAL_H
#include "Portal.cpp"
#include <string>
#include <iostream>
#include <fstream>

class DemoPortal : public Portal
{

    DemoPortal()
    {
        Fileio.open("PlatformToPortal.txt")
    }

public:
    void processUserCommand(string command);

public:
    void checkResponse();

public:
    static void main()
    {
        // TODO Auto-generated method stub
    }

private:
    ifstream Fileio;
}
#endif