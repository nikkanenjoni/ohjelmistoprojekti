# Tänne CRUD-toimintopyynnön metodi, polku, polkuparametrit, query-parametrit ja sisältö ja niistä vastauksen paluukoodi ja sisältö

## GET

#### Näytä valitun tapahtuman tiedot.
Sisältää tapahtuman nimen, tapahtumapaikan, kapasiteetin, kuvauksen ja tapahtuma-ajan.

### Vastauksen paluukoodit

Onnistunut vastaus:

Koodi : 200

Epäonnistunut vastaus:

Koodi : 404 NOT FOUND

### Sisältöesimerkkejä:

Tapahtuma ID:llä 1234 tietokannassa, jonne käyttäjä on tallentanut tapahtuman nimen, ajan ja tapahtumapaikan.

{

    "id": 1234;
    "event" = "Anna Puu juhlakiertue";
    "city" = "Helsinki";
	"eventPlace" = "Tavastia";
	"capacity" = 500;
	"description" = "";
	"dateTime" = "2022/08/22 19:00:00";

}


