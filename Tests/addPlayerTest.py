import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

#url = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia'
#url2 = 'http://powerful-depths-2851.herokuapp.com/players/add/elaine'
#url3 = 'http://powerful-depths-2851.herokuapp.com/players/add/jake'
url4 = 'http://powerful-depths-2851.herokuapp.com/players/add/joshua/true/31/29'
#url = 'http://localhost:8080/werewolf/players/add/elaine'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

#r = requests.post(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
#r = requests.post(url2, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
#r = requests.post(url3, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.post(url4, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
