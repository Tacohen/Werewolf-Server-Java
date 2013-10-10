import json
import requests
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

url = 'http://powerful-depths-2851.herokuapp.com/players/location/372199381/32/37'

#url='http://localhost:8080/werewolf/players/location/372199381/-35.2/35'


headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":-35.2,"lng":35})

r = requests.put(url, auth=HTTPBasicAuth(username, password),data=data, headers=headers)
