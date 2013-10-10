import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

#url = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia'
url = 'http://localhost:8080/werewolf/players/add/elaine'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

r = requests.put(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
