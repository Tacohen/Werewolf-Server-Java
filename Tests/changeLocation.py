import requests
import json

auth = ("admin","123")

url='http://localhost:8080/werewolf/players/123/location'

params = {"lat":35,"lng":20}

r = requests.post(url,data=params,auth=auth)

print(r.text)