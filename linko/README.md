# TicketGuru

Tiimi: Koskela Ville, Lindholm Sofia, Nikkanen Joni


## Contents

- [Johdanto](#Johdanto)
- [Järjestelmän määrittely](#Järjestelmän-määrittely)
    - [Listaus käyttäjätarinoista](#Listaus-käyttäjätarinoista)
- [Käyttöliittymä](#Käyttöliittymä)
- [Tietokanta](#Tietokanta)
- [Tekninen kuvaus](#Tekninen-kuvaus) 
    - [REST-API Dokumentaatio](#REST-API-Dokumentaatio)
    - [Autentisointi](#Autentisointi)
- [Testaus](#Testaus)
- [Asennustiedot](#Asennustiedot)


## Johdanto

Asiakasyritys on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteissään. Toistaiseksi järjestelmää käyttävät vain yrityksen työntekijät, joiden tulee pystyä lisäämään järjestelmään tapahtumia, myydä niihin lippuja ja tulostaa liput asiakkaille. Asiakas varaa mahdollisuuden verkkokauppaan tulevaisuudessa. 
Taustajärjestelmän toteuttamiseen käytetään Javaa ja Spring Bootia. Frontend toteutetaan selainpohjaisena ja se toteutetaan Reactilla. Tietokantana käytetään Postgresql-tietokantaa. 

Koska järjestelmää käyttävät työntekijät lipunmyyntipisteissä, ei käyttöliittymän tarvitse toimia muilla kuin tietokoneen suurella monitorilla. Mahdollinen myöhempi verkkokauppa mietitään sitten erikseen. 

Kooditietokanta (codebase) säilytetään Githubissa, mistä on helppo tehdä pipeline esim. Docker Hubiin. Tuotannossa järjestelmää ajetaan Dockerin kautta, jolloin käyttöönotto, päivittäminen ja skaalaaminen on yksinkertaista ja nopeaa. 

## Järjestelmän määrittely

Järjestelmä määritellään tässä vaiheessa projektia käyttäjätarinoiden pohjalta. Käyttäjätarinat ovat tallennettuna projektiryhmän Scrum-boardille, ja lisäksi listaus niistä löytyy alta. Lisäksi järjestelmämäärittely on aloitettu käyttöliittymämallinnuksen ensimmäisten versioiden avulla. Tämän hetkiset käyttöliittymämallinnukset löytyvät kappaleesta Käyttöliittymä.

Käyttäjätarinoita lisätään ja muokataan projektin edetessä tarpeen mukaan. Lisäksi käyttäjätarinoista muodostetaan tarvittaessa epicejä. Käyttäjätarinoiden tarkoitus on ymmärtää järjestelmän käyttötarkoitus sekä tarpeet, jonka avulla tekninen määrittely muodostuu. Käyttäjätarinoissa kuvattuja järjestelmävaatimuksille luodaan projektin edetessä priorisointijärjestys, jota voi tarvittaessa muokata. Priorisointijärjestys selkenee kun lopputuotteesta keskustellaan tarkemmin vielä tuoteomistajan kanssa.

Järjestelmän määrittelyyn tullaan palaamaan vielä tarkemmin myöhemmin. Tässä vaiheessa projektia ei olla vielä keskusteltu tuoteomistajan kanssa, vaan saatu ainoastaan kirjallinen kuvaus projektista. Projektiryhmän päämäärä on saada vielä tarkennuksia järjestelmävaatimuksiin.

### Listaus käyttäjätarinoista:

| ID  | Rooli  |  Tarina |
|-----|--------|---------|
| 1   | Lipunmyyjä  | Myyjänä toivon, että pystyn tulostamaan myyntipisteellä asiakkaalle lipun, jotta hän pääsee haluamaansa tapahtumaan. |
| 2   | Tapahtumajärjestäjä | Tapahtumanjärjestäjänä haluan, että lipunmyyntimäärät saa tietoonsa, jotta saan seurattua kävijämääriä. |
| 3   | Lipunmyyjä  | Lipunmyyjänä haluan, että voin hakea tapahtumia nimillä, koska asiakkaat kertovat yleensä keikan tai tapahtuman nimen lippua ostaessaan. |
| 4   | Myymäläpäällikkö | Myymäläpäällikkönä toivon, että pystyn raportoimaan tapahtumajärjestäjälle mahdollisimman yksiselitteisesti lipunmyyntiä ja seuraamaan kuinka paljon lippuja kuhunkin tapahtumaan on vielä jäljellä. |
| 5   | Ostaja1 | Ostajana haluan, että lipunmyyjä löytää haluamani tapahtuman helposti, ja lipun saa nopeasti mukaan. Haluan myös, että tapahtumaan pystyy ostamaan useamman lipun. |
| 6    | Lipun tarkastaja | Lipun tarkastajana haluan, että lipussa on toimiva koodi, joka on helposti luettavissa, jotta kanssakäynti tapahtumakävijöiden kanssa on mahdollisimman nopeaa. Haluan myös, että lippu on helposti tunnistettavissa. |
| 7   | Lipunmyyjä | Lipunmyyjänä toivon, että eri alennusryhmien liput ovat helposti löydettävissä, jotta niidenkin myyminen on mutkatonta. |
| 8   | Lippunmyyjä | Lipunmyyjänä toivon, että näen yhteenvedon ostotapahtumasta ennen lippujen tulostamista, jotta voin tarkistaa tilauksen sisällön ennen vahvistamista. |
| 9 | Lipunmyyjä | Lipunmyyjänä haluan, että näen paljonko lippuja tapahtumaan on jäljellä, jotta määrä on helposti kerrottavissa eteenpäin. |
| 10  | Lipunmyyjä | Lipunmyyjänä haluan, että suosittuja tapahtumia pystyy selaamaan, koska se helpottaisi tapahtumien etsintää. |
| 11 | Lipunmyyjä | Lipunmyyjänä haluan, että pystyn hakemaan tapahtumia päivämäärällä, koska asiakas haluaa joskus etsiä tapahtumia tietyllä päivämäärällä. |
| 12 | Myymäläpäällikkö | Myymäläpäällikkönä toivon, että pystyn tulostamaan tapahtumajärjestäjälle raportteja, koska tapahtumajärjestäjät haluavat seurata kävijämääriä. |

## Käyttöliittymä

### Etusivu & myy lippuja
![Import](Docs/Kuvat/Kayttoliittyma/Sivu1.png "Import")
### Luo tapahtuma
![Import](Docs/Kuvat/Kayttoliittyma/Sivu2.png "Import")
### Muokkaa tapahtumaa & myyntiraportti
![Import](Docs/Kuvat/Kayttoliittyma/Sivu3.png "Import")

## Tietokanta

Ohjelmistoa varten on suunniteltu relaatiotietokanta.
Tietokannan relaatiokaavio löytyy täältä: [Luokkakaavio_updated.png](Docs/Luokkakaavio_updated.png)

Lisäksi käyttäjien autorisointia varten on olemassa toinen taulurakenne, joka kuvataan 

Alla vielä tietohakemisto eri luokkien attribuuteista.

### Event

*Tähän tauluun tallennetaan kaikki tapahtumat*

| Kenttä | Tyyppi | Kuvaus | 
| :----  | :---- | :-----  |
| eventID     | int PK | Tapahtuman Id |
| city| FK| Kunnan nimi|
| event| string| Tapahtuman nimi|
| eventPlace| string| Tapahtumapaikan nimi|
| capacity| int| maksimilippumäärä|
| description| string| vapaavalintainen kuvaus|
| datetime| LocalDateTime| päivämäärä ja kellonaika|

### City

*Tähän tauluun tallennetaan kaikki kunnat. Tällä hetkellä tarkin sijaintimääritys, tarkennetaan tarvittaessa (esim. postinumerotasolle).*

| Kenttä | Tyyppi | Kuvaus | 
| :----  | :---- | :-----  |
| cityID     | int PK | Kunnan Id |
| city | string | Kunnan nimi |


### Order

*Tähän tauluun tallennetaan kaikki tapahtuneet kaupat*

| Kenttä | Tyyppi | Kuvaus | 
| :----  | :---- | :-----  |
| orderID     | int PK | Ostotapahtuman Id |
| timestamp | timestamp | Ostotapahtuman päivä & aika |

### TicketOrder

*Tämä taulu purkaa 'monen suhde moneen' -relaatiot lippu- ja ostotaulujen välistä. Tässä taulussa pidetään tieto ostetuista lipuista.*

| Kenttä | Tyyppi | Kuvaus |
| :---- | :---- | :----- |
| id   | int PK | Lippumyynnin ID |
| orderID | int FK | Viittaus [ostotapahtumaan](#Order), jossa tämä lippu on ostettu |
| ticketID | int FK | Viittaus [lippuun](#Ticket), jotta tiedetään millainen lippu tässä ostotapahtumassa on ostettu | 
| price | double | Tallettaa tiedon, mihin hintaan juuri tämä kyseinen lippu on myyty |
| code | varchar | Koodi, jolla lippu tunnistetaan ovella tarkistettaessa |
| used | boolean | Tieto siitä, onko lippu käytetty |
| usedDate | timestamp | Päivämäärä ja kellonaika, jolloin lippu on käytetty |

### Ticket

*Tähän tauluun tallennetaan kaikki liput ja niiden tiedot, jotka ovat tiettyyn tapahtumaan saatavilla.*

| Kenttä | Tyyppi | Kuvaus |
| :---- | :---- | :----- |
| id   | int PK | Lippu ID |
| ticketTypeID | int FK | Viittaus [lipputyyppiin](#TicketType), joka kertoo minkä tyyppinen lippu on kyseessä |
| price | double | Lipulle määritettävä hinta | 
| eventID | int FK | Viittaus [tapahtumaan](#Event), johon tämä lippu on ostettu |
| description | string | Kuvaus lipusta.  |

### TicketType

*Tässä taulussa listataan kaikki lipputyypit, jotka ovat tapahtumiin saatavilla*

| Kenttä | Tyyppi | Kuvaus |
| :---- | :---- | :----- |
| id   | int PK | Lipputyyppi ID |
| ticketType | string | Lippityypin nimi, jolla se on yksilöitävissä. Esimerkiksi opiskelija|


## Tekninen kuvaus

### REST-API Dokumentaatio


HTTP-protokollalla toteutetussa REST API:ssa pyyntötyyppi määrittä resurssille tehtävän operaation. Kuvaukset ohjelmiston pyyntötyypeistä löytyy seuraavasta linkistä:

[Tarkempi kuvaus](Docs/CRUD_rajapinta_kuvaus.md) Sisältöesimerkit, endpointit, paluukoodit, virheviestit

### Autentisointi

Projektissa käyttäjien tunnistamiseksi käytetään Spring Bootin Basic Authorization-suojausta. Ohjelmaa ei pysty käyttämään, ellei käyttäjällä ole käytössä tarvittavia, määriteltyjä käyttöoikeuksia. Ohjelmistossa on tällä hetkellä kahden tasoisia käyttäjiä: Ylläpitäjiä (admin) ja peruskäyttäjiä (user). Käyttäjätasoja tarkennetaan tarvittaessa.

Käyttäjien tiedot ja käyttöoikeudet tallennetaan tietokantaan ja ohjelma tarkistaa oikeudet pyyntöjä toteuttaessa. Käyttäjien salasanoista talletetaan tiiviste (hash), joka kryptografisin menetelmin voidaan tarkistaa, mutta josta itse salasanaa ei saada selville.

Kuvaus käyttäjätietokannasta, taulut User_Entity ja User_Authorization:


![Import](Docs/Kuvat/UsersDiagram.png "Import")


## Testaus

Testausta on toteutettu kolmella tasolla:
- Yksikkötestaus
- Integraatiotestaus
- End-to-end -testaus

#### Yksikkötestaus

Yksikkötestauksessa on käytetty JUnitia ja testiautomatiikkaa. Testejä on kirjoitettu controllereille, jolloin on testattu
endpointtien oikeanlaista toimintaa. Lisäksi on tehty tehty testejä repository-rajapinnoille, jotta on voitu varmistaa niiden halutunkaltainen toiminta. Näiden lisäksi jokaiselle controllerille on määritelty smoke-testit. Valitettavasti yksikkötestien kattavuus ei ole 100 % ja osa controllereista on vailla yksikkötestejä. 

Automaattisten testien tulokset ovat nähtävillä Github Actionsissa. 

#### Integraatiotestaus

Integraatiotestit on kirjoitettu Pythonilla ja niissä käytetään Pytestiä. Integraatiotesteissä on kirjoitettu kokonaisia tapahtumia API-kyselyiden sarjana, jolloin voidaan vahvistaa onnistunut lipunmyyntitapahtuma rajapintaa vasten tai onnistunut lipun tarkistaminen ovella. Integraatiotestien kattavuus ei kuitenkaan ole kovin hyvä. Integraatiotestit ajetaan manuaalisesti aina testipalvelimen päivityksen yhteydessä ja näin varmistetaan, että päivitysten myötä toimivuus ei ole heikentynyt. 

#### End-to-end -testaus

End-to-end -testauksessa on testattu manuaalisesti käyttöliittymää ja varmistettu, että käyttöliittymä toimii halutulla tavalla, jolloin voidaan olettaa myös käyttöliittymän takana olevan sovelluslogiikan (backend, tietokanta yms.) toimivan tarkoitetulla tavalla. 

#### Tunnetut ongelmat

Tällä hetkellä ei tunnettuja käyttöä haittaavia ongelmia. 

## Asennustiedot

### Vaatimukset

- Kohdejärjestelmässä tulee olla asennettuna Dockerin riittävän uusi versio.
- Kohdejärjestelmässä tulee olla asennettuna Docker Composen riittävän uusi versio. 
- Tässä ohjeessa ohjelma noudetaan koneelle käyttämättä Gitiä. Myös muut tavat siirtää ohjelmakoodi kohdejärjestelmään ovat mahdollisia, mutta niitä ei käsitellä tässä ohjeessa. 

Järjestelmän asentaminen ja ajaminen tapahtuu Docker-konttien avulla. Helpoin tapa ladata ohjelman koodi asennusta varten on kloonata sovelluksen Git-repository. 

### Ohjeet

Suorita asentamista varten seuraavat komennot. 

```
git clone https://github.com/nikkanenjoni/ohjelmistoprojekti.git
cd ohjelmistoprojekti/linko
```

**Valinnaiset toimenpiteet tässä kohdassa**

Jos haluat valita, mihin tietokanta tallennetaan, luo nyt symlink tuohon kohdekansioon seuraavalla komennolla:
```
ln -s /kohde/hakemisto/tahan DB
```
Tämän jälkeen voit vielä valita, missä portissa haluat palvelinta ajaa. Tämä tapahtuu muokkaamalla docker-compose.yml-tiedostoa ja vaihtamalla sinne rivillä `80:8080` vasemmanpuoleisen porttinumeron paikalle haluttu portti. 

**Yleiset ohjeet jatkuvat**

Riippumatta siitä, teitkö valinnaiset toimenpiteet, jatka seuraavien ohjeiden mukaan: 
```
docker-compose up -d
```

Odota ohjelman kääntyminen. Kun ohjelma on käännetty, docker käynnistää sen automaattisesti. Ohjelma on käynnistynyt, kun palaat takaisin komentoriville ja voit kirjoittaa uusia komentoja. Tämän jälkeen odota tarvittaessa vielä n. 1-2 minuuttia, jotta järjestelmä yhdistää tietokantaan ja käynnistyy kokonaan. 

Palvelimen tulisi vastata portissa 80 tai valitsemassasi portissa, mikäli vaihdoit toisen porttinumeron oletusportin tilalle. Docker käynnistää palvelimen jatkossa automaattisesti järjestelmän uudelleenkäynnistyksen yhteydessä tai palvelinsovelluksen kaatuessa. 

