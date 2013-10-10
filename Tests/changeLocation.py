import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

url = 'http://powerful-depths-2851.herokuapp.com/players/location/372199381/36/37'

#url='http://localhost:8080/werewolf/players/location/372199381/-35.2/35'


headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":39.2,"lng":25})

r = requests.post(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
