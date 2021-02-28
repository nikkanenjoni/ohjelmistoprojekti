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

**URL** : `/api/event/:id`

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

**URL** : `/api/event/:id`

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

**URL** : `/api/event/:id`

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

