import json
import requests
from requests.auth import HTTPBasicAuth

#username = "admin"
#password = "123"

#r = requests.get('http://localhost:8080/werewolf/players/add/nadia',auth=HTTPBasicAuth(username, password))


#print(r.text)

#payload = {'lat': 42.3, 'lng': 30.2}
payload = {'some': 'data'}

r = requests.post("http://localhost:8080/werewolf/players/add/nadia", data=json.dumps(payload))

print(r.text)