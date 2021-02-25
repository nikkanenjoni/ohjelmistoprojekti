# Tänne CRUD-toimintopyynnön metodi, polku, polkuparametrit, query-parametrit ja sisältö ja niistä vastauksen paluukoodi ja sisältö

## GET


### Event


Näytä valitun tapahtuman tiedot.


#### Näytä kaikki tapahtumat:

Polku: GET localhost:8080/events/

#### Näytä tietty tapahtuma:

Polku: GET localhost:8080/events/:pk

#### Vastauksen paluukoodit

##### Onnistunut vastaus

Code : 200 OK

##### Error

Code: 404 Not found

#### Sisältöesimerkkejä:

Tapahtuma ID:llä 1234 tietokannassa, jonne käyttäjä on tallentanut tapahtuman nimen, ajan ja tapahtumapaikan. Kuvaus ei ole pakollinen.


{  
    "id": 1234;  
    "event" = "Anna Puu juhlakiertue";   
    "city" = "Helsinki";  
	"eventPlace" = "Tavastia";  
	"capacity" = 500;  
	"description" = "";  
	"dateTime" = "2022/08/22 19:00:00";  
}



### Order (Täydennetään myöhemmin)


Näytä kaikki tilaukset:
Polku: GET localhost:8080/orders/


Näytä tietty tilaus:
Polku: GET localhost:8080/orders/:pk

#### Ticket (Täydennetään myöhemmin)


Näytä kaikki liput:

Polku: GET localhost:8080/tickets/


Näytä tietty lippu:

Polku: GET localhost:8080/tickets/:pk


## POST


Postilla luodaan uusia tietoja. POST luo uuden resurssin. Uudelle resurssille annetaan URI, jonka palvelin
palauttaa kutsujalle. POST-pyyntöä käytetään usein myös lähettämään dataa olemassaolevalle resurssille.


#### Event

Uuden tapahtuman luomiseen. Vaatii käyttäjäoikeudet.
Polku: POST localhost:8080/events/:pk


#### Order (Täydennetään myöhemmin)

Uuden tilauksen luomiseen, tapahtuu lippuoston yhteydessä.

Polku: POST localhost:8080/orders/:pk

#### Ticket (Täydennetään myöhemmin)

Uuden lipun luomiseen, tapahtuu lippuoston yhteydessä.

Polku: POST localhost:8080/tickets/:pk


## PUT

PUT-komennolla päivitetään tietoja. PUT korvaa olemassa olevan resurssin kokonaisuudessaan.

### Event

Tapahtuman päivittäminen.

Polku: PUT localhost:8080/events/:pk


### Order (Täydennetään myöhemmin)

Tilauksen päivittäminen.

Polku: PUT localhost:8080/orders/:pk


### Ticket (Täydennetään myöhemmin)

Yksittäisen lipun päivittäminen.

Polku: PUT localhost:8080/tickets/:pk


## DELETE

Delete poistaa resurssin kokonaisuudessaan. Poistettava resurssi tunnistetaan pyynnön URI:lla.


##### Event

Tapahtuman poistaminen.

Polku: DELETE localhost:8080/events/:pk


##### Order (Täydennetään myöhemmin)

Tilauksen poistaminen.

Polku: DELETE localhost:8080/orders/:pk


##### Ticket (Täydennetään myöhemmin)

Lipun poistaminen.

Polku: DELETE localhost:8080/tickets/:pk