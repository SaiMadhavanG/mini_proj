#include "DemoPortal.h"

class DemoPortal : public Portal
{

    DemoPortal::DemoPortal
    {
        Fileio.open("PlatformToPortal.txt")
    }
    void DemoPortal::processUserCommand(string command)
    {
    }

    void DemoPortal::checkResponse()
    {
        // TODO Auto-generated method
        string responseString;

        if (Fileio.is_open())
        {
        }
    }

    void DemoPortal::static void main()
    {
        // TODO Auto-generated method stub
    }
}
#endif