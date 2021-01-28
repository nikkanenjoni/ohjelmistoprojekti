# Ohjelmistoprojekti
Ohjelmistoprojekti kurssin repository

## Sisältö

- [Scrum-syklin yleiskuvaus](#Scrym-syklin-yleiskuvaus)
- [Sprintit](#Sprintit)
- [Työjonot](#Tyojonot)
- [Roolit](#Roolit)
- [Kokoukset](#Kokoukset)
- [Miksi Scrum toimii?](#Miksi-Scrum-toimii)
- [Git](#Git)

## Scrum-syklin yleiskuvaus

## Sprintit

### Aloitus

Sprintti alkaa aina kokouksesta, jossa sovitaan työjonosta ja asetetaan sprintin työjono. Kokouksessa tehdään suunnitelma, miten sprintti toteutetaan ja miten sprintille asetetut tavoitteet saavutetaan. 

Käytännössä tämä tarkoittaa, että katsotaan tuotteen nykyinen tila ja työjono, sekä muut vallitsevat olosuhteet ja sovitaan näiden pohjalta tulevan sprintin työjono suhteessa tiimin käytössäolevaan työvoimaan. 

Kokouksessa pitää saada sovittua tulevan sprintin työjono ja tavoitteet. 

### Kulku

Sprintti kestää yleensä 2-4 viikkoa. Sprintin aikana työjonoa ei muuteta, joten sprinttien pituus tulee katsoa sellaiseksi, että työjonon muuttamista voidaan odottaa seuraavaan sprinttiin asti. 

Sprintin aikana pidetään päivittäisiä 15 minuutin pikakokouksia, joissa katsotaan, mitä kukakin on saanut tehtyä edellisenä päivänä ja mitä aikoo tehdä tänään. 

Sprintin aikana työjonossa määritellyt asiat suunnitellaan, toteutetaan ja testataan. 

### Päätös

Sprintin päätteeksi pidetään kaksi kokousta (Sprint review & Sprint Retrospective). Sprint reviewissa esitellään itse sprintin tuotokset ja Sprint Retrospectivessa keskitytään itse prosessin onnistumiseen. Sprintin tuotoksena voi olla esimerkiksi jokin uusi ominaisuus. Samassa yhteydessä voidaan havainnoida mitä mahdollisuuksia ja rajoituksia kehityksessä on havaittu, jotta näihin voidaan myöhemmissä vaiheissa reagoida entistä paremmin. Kokouksessa myös sovitaan seuraavasta sprintistä.

## Työjonot

Jonossa on featureita, joista tärkein ensimmäisenä, toisiksi tärkein toisena jne. Featureita toteutetaan jonosta tärkein-ensin -periaatteella. 

## Roolit

## Kokoukset

Projektin työvaiheita ja niihin liittyviä avoimia kysymyksiä suunnitellaan tarvittaessa päivittäisissä daily scrumeissa.
Lisäksi järjestetään sprinttien suunnittelukokouksia, ryhmän vastuiden ja kehityksen priorisointijärjestyksen määrittämiseksi.

Sprinttien jälkeen järjestetään sprinttien lopetuskokous, joissa käydään edellinen sprintti läpi (sprint review).Tiimi esittelee, että mitä on saanut sprintissä aikaan, ja tulokset demotaan. Kokouksissa on mukana myös asiakas, eli tuoteomistaja.

Lopetuskokouksen jälkeen pidetään vielä
lyhyt kritiikkipalaveri eli sprint retrospective, jossa arvioidaan kuluneen sprintin onnistumiset ja kehityskohteet.
Tämän tarkoituksena on kehittää tiimin
toimintaa paremmaksi.

## Miksi Scrum toimii? 

## Git

Ota ensin klooni repositorysta omalle koneellesi: 
```
git clone git@github.com:nikkanenjoni/ohjelmistoprojekti.git
``` 

Jos et ole tehnyt SSH-avaimia ja laittanut niitä github-tilillesi, joudut käyttämään https-protokollaa gitin kanssa. Valitettavasti sen tuki ollaan tosin poistamassa, eli suosittelen tekemään avaimet. Http-protokollalla: 
```
https://github.com/nikkanenjoni/ohjelmistoprojekti.git
```

Kun olet kloonannut repositoryn, mene hakemistoon `ohjelmistoprojekti` ja ota itsellesi `develop` branch

`git pull origin develop`

Nyt voit siirtyä develop branchiin komennolla: 

`git checkout develop`

Tee nyt itsellesi uusi haara, jossa alat vääntää omaa featureasi:

`git checkout -b feature1`

Muokkaa tiedostoja, miten haluat viedäksesi projektia eteenpäin. 
Sen jälkeen tallenna muutokset ja mergeä develop-haaraan:

```
git add .
git commit -m "Kerro mitä olet muuttanut (esim. päivitin README-tiedostoa)"
git checkout develop
git merge --no-ff feature1
```
Korvaa `feature1` jollain muulla, mikä on mielestäsi parempi nimi haarallesi. 

Nyt olet tehnyt muutoksia, mutta et ole vielä työntänyt niitä githubiin. Työnnä githubin:

`git push origin develop`

Valmista on!

