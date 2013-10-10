import requests
from requests.auth import HTTPBasicAuth

username = "admin"
password = "123"

url = 'http://localhost:8080/werewolf/players/alive/123'

r = requests.get(url,auth=HTTPBasicAuth(username, password))


print(r.text)
