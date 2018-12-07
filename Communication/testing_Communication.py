# Here are the main imports for the programm
import json
import MySQLdb
from flask import Flask, jsonify, request

app = Flask(__name__)
#Here is the route for sending data for the mirror to the Raspberry Pi
@app.route('/sendToRasp', methods=['POST'])
def read_from_app():
    #First of all the values from the table will be  deleted
    Droppen_Werte()
    #Here will read the json body from the request
    conten = request.json
    #Here are the defining variables, so that is clear which json data is which variable
    abmessung = conten['groese']
    postion = conten['postion']
    objekte = conten['objekte']
    #Here is the splitting part for two variables    
    hohe = abmessung.split(',')[0]
    breite = abmessung.split(',')[1]
    xpos = postion.split(',')[0]
    ypos = postion.split(',')[1]
    #Here is the SQL Query for the database
    sql="INSERT INTO Werte(xpos,ypos,hohe,breite,objekte) VALUES(%s,%s,%s,%s,%s)"%("'"+xpos+"'","'"+ypos+"'","'"+hohe+"'","'"+breite+"'","'"+objekte+"'")
    #Calling the method to send the data to the database
    execution(sql)
    #To be safe we send a succes back to the client
    return 'success'
#Here is the route for sending data for the ToDo-List to the Raspberry Pi
@app.route('/sendTodo', methods=['POST'])
def send_ToD_Rasp():

    conten = request.json

    description = conten['description']
    status = conten['status']

    result=[]
    result.append(description)
    result.append(status)

    print(description)
    print(status)

    sql="INSERT INTO ToDo(status,description) VALUES(%s,%s)"%("'"+status+"'","'"+description+"'")
    
    execution(sql)
    
    return 'success'
#Here is the route for sending the data for the mirro to the Tablet
@app.route('/sendToTablet', methods=['GET'])
def send_to_tablet():
    #Defining an Array to safe the data
    results = []
    #Starting a connection to the database
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    #Open the cursor to get through the data
    cur = db.cursor()
    #Here is the SQL Query which will be executed
    sql = "SELECT xpos,ypos,hohe,breite,objekte FROM Werte"
    #Here will be the SQL executed
    cur.execute(sql)
    #Reading through the data abd safe it into some variables 
    for row in cur.fetchall():
        x=str(row[0])
        y=str(row[1])
        h=str(row[2])
        b=str(row[3])
        objekte=str(row[4]) 
        #The variables will be saved into the Array
        results.append(x.replace("L",""))
        results.append(y.replace("L",""))
        results.append(h.replace("L",""))
        results.append(b.replace("L",""))
        results.append(objekte.replace("L",""))
    #Closing the cursor and the database to avoid overfloating the server
    cur.close()
    db.close()
    #To be safe we send a succes back to the client
    return 'success'
#Here is the route for sending the data for the ToDo-List to the Tablet
@app.route('/sendTabletToDo', methods=['GET'])
def send_ToDo_to_tablet():
    results = []
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    cur = db.cursor()
    sql = "SELECT description, status FROM ToDo"
    cur.execute(sql)
    for row in cur.fetchall():
        x=str(row[0])
        y=str(row[1])
        

        results.append(x.replace("L",""))
        results.append(y.replace("L",""))

    cur.close()
    db.close()

    return 'success'
#Here will be sending the inserts to the database
def execution(sql):
    #Starting a connection to the databas
    db = MySQLdb.connect("localhost", "Michael", "Kematen", "SmartMirror")
    #Open the cursor to get through the data
    cur = db.cursor()
    #Execute the SQL 
    cur.execute(sql)
    #Comminting into the database to be sure that the data will be in the tables
    db.commit()
    #Closing the cursor and the database to avoid overfloating the server
    cur.close()
    db.close()
#Here is the method to tuncate the values from the Werte table
def Droppen_Werte():
    sql ="TRUNCATE TABLE Werte"
    execution(sql)
#Here is the method to tuncate the values from the ToDo table
def Droppen_ToDo():
    sql ="TRUNCATE TABLE ToDo"
    execution(sql)

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')