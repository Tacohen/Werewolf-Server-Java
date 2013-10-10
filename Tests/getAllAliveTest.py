import requests
from requests.auth import HTTPBasicAuth

username = "admin"
password = "123"


url = 'http://powerful-depths-2851.herokuapp.com/players/alive'
#url = 'http://localhost:8080/werewolf/players/alive'

r = requests.get(url,auth=HTTPBasicAuth(username, password))


print(r.text)
