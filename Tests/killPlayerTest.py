import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

url = 'http://powerful-depths-2851.herokuapp.com/players/kill/elaine/jake'
#url = 'http://localhost:8080/werewolf/players/add/elaine'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

r = requests.post(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)

