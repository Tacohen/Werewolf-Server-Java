import json
import requests
import time
from requests.auth import HTTPBasicAuth


username = "admin"
password = "123"

addNadiaUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/nadia/false/31/45'
addElaineUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/elaine/true/31/45'
addJakeUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/jake/false/29/29'
addJoshuaUrl = 'http://powerful-depths-2851.herokuapp.com/players/add/joshua/true/31/29'
checkAllAliveUrl = 'http://powerful-depths-2851.herokuapp.com/players/alive'
restartGameUrl = 'http://powerful-depths-2851.herokuapp.com/admin/restartGame'
killNadiaUrl = 'http://powerful-depths-2851.herokuapp.com/players/kill/elaine/nadia'
killJakeUrl = 'http://powerful-depths-2851.herokuapp.com/players/kill/elaine/jake'
isNightUrl = 'http://powerful-depths-2851.herokuapp.com/players/isnight'
setNightUrl = 'http://powerful-depths-2851.herokuapp.com/admin/setnight'
#moveJakeUrl = 'http://powerful-depths-2851.herokuapp.com/players/location/jake/31/45'
#moveJakeUrl = 'http://powerful-depths-2851.herokuapp.com/players/location/jake'
moveUrl = 'http://powerful-depths-2851.herokuapp.com/players/location'
setDayUrl = 'http://powerful-depths-2851.herokuapp.com/admin/setday'
voteJoshuaUrl = 'http://powerful-depths-2851.herokuapp.com/players/vote/elaine/joshua'
voteElaineUrl = 'http://powerful-depths-2851.herokuapp.com/players/vote/joshua/elaine'
loginTimUrl = "http://powerful-depths-2851.herokuapp.com/users/login"


addNadiaUrlLocal = 'http://localhost:8080/werewolf/players/add/nadia/false/31/45'
addElaineUrlLocal = 'http://localhost:8080/werewolf/players/add/elaine/true/31/45'
addJakeUrlLocal = 'http://localhost:8080/werewolf/players/add/jake/false/29/29'
addJoshuaUrlLocal = 'http://localhost:8080/werewolf/players/add/joshua/true/31/29'
checkAllAliveUrlLocal = 'http://localhost:8080/werewolf/players/alive'
restartGameUrlLocal = 'http://localhost:8080/werewolf/admin/restartGame'
killNadiaUrlLocal = 'http://localhost:8080/werewolf/players/kill/elaine/nadia'
isNightUrlLocal = 'http://localhost:8080/werewolf/players/isnight'
setNightUrlLocal = 'http://localhost:8080/werewolf/admin/setnight'

headers = {'Content-type': 'application/json'}

#data = json.dumps({"lat":31,"lng":45})
moveJakeParams = {"playerId":"jake","lat":31,"lng":45}
loginData = json.dumps({"username":"Tim","password":"12345"})

print("Welcome to the testing script for the Werewolf game! \n")
print("There is probably some old data left over from the last game. Let's check: \n")
print("Heroku might take 60 seconds or so to start up please be patient... \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print(" \n")
time.sleep(1)
print("Well that just won't do. Let's clear everything and start a new game \n")
r = requests.post(restartGameUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
time.sleep(1)
print("Let's check the game's database again: \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("That's much better. Now let's add some players!\n")
time.sleep(1)
r = requests.post(addNadiaUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
print("Adding Nadia...")
time.sleep(1)
r = requests.post(addElaineUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
time.sleep(1)
print("Adding Elaine...")
r = requests.post(addJakeUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
time.sleep(1)
print("Adding Jake...")
r = requests.post(addJoshuaUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
time.sleep(1)
print("Adding Joshua...")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("The key points in that are: Elaine is a werewolf, Nadia is very close to Elaine, and Jake and Joshua far away from all of them. \n")
time.sleep(1)
print("Notice that is is daytime. The game automatically starts as day to prevent too many easy kills. To verify, we ask if it is nighttime. It should return false \n")
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Now let's have Elaine try to kill Nadia.")
r = requests.post(killNadiaUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
print("Despite being close, she shouldn't have suceeded since it is day. Let's fetch the list of all alive players to check \n")
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
time.sleep(1)
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print("Verification that it is not nightime- when we ask if it is night, we get \n")
print(r.text)
print("That's no fun. Let's set the game to night! \n")
r = requests.post(setNightUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
print("Now when we ask if it is night, we should get true \n")
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
time.sleep(1)
print("Let's have Elaine try again. She should succeed this time \n")
r = requests.post(killNadiaUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("As you can see, Nadia is no longer on the list of living players, since Elaine killed her. Now let's move Jake right next to Elaine (31,45) so she can kill him")
time.sleep(1)
r = requests.post(moveUrl, auth=HTTPBasicAuth(username, password),params=moveJakeParams, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)

time.sleep(1)
print("And now for the kill...")
r = requests.post(killJakeUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Note that Jake is indeed dead \n")
time.sleep(1)
print("Now, let's make it day again, for voting, and verify that it is night \n")
r = requests.post(setDayUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
print("Is it night? We should get false.")
r = requests.get(isNightUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
time.sleep(1)
print("Now that it is day, let's have voting begin! Jake obviously votes for Elaine as the werewolf... \n")
r = requests.post(voteElaineUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("Notice Elaine now has one vote against her, while Joshua has 0. \n")
time.sleep(1)
print("Now Elaine votes for Joshua.. \n")
r = requests.post(voteJoshuaUrl, auth=HTTPBasicAuth(username, password),params=params, headers=headers)
r = requests.get(checkAllAliveUrl,auth=HTTPBasicAuth(username, password))
print(r.text)
print("And now see Joshua has a vote against him as well. \n")
time.sleep(1)
print("This concludes the demonstration of the werewolf program. Hope you enjoyed it!")
#r = requests.post(loginTimUrl, auth=HTTPBasicAuth(username, password),data=loginData, headers=headers)


