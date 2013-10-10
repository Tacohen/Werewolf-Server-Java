import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

url = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia'
url2 = 'http://powerful-depths-2851.herokuapp.com/players/add/elaine'
url3 = 'http://powerful-depths-2851.herokuapp.com/players/add/jake'
#url = 'http://localhost:8080/werewolf/players/add/elaine'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

r = requests.put(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.put(url2, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
r = requests.put(url3, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
