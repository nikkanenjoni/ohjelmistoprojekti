# Tänne CRUD-toimintopyynnön metodi, polku, polkuparametrit, query-parametrit ja sisältö ja niistä vastauksen paluukoodi ja sisältö

# GET

## Näytä valitun tapahtuman tiedot.
Get the details of the currently Authenticated User along with basic subscription information.

### Vastauksen paluukoodit

Onnistunut vastaus
Code : 200 OK

#### Sisältöesimerkkejä:

Tapahtuma ID:llä 1234 tietokannassa, jonne käyttäjä on tallentanut tapahtuman nimen, ajan ja tapahtumapaikan.

{
    "id": 1234,
   "event" = "Anna Puu juhlakiertue";
    "city" = "Helsinki";
	"eventPlace" = "Tavastia";
	"capacity" = 500;
	"description" = "";
	"dateTime" = "2022/08/22 19:00:00";
	}
}
