from bs4 import BeautifulSoup
import requests
url = "https://www.yorku.ca/foodservices/dining-directory/"
response = requests.get(url)

soup = BeautifulSoup(response.text, 'html.parser')

restaurantName = soup.find_all('h2')
card_divs = soup.find_all('div', class_='card border-0 mb-5')

restaurantlist = []
for names in restaurantName:
     restaurantlist.append(names.text)

tempdiv = []
for card in card_divs:
    restaurant = card.find_all('div', class_='row mb-3')
    tempdiv.append(restaurant)


info2 = []
for x in range (len(tempdiv) - 1):
    info = []
    for z in range(len(tempdiv[x])):
        info.append(tempdiv[x][z].text.strip())
    info2.append(info)
    

f = open('restaurants.txt', 'w')


for x in range(len(restaurantlist)):
    f.write(restaurantlist[x] + "   ")
    for z in range(len(info2[x])):
        f.write(info2[x][z] + "     ")
    f.write("\n")

