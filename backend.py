from flask import Flask
from collections import Counter


# bus_db
# busID : Route | Location | # of seats occupied | # of stops

# user_db
# UserID : Intentions | Location | Destination


app = Flask(__name__)

bus_db = {}
user_db = {}

active_busses = {}
users_waiting = {}

############# User Query Service ##############
def user_init(userID, location):
    user_db[userID][0] = {}
    user_db[userID][1] = location
    user_db[userID][2] = None

def user_update_location(userID, location):
    user_db[userID][1] = location

def update_intentions(userID, intended_busses):
    for route in intended_busses:
        if route not in user_db[userID][0]:
            user_db[userID][0][route] = True

def user_awaits(userID):
    bus_stop = user_db[userID][1]
    for route in user_db[userID][0]:
        if user_db[userID][0][route]:
            if bus_stop not in users_waiting[route]:
                users_waiting[route][bus_stop] = 1
            else;
                users_waiting[route][bus_stop] += 1
    return "success"

# returns (Wait time to first bus, avail num of seats, wait time to 2nd bus, avail num of seats)
def get_waiting_time(userID):
    bus_stop = user_db[userID][1]
    intended_busses = user_db[userID][0]
    times = {}
    for route in intended_busses:
        times[route] = []
        for busID in active_busses[route]:
            if bus_db[busID][1] > bus_stop:
                times[route].append(bus_db[busID][3] - bus_db[busID][1] +  )
            else:
                times[route].append(bus_stop - bus_db[busID][1])
    b1 = -1
    b2 = -1
    b1_time = -1
    b2_time = -2
    for route in intended_busses:
        for idx in len(active_busses[route]):
            busID = active_busses[route][idx]
            t = times[route][idx]
            if b1 == -1:
                b1 = busID:
                b1_time = t
            else if b2 == -1:
                b2 = busID:
                b2_time = t
            else if b1_time > t:
                b1 = busID
                b1_time = t
            else if b2_time > t:
                b2 = busID
                b2_time = t
    return b1_time * 3, 16 - bus_db[b1][2], b2_time * 3, 16 - bus_db[b2][2]


############# Driver Query Service ##############
@app.route("/start/<busID>")
def bus_init(busID):
    active_busses[bus_db[busID]].append(busID)
    bus_db[busID][1] = 0
    bus_db[busID][2] = 16

def bus_update_location(busID, location):
    bus_locations[busID] = location

@app.route("/emptySeats/<busID>/<num_taken_seats>")
def update_num_seats(busID, num_taken_seats):
    bus_db[busID][2] = num_taken_seats

def go(busID):
    # users_waiting_on_bus[
    expected_num_of_clients = 0
    route = bus_db[busID][0]
    for bus_stop in users_waiting[route]:
        expected_num_of_clients += users_waiting[route][bus_stop]

    for busID in active_busses[route]:
        if bus_db[busID][1] != 0:
            expected_num_of_clients -= 16 - bus_db[busID][2]

    return "{}".format((expected_num_of_clients + bus_db[busID][2]) >= 16

if __name__ == '__main__':
   app.run()