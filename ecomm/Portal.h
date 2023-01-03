#ifndef PORTAL_H
#define PORTAL_H
#include <string>
#include "Product.h"
#include <vector>

using namespace std;
class Portal
{

    Portal();

    // Invoked by main or driver class
    // sends command to Platform (by writing to PortalToPlatform)
    // Each command line in the file should have a PortalID as the first token
    // and a RequestID as second token.
    // PortalID is unique to each instance of Portal
    // Each request from a portal should have a unique ID
public:
    void processUserCommand(string command);

    // checks for pending responses (in PortalToPlatform)
    // Displays response
public:
    void checkResponse();
private:
    // static vector<Product> sortByParameter(vector<Product> productList, string Parameter);
    int portalID;
// private:
//     static bool compare(Product p1, Product p2);
// };
#endif