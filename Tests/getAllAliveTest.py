import requests
import json
from requests.auth import HTTPBasicAuth

username = "admin"
password = "123"


url = 'http://powerful-depths-2851.herokuapp.com/players/alive'
#url = 'http://localhost:8080/werewolf/players/alive'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

r = requests.get(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)

print(r.text)
