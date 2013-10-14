import json
import requests
import time
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

addNadiaUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia/false/31/45'
addElaineUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/elaine/true/31/45'
addJakeUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/jake/false/29/29'
addJoshuaUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/joshua/true/31/29'
checkAllAliveUrl = 'http://powerful-depths-2851.herokuapp.com/players/alive'
restartGameUrl = 'http://powerful-depths-2851.herokuapp.com/admin/restartGame'
killNadiaUrl = 'http://powerful-depths-2851.herokuapp.com/players/kill/elaine/nadia'
isNightUrl = 'http://powerful-depths-2851.herokuapp.com/players/isnight'
setNightUrl = 'http://powerful-depths-2851.herokuapp.com/admin/setnight'


addNadiaUrlLocal = 'http://localhost:8080/werewolf/players/add/nadia/false/31/45'
addElaineUrlLocal = 'http://localhost:8080/werewolf/players/add/elaine/true/31/45'
addJakeUrlLocal = 'http://localhost:8080/werewolf/players/add/jake/false/29/29'
addJoshuaUrlLocal = 'http://localhost:8080/werewolf/players/add/joshua/true/31/29'
checkAllAliveUrlLocal = 'http://localhost:8080/werewolf/players/alive'
restartGameUrlLocal = 'http://localhost:8080/werewolf/admin/restartGame'
killNadiaUrlLocal = 'http://localhost:8080/werewolf/players/kill/elaine/nadia'
isNightUrlLocal = 'http://localhost:8080/werewolf/players/isnight'
setNightUrlLocal = 'http://localhost:8080/werewolf//admin/setnight'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

print("Welcome to the testing script for the Werewolf game! \n")
print("There is probably some old data left over from the last game. Let's check: \n")
print("Heroku might take 60 seconds or so to start up please be patient... \n")
r = requests.get(checkAllAliveUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print(" \n")
time.sleep(1)
print("Well that just won't do. Let's clear everything and start a new game \n")
r = requests.post(restartGameUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
time.sleep(1)
print("Let's check the game's database again: \n")
r = requests.get(checkAllAliveUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print("That's much better. Now let's add some players!\n")
time.sleep(1)
r = requests.post(addNadiaUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print(".")
time.sleep(1)
r = requests.post(addElaineUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
time.sleep(1)
print(".")
r = requests.post(addJakeUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
time.sleep(1)
print(".")
#r = requests.post(addJoshuaUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
#time.sleep(2)
#print(".")
r = requests.get(checkAllAliveUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print("The key points in that are: Elaine is a werewolf, Nadia is very close to Elaine, and Jake is far away from all of them. \n")

print("Notice that is is daytime. The game automatically starts as day to prevent too many easy kills. To verify, we ask if it is nighttime. It should return false \n")
r = requests.get(isNightUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Now let's have Elaine try to kill Nadia.")
r = requests.post(killNadiaUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print("Despite being close, she shouldn't have suceeded since it is day. Let's fetch the list of all alive players to check \n")
r = requests.get(checkAllAliveUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
r = requests.get(isNightUrlLocal,auth=HTTPBasicAuth(username, password))
print("Verification that it is not nightime- when we ask if it is night, we get \n")
print(r.text)
print("That's no fun. Let's set the game to night! \n")
r = requests.post(setNightUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print("Now when we ask if it is night, we should get true \n")
r = requests.get(isNightUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Let's have Elaine try again. She should succeed this time \n")
r = requests.post(killNadiaUrlLocal, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.get(checkAllAliveUrlLocal,auth=HTTPBasicAuth(username, password))
print(r.text)
print("As you can see, Nadia is no longer on the list of living players, since Elaine killed her. Now let's move Jake right next to Elaine so she can kill him")
print("This is unfinished, please come back later...")

