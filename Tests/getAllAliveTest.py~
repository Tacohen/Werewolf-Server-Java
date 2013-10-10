import requests
from requests.auth import HTTPBasicAuth

username = "admin"
password = "123"

r = requests.get('http://localhost:8080/werewolf/players/alive',auth=HTTPBasicAuth(username, password))


print(r.text)
