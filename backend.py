from flask import Flask, request, jsonify
import os
# bus_db
# busID : Route | Location | # of seats occupied | # of stops

# user_db
# userID : Intentions | Location | Destination


app = Flask(__name__)

############# MockDatabase (in memory) ##############
bus_db = {}
user_db = {}

active_busses = {}
users_waiting = {}

############# User Query Service ##############
def user_init(userID, location):
    if not user_db.get(userID, False):
        user_db[userID] = [0,0,0,0]
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
            if users_waiting.get(route) == None:
                users_waiting[route] = {}
            if bus_stop not in users_waiting[route]:
                users_waiting[route][bus_stop] = 1
            else:
                users_waiting[route][bus_stop] += 1

# returns (bus1 id, Wait time to first bus, avail num of seats, bus2 id, wait time to 2nd bus, avail num of seats)
def get_waiting_time(userID):
    bus_stop = user_db[userID][1]
    intended_busses = user_db[userID][0]
    times = {}
    for route in intended_busses:
        times[route] = []

        for busID in active_busses[route]:
            if bus_db[busID][1] > bus_stop:
                times[route].append(bus_db[busID][3] - bus_db[busID][1] +  bus_stop)
            else:
                times[route].append(bus_stop - bus_db[busID][1])
    b1 = -1
    b2 = -1
    b1_time = -1
    b2_time = -1
    for route in intended_busses:
        for idx in range(len(active_busses[route])):
            busID = active_busses[route][idx]
            t = times[route][idx]
            if b1 == -1:
                b1 = busID
                b1_time = t
            elif b2 == -1:
                b2 = busID
                b2_time = t
            elif b1_time > t:
                b1 = busID
                b1_time = t
            elif b2_time > t:
                b2 = busID
                b2_time = t

    if b1 == -1 and b2 == -1:
        return -1, -1, -1, -1, -1, -1
    elif b2 == -1:
        return bus_db[b1][0], b1_time * 3, 16 - bus_db[b1][2], -1, -1 ,-1
    return bus_db[b1][0], b1_time * 3, 16 - bus_db[b1][2], bus_db[b2][0], b2_time * 3, 16 - bus_db[b2][2]

############# User Query Service API Layer ##############

def answerUser(userID):
    user_awaits(userID)
    res = get_waiting_time(userID)
    bus1 = {"id": res[0], "time": res[1], "seats": res[2]}
    bus2 = {"id": res[3], "time": res[4], "seats": res[5]}
    return jsonify({"busses": [bus1, bus2]})

# Trigerred when user chooses buses, updates bus list and declares user as awaiting
# Returns bus information on latest two buses
@app.route("/user/<userID>/bus/<busRoute>")
def user_selects_bus(userID, busRoute):
    update_intentions(userID, [busRoute])
    return answerUser(userID)

@app.route("/user/<userID>/bus/<busRoute>/delete")
def user_unselects_bus(userID, busRoute):
    user_db[userID][0].pop(busRoute, None)
    users_waiting.pop(busRoute, None)
    return answerUser(userID)
############# Driver Query Service ##############
def bus_init(busID, route):
    if not bus_db.get(busID, False):
        bus_db[busID] = [route,0,0,0]
    if not active_busses.get(bus_db[busID][0], False):
        active_busses[bus_db[busID][0]] = []
    active_busses[bus_db[busID][0]].append(busID)
    bus_db[busID][1] = 0
    bus_db[busID][2] = 16

def bus_update_location(busID, location):
    bus_locations[busID] = location

@app.route("/bus/<busID>/seats/<num_taken_seats>")
def update_num_seats(busID, num_taken_seats):
    bus_db[busID][2] = int(num_taken_seats)
    return "ok"

@app.route("/bus/<busID>/go")
def go(busID):
    # users_waiting_on_bus[
    expected_num_of_clients = 0
    route = bus_db[busID][0]
    # If no one waiting
    if users_waiting.get(route) == None:
        return "{}".format(False)

    for bus_stop in users_waiting[route]:
        expected_num_of_clients += users_waiting[route][bus_stop]

    for busID in active_busses[route]:
        if bus_db[busID][1] != 0:
            expected_num_of_clients -= 16 - bus_db[busID][2]
    return "{}".format((expected_num_of_clients + bus_db[busID][2]) >= 16)

if __name__ == '__main__':
    user_init('632', 1) # user 632 at stop 1
    bus_init('420', "11m") # bus 420 at stop 0, with 0 empty seats
    bus_init('931', "12")
    bus_init('196', "11")
    port = int(os.environ.get("PORT", 5000))
    app.run(host='0.0.0.0', port=port)
