from bs4 import BeautifulSoup
import requests
url = "https://www.yorku.ca/foodservices/dining-directory/"
response = requests.get(url)

soup = BeautifulSoup(response.text, 'html.parser')

name = soup.find_all('h2')

resturantName = []
for names in name:
    myname = {"name": names.text}
    resturantName.append(myname)


location = soup.find_all("div", class_='col-11')


f = open("text.txt", "w")
i = 0
for locations in location:
    f.write(locations.text.strip() + "\n")
    i+=1
