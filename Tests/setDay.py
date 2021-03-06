import json
import requests
import time
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

headers = {'Content-type': 'application/json'}

loginData = json.dumps({"username":"admin","password":"123"})
loginTimUrl = "http://powerful-depths-2851.herokuapp.com/users/login"
setDayUrl = 'http://powerful-depths-2851.herokuapp.com/admin/setday'
loginParams = {"username":"admin","password":123,"lat":31,"lng":30}
isNightUrl = 'http://powerful-depths-2851.herokuapp.com/players/isnight'
params = {"playerId":"jake","lat":31,"lng":45}

r = requests.post(loginTimUrl, auth=HTTPBasicAuth(username, password),params=loginParams, headers=headers)
r = requests.post(setDayUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
print("Is it night? \n")
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
