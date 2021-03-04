# RestAPI 

Currently only http://localhost:8080/ works as test base for the Rest-api. 
All endpoints are open at the moment. Authentication is added later. 

## Contents

- [Events endpoint](#Events-endpoint)
- [Tickets endpoint](#Tickets-endpoint)

## Events endpoint

Each endpoint is used to view events or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/events](#GET-events) | -- | Lists ALL events in database |
| `GET` | [/api/events/:id](#GET-event-id) | -- | Displays information of event with given `:id` |
| `POST` | [/api/events](#POST-event) | -- | Adds new event to database |
| `PUT` | [/api/events/:id](#PUT-event-id) | -- | Updates the event with given `:id` |
| `DELETE` | [/api/events/:id](#DELETE-event-id) | -- | Deleted the event with given `:id` |



## GET events

Lists all events

**URL** : `/api/events/`

**Method** : `GET`

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content including three events. 
> 
> ```JSON
> [
>     {
>         "event":"Hippafesti",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Hippakenttä",
>         "capacity":1000,
>         "description":"Kuvaus tapahtumasta tähän.",
>         "datetime":"2021-02-28T13:27:44.796903"
>     },
>     {
>         "event":"Musadiggarit",
>         "city":
>         {
>             "city":"Ilmala"
>         },
>         "eventPlace":"Mutakenttä jäähallin takana",
>         "capacity":6,
>         "description":"",
>         "datetime":"2021-02-28T13:27:44.820266"
>     },
>     {
>         "event":"Antin rokkibändi",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Kellariklubi",
>         "capacity":150,
>         "description":"Hieno bändi!",
>         "datetime":"2021-02-28T13:27:44.8259"
>     }
> ]
> ```

</details>


## GET event id

Views information of a spesific event. 

**URL** : `/api/events/:id`

**Method** : GET 

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of an event. 
> 
> ```JSON
> {
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903"
> }
> ```

#### Error

> **Condition** : Requested `id` is not found from database. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"NOT FOUND"
> }
> ```


</details>

## POST event

Allow creation of new events to database. 

**URL** : `/api/events`

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

<details>


#### Success

> **Condition** : If all information given was valid. 
> 
> **Code** : `201 CREATED`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903"
> }
> ```
 
#### Error
 
> **Condition** : Some of the information given wasn't tested valid. 
> 
> **Code** : `400 BAD REQUEST`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"This field has to be atleast 10 charactes long",
>     "capacity": "This field cannot be null"
> }
> ```

</details>

## PUT event id 

This endpoint allows updating of a specific event. 

**URL** : `/api/events/:id`

**Method** : `PUT`

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

<details>

#### Success

> **Condition** : Event with the `id` exists in the database. 
> 
> **Code** : `200 OK`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903"
> }
> ```

#### Error

> **Condition** : Event with the `id` didn't exist in the database. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>

## DELETE event id

This endpoint allows deleting of a specific event from database. 

**URL** : `/api/events/:id`

**Method** : `DELETE`
 
**Auth required** : *not available*
 
<details>


#### Success

> **Condition** : Event with the `id` was found from database. 
> 
> **Code** : `204 NO CONTENT`
> 
> **Content** : `{}`

#### Error

> **Condition** : Event with the `id` wasn't found. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>


## Tickets endpoint

Each endpoint is used to view tickets or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/tickets](#GET-ticket) | -- | Lists ALL tickets in database |
| `GET` | [/api/tickets/:id](#GET-ticket-id) | -- | Displays information of ticket with given `:id` |
| `POST` | [/api/tickets](#POST-ticket) | -- | Adds new ticket to database |
| `DELETE` | [/api/tickets/:id](#DELETE-ticket-id) | -- | Deleted the event with given `:id` |



## GET tickets

Lists all tickets

**URL** : `/api/tickets/`

**Method** : `GET`

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content including three tickets. 
> 
> ```JSON
> [
>     {
>         "event":"Hippafesti",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Hippakenttä",
>         "capacity":1000,
>         "description":"Kuvaus tapahtumasta tähän.",
>         "datetime":"2021-02-28T13:27:44.796903"
>     },
>     {
>         "event":"Musadiggarit",
>         "city":
>         {
>             "city":"Ilmala"
>         },
>         "eventPlace":"Mutakenttä jäähallin takana",
>         "capacity":6,
>         "description":"",
>         "datetime":"2021-02-28T13:27:44.820266"
>     },
>     {
>         "event":"Antin rokkibändi",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Kellariklubi",
>         "capacity":150,
>         "description":"Hieno bändi!",
>         "datetime":"2021-02-28T13:27:44.8259"
>     }
> ]
> ```

</details>


## GET ticket id

Views information of a spesific ticket. 

**URL** : `/api/tickets/:id`

**Method** : GET 

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of an ticket. 
> 
> ```JSON
> {
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903"
> }
> ```

#### Error

> **Condition** : Requested `id` is not found from database. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"NOT FOUND"
> }
> ```


</details>

## POST ticket

Allow creation of new ticket to database. 

**URL** : `/api/tickets`

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

<details>


#### Success

> **Condition** : If all information given was valid. 
> 
> **Code** : `201 CREATED`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903"
> }
> ```
 
#### Error
 
> **Condition** : Some of the information given wasn't tested valid. 
> 
> **Code** : `400 BAD REQUEST`
> 
> **Content example**
> 
> ```JSON
> {
>     "event":"This field has to be atleast 10 charactes long",
>     "capacity": "This field cannot be null"
> }
> ```

</details>


## DELETE ticket id

This endpoint allows deleting of a specific ticket from database. 

**URL** : `/api/tickets/:id`

**Method** : `DELETE`
 
**Auth required** : *not available*
 
<details>


#### Success

> **Condition** : Ticket with the `id` was found from database. 
> 
> **Code** : `204 NO CONTENT`
> 
> **Content** : `{}`

#### Error

> **Condition** : Ticket with the `id` wasn't found. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>