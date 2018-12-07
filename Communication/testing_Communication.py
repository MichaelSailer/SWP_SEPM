import json
import MySQLdb
from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/sendToRasp', methods=['POST'])
def read_from_app():
    abmessung = json.loads()

    return json.dumps(abmessung)

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')


def execution(sql):
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    cur = db.cursor()
    cur.execute(sql)
    db.commit()
    cur.close()
    db.close()


