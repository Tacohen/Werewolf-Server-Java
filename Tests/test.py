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
