from flask import Flask, request
from collections import Counter

app = Flask(__name__)

users_waiting_on_bus = Counter()
empty_seat_on_bus = Counter()

@app.route("/user", methods=["POST"])
def userAwaits():
    busIds = request.get_json()["busIds"]
    for busId in busIds:
        users_waiting_on_bus[busId] += 1
    print(users_waiting_on_bus)
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
    port = int(os.environ.get("PORT", 5000))
    app.run(host='0.0.0.0', port=port)