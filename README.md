# Hong Kong Mini Buses improvement project

In this repository, you will find the project developed during hackUST2018. The aim of that project was to develop mobile apps for both mini bus drivers and customers that would optimise the waiting time at the bus station, the occupancy rate of the bus and the waiting queues at the different bus stops along the line. It would also give useful information such as the position of the bus in real time and the Estimate Time Arriving.
## Files
### MiniBusDriver/
This folder contains the source code of the Android application for the bus drivers. The aim of this app is for the driver to communicate the position of the bus in real time as well as the number of seats left in the vehicle. The driver will receive the order to leave the terminus station if the server decides that it is optimised because people are waiting for the bus ahead in the bus route.

### User/
This folder contains the source code of the Android application for the bus customers. This app is useful to look at the position of the buses in real time, the user can indicate her/his destination and the app will compute the best route and the average commute time, including the queuing time, and traffic jam. This app also serve to indicate to the server that the user is waiting for one or many buses at a given stop.
### backend.py
This file is a simple python server that makes the communication between the driver's and customer's applications. The server is responsible to compute commute time and to give the order to the bus drivers to leave if it is optimised.

![Alt text](/screenshot.jpg?raw=true "Communication between apps")
