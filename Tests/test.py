import json
import requests
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

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

print("Welcome to the testing script for the Werewolf game! \n")
print("There is probably some old data left over from the last game. Let's check: \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Well that just won't do. Let's clear everything and start a new game \n")
r = requests.post(restartGameUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print("Let's check the game's database again: \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("That's much better. Now let's add some players!\n")
r = requests.post(addNadiaUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.post(addElaineUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.post(addJakeUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.post(addJoshuaUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("The key points in that are: Elaine and Joshua are werewolves, Nadia is very close to Elaine, and Jake is far away from all of them. \n")
print("Now let's have Elaine kill Nadia...but it is day \n")
r = requests.post(killNadiaUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print("Despite being close, she shouldn't have suceeded since it is day. Let's fetch the list of all alive players to check \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print("Verification that it is not nightime- when we ask is it is night, we get \n")
print(r.text)
print("That's no fun. Let's set the game to night! \n")
r = requests.post(setNightUrl, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
print("Now when we ask if it is night, we should get true \n")
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print(r.text)


