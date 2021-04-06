# Git

Tässä lyhyt ohje siihen, mikäli et saa ajettua omaa koodiasi tai testit eivät mene sen kanssa läpi. 

## Esivalmistelut

Ota aina ensin pulli Githubista `git pull origin develop` ja tee uusi haara, jossa ryhdyt kirjoittamaan omaa koodiasi `git checkout -b feature1`. 

Kun olet tehnyt muutoksia projektiin, testaa aina, että ohjelma käynnistyy ja mielellään myös, että kirjoitetut testit menevät läpi. Mikäli ohjelma ei käynnisty, testit eivät mene läpi tai toiminnassa on jotain kummaa, älä pushaa develop-haaraan, vaan tee seuraavat toimenpiteet!

## Viallisen haaran pushaaminen

Tuttuun tapaan aloita näin: 

```
git add .
git commit -m "aloitettu feature1:n kehittäminen"
```

Tässä kohtaa älä kuitenkaan vaihda *develop*-haaraan, vaan pysy feature1 haarassasi ja pushaa seuraavalla tavalla:

```
git push origin feature1
```

Nyt Githubiin tulee uusi haara, jossa kirjoittamasi koodi on kaikkien saatavilla ja tallessa, jotta sen kehitystä voi jatkaa. Se ei kuitenkaan riko develop-haaraa ja aiheuta tilannetta, jossa vikaa on hankala löytää ja koko projekti on rikkinäisessä tilassa. 