from flask import Flask
from collections import Counter

app = Flask(__name__)

users_waiting_on_bus = Counter()
empty_seat_on_bus = Counter()

@app.route("/user/<busId>")
def userAwaits(busIds):
    [users_waiting_on_bus[busId] += 1 for busId in busIds]
    return "Success"

@app.route("/emptySeats/<busId>/<numSeats>")
def updateBusSeats(busId, numSeats):
    empty_seat_on_bus[busId] += int(numSeats)
    return "Success"

@app.route("/start/<busId>")
def busStart(busId):
    # Returns true if people waiting >= empty seats
    return "{}".format(users_waiting_on_bus[busId] >= empty_seat_on_bus[busId])

if __name__ == '__main__':
   app.run()