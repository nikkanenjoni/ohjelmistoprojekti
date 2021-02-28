# Tänne CRUD-toimintopyynnön metodi, polku, polkuparametrit, query-parametrit ja sisältö ja niistä vastauksen paluukoodi ja sisältö

# RestAPI 

Currently only http://localhost:8080/ works as test base for the Rest-api. 
All endpoints are open at the moment. Authentication is added later. 

## Contents

- [Events endpoint](#Events-endpoint)

## Events endpoint

Each endpoint is used to view events or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/events](#GET-events) | -- | Lists ALL events in database |
| `GET` | [/api/event/:id](#GET-event-id) | -- | Displays information of event with given `:id` |
| `POST` | [/api/event](#POST-event) | -- | Adds new event to database |
| `PUT` | [/api/event/:id](#PUT-event-id) | -- | Updates the event with given `:id` |
| `DELETE` | [/api/event/:id](#DELETE-event-id) | -- | Deleted the event with given `:id` |



## GET events

<details>

Lists all events

**URL** : `/api/events/`

**Method** : `GET`

**Auth required** : *not available*

#### Success

**Code** : `200 OK`

**Content** : An example of possible content including three events. 

```JSON
[
    {
        "event":"Hippafesti",
        "city":
        {
            "city":"Rovaniemi"
        },
        "eventPlace":"Hippakenttä",
        "capacity":1000,
        "description":"Kuvaus tapahtumasta tähän.",
        "datetime":"2021-02-28T13:27:44.796903"
    },
    {
        "event":"Musadiggarit",
        "city":
        {
            "city":"Ilmala"
        },
        "eventPlace":"Mutakenttä jäähallin takana",
        "capacity":6,
        "description":"",
        "datetime":"2021-02-28T13:27:44.820266"
    },
    {
        "event":"Antin rokkibändi",
        "city":
        {
            "city":"Rovaniemi"
        },
        "eventPlace":"Kellariklubi",
        "capacity":150,
        "description":"Hieno bändi!",
        "datetime":"2021-02-28T13:27:44.8259"
    }
]
```

</details>


## POST event

<details>

Allow creation of new events to database. 

**URL** : `/api/event`

**Method** : `POST`

**Auth required** : *not available*

**Data constrains**

Following JSON-body is required.

```JSON
{
    "event":"name-of-the-event",
    "city":"name-of-the-city",
    "eventPlace":"Address of the event",
    "capacity":1,
    "description":"An optional description of the event",
    "datetime":"event date & time in without a time-zone in the ISO-8601"
}
```

#### Success

**Condition** : If all information given was valid. 

**Code** : `201 CREATED`

**Content example**

```JSON
{
    "event":"Hippafesti",
    "city":
    {
        "city":"Rovaniemi"
    },
    "eventPlace":"Hippakenttä",
    "capacity":1000,
    "description":"Kuvaus tapahtumasta tähän.",
    "datetime":"2021-02-28T13:27:44.796903"
}
```

#### Error

**Condition** : Some of the information given wasn't tested valid. 

**Code** : `400 BAD REQUEST`

**Content example**

```JSON
{
    "event":"This field has to be atleast 10 charactes long",
    "capacity": "This field cannot be null"
}
```

</details>

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
