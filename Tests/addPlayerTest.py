import json
import requests
from requests.auth import HTTPBasicAuth

url = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia'

headers = {'Content-type': 'application/json'}

data = json.dumps({"lat":35,"lng":45})

r = requests.post(url, data=data, headers=headers)
