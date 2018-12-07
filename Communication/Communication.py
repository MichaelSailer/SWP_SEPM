import json
import MySQLdb
from flask import Flask, jsonify

app = Flask(__name__)



@app.route('/sendToRasp', methods=['POST'])
def read_from_app():
    position = json.request["postion"]
    instanzen = json.request["objekte"]

    position.split(",")
    x = position[0]
    y = position[1]

    abmessung.split(",")
    h = position[0]
    b = position[1]

    sql = "INSERT INTO Werte(xpos,ypos,hohe,breite,objekte) VALUES (%d,%d,%d,%d,%d)" % (x, y, h, b, instanzen)
    execution(sql)


@app.route('/sendToTablet', methods=['GET'])
def send_to_tablet():
    results = []
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    cur = db.cursor()
    sql = "SELECT xpos,ypos,hohe,breite,objekte FROM Werte"
    cur.execute(sql)
    for row in cur.fetchall():
        x=str(row[0])
        y=str(row[1])
        h=str(row[2])
        b=str(row[3])
        objekte=str(row[4])

        results.append(x.replace("L",""))
        results.append(y.replace("L",""))
        results.append(h.replace("L",""))
        results.append(b.replace("L",""))
        results.append(objekte.replace("L",""))

    cur.close()
    db.close()

    return json.dumps(results)



if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')


def execution(sql):
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    cur = db.cursor()
    cur.execute(sql)
    db.commit()
    cur.close()
    db.close()
