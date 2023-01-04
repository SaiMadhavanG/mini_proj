PortalMain: PortalMain.o DemoPortal.o Comparator.o Product.o
	g++ PortalMain.o DemoPortal.o Comparator.o Product.o -o PortalMain.out

PortalMain.o: PortalMain.cpp
	g++ -c PortalMain.cpp

DemoPortal.o: ./demo/DemoPortal.cpp ./demo/DemoPortal.h
	g++ -c ./demo/DemoPortal.cpp

Comparator.o: ./demo/Comparator.cpp ./demo/Comparator.h
	g++ -c ./demo/Comparator.cpp
	

Product.o: ./demo/Product.cpp ./demo/Product.h
	g++ -c ./demo/Product.cpp


clean:
	rm *.o