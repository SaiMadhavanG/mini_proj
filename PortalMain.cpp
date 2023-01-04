#include <iostream>
#include <ctime>
#include "./ecomm/Portal.h"
#include "./demo/DemoPortal.h"

using namespace std;

int main()
{
    time_t rawtime;
    struct tm *info;
    time(&rawtime);
    info = localtime(&rawtime);
    string portal_id = "Portal_" + to_string(info->tm_year) + to_string(info->tm_yday) + to_string(info->tm_hour) + to_string(info->tm_min) + to_string(info->tm_sec);
    DemoPortal *portal = new DemoPortal(portal_id);
    string list_of_commands = "Please use one of the following commands:- \n • Start - Shows the list of categories available across all the sellers. \n • List <Category> <SortOrder> - Shows list of products belonging to Category from all sellers who have that Category, sorted by SortOrder, which is one of Price or Name \n • Buy <productID> <numItems> - Buy that many items of the specified productID. \n • Check - Checks if there is a response from the platform for an earlier request. \n • Help - Lists out the list of commands \n • Quit - Exits the program\n\n";
    cout << "Welcome to your ecommerce platform!" << endl;
    cout << list_of_commands;
    string command, command_line;
    while (true)
    {
        getline(cin, command_line);
        stringstream c(command_line);
        getline(c, command, ' ');
        if (command == "Start" || command == "List" || command == "Buy" || command == "Check")
        {
            portal->processUserCommand(command_line);
        }
        else if (command == "Help" || command == "help")
        {
            cout << list_of_commands;
        }
        else if (command == "Quit" || command == "quit")
        {
            break;
        }
        else
        {
            cout << "Please enter a valid command!\nType \"Help\" to get a list of commands\n";
        }
    }

    return 0;
}