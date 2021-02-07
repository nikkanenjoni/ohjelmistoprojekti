# TicketGuru

Tiimi: Koskela Ville, Lindholm Sofia, Nikkanen Joni

## Johdanto

Asiakasyritys on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteissään. Toistaiseksi järjestelmää käyttävät vain yrityksen työntekijät, joiden tulee pystyä lisäämään järjestelmään tapahtumia, myydä niihin lippuja ja tulostaa liput asiakkaille. Asiakas varaa mahdollisuuden verkkokauppaan tulevaisuudessa. 
Taustajärjestelmän toteuttamiseen käytetään Javaa ja Spring Frameworkkia. Frontend toteutetaan selainpohjaisena ja sen toteutetaan puhtaasti HTML5 / ES6 -pohjaisena. (**vai Angular?**) Tietokanta toteutetaan MariaDB:n avulla. 

Koska järjestelmää käyttävät työntekijät lipunmyyntipisteissä, ei käyttöliittymän tarvitse toimia muilla kuin tietokoneen suurella monitorilla. Mahdollinen myöhempi verkkokauppa mietitään sitten erikseen. 

Kooditietokanta (codebase) säilytetään Githubissa, mistä on helppo tehdä pipeline esim. Docker Hubiin. Tuotannossa järjestelmää ajetaan Dockerin kautta, jolloin käyttöönotto, päivittäminen ja skaalaaminen on yksinkertaista ja nopeaa. 

## Järjestelmän määrittely

Järjestelmä määritellään tässä vaiheessa projektia käyttäjätarinoiden pohjalta. Käyttäjätarinat ovat tallennettuna projektiryhmän Scrum-boardille, ja lisäksi listaus niistä löytyy alta. Lisäksi järjestelmämäärittely on aloitettu käyttöliittymämallinnuksen ensimmäisten versioiden avulla. Tämän hetkiset käyttöliittymämallinnukset löytyvät kappaleesta Käyttöliittymä.

Käyttäjätarinoita lisätään ja muokataan projektin edetessä tarpeen mukaan. Lisäksi käyttäjätarinoista muodostetaan tarvittaessa epicejä. Käyttäjätarinoiden tarkoitus on ymmärtää järjestelmän käyttötarkoitus sekä tarpeet, jonka avulla tekninen määrittely muodostuu. Käyttäjätarinoissa kuvattuja järjestelmävaatimuksille luodaan projektin edetessä priorisointijärjestys, jota voi tarvittaessa muokata. Priorisointijärjestys selkenee kun lopputuotteesta keskustellaan tarkemmin vielä tuoteomistajan kanssa.

Järjestelmän määrittelyyn tullaan palaamaan vielä tarkemmin myöhemmin. Tässä vaiheessa projektia ei olla vielä keskusteltu tuoteomistajan kanssa, vaan saatu ainoastaan kirjallinen kuvaus projektista. Projektiryhmän päämäärä on saada vielä tarkennuksia järjestelmävaatimuksiin.

### Listaus käyttäjätarinoista:

| ID  | Rooli  |  Tarina |
|-----|--------|---------|
| 1   | Myyjä  | Myyjänä toivon, että pystyn tulostamaan myyntipisteellä asiakkaalle lipun, jotta hän pääsee haluamaansa tapahtumaan. |
| 2   | Tapahtumajärjestäjä | Tapahtumanjärjestäjänä haluan, että lipunmyyntimäärät saa tietoonsa, jotta saan seurattua kävijämääriä. |
| 3   | Myyjä  | Lipunmyyjänä haluan, että voin hakea tapahtumia nimillä, koska asiakkaat kertovat yleensä keikan tai tapahtuman nimen lippua ostaessaan. |
| 4   | Myymäläpäällikkö | Myymäläpäällikkönä toivon, että pystyn raportoimaan tapahtumajärjestäjälle mahdollisimman yksiselitteisesti lipunmyyntiä ja seuraamaan kuinka paljon lippuja kuhunkin tapahtumaan on vielä jäljellä. |
| 5   | Ostaja1 | Ostajana haluan, että lipunmyyjä löytää haluamani tapahtuman helposti, ja lipun saa nopeasti mukaan. Haluan myös, että tapahtumaan pystyy ostamaan useamman lipun. |
| 6    | Lipun tarkastaja | Lipun tarkastajana haluan, että lipussa on toimiva koodi, joka on helposti luettavissa, jotta kanssakäynti tapahtumakävijöiden kanssa on mahdollisimman nopeaa. Haluan myös, että lippu on helposti tunnistettavissa. |

## Käyttöliittymä

### Etusivu & myy lippuja
![Import](Kayttoliittyma/Sivu1.png "Import")
### Luo tapahtuma
![Import](Kayttoliittyma/Sivu2.png "Import")
### Muokkaa tapahtumaa & myyntiraportti
![Import](Kayttoliittyma/Sivu3.png "Import")

## Tietokanta

Järjestelmään säilöttävä ja siinä käsiteltävät tiedot ja niiden väliset suhteet
kuvataan käsitekaaviolla. Käsitemalliin sisältyy myös taulujen välisten viiteyhteyksien ja avainten
määritykset. Tietokanta kuvataan käyttäen jotain kuvausmenetelmää, joko ER-kaaviota ja UML-luokkakaaviota.

Lisäksi kukin järjestelmän tietoelementti ja sen attribuutit kuvataan
tietohakemistossa. Tietohakemisto tarkoittaa yksinkertaisesti vain jokaisen elementin (taulun) ja niiden
attribuuttien (kentät/sarakkeet) listausta ja lyhyttä kuvausta esim. tähän tyyliin:

> ### _Tilit_
> _Tilit-taulu sisältää käyttäjätilit. Käyttäjällä voi olla monta tiliä. Tili kuuluu aina vain yhdelle käyttäjälle._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | int PK | Tilin id
> nimimerkki | varchar(30) |  Tilin nimimerkki
> avatar | int FK | Tilin avatar, viittaus [avatar](#Avatar)-tauluun
> kayttaja | int FK | Viittaus käyttäjään [käyttäjä](#Kayttaja)-taulussa

## Tekninen kuvaus

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset
ratkaisut, esim.

-   Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma)
    ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin:
    https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/)
-   Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms.
-   Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää
    UML-sekvenssikaavioilla.
-   Toteutuksen yleisiä ratkaisuja, esim. turvallisuus.

Tämän lisäksi

-   ohjelmakoodin tulee olla kommentoitua
-   luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa
    johdonmukaisia nimeämiskäytäntöjä
-   ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta
    vältytään

## Testaus

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan
testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa.
Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan
erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

-   järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi
    rakennettua johonkin toiseen koneeseen

-   järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi
    asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja
käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta,
käyttäjätunnus, salasana, tietokannan luonti yms.).

## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä
mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän
käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat
järjestelmän pariin !
