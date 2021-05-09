# RestAPI 

Currently only http://localhost:8080/ works as test base for the Rest-api. 
All endpoints are open at the moment. Authentication is added later. 

## Contents

- [Events endpoint](#Events-endpoint)
- [Tickets endpoint](#Tickets-endpoint)
- [TicketTypes endpoint](#TicketTypes-endpoint)
- [Orders endpoint](#Orders-endpoint)
- [SoldTickets endpoint](#SoldTickets-endpoint)


## Events endpoint

Each endpoint is used to view events or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/events](#GET-events) | User | Lists ALL events in database |
| `GET` | [/api/events/:id](#GET-event-id) | User | Displays information of event with given `:id` |
| `GET` | [/api/events/:id/tickets](#GET-event-id-tickets) | User | **Obsolete** Displays information of tickets for an event | 
| `POST` | [/api/events](#POST-event) | Admin | Adds new event to database |
| `PUT` | [/api/events/:id](#PUT-event-id) | Admin | Updates the event with given `:id` |
| `DELETE` | [/api/events/:id](#DELETE-event-id) | Admin | Deleted the event with given `:id` |



## GET events

Lists all events

**URL** : `/api/events/`

**Method** : `GET`

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content including three events. 
> 
> ```JSON
> [
>     {
>         "eventID": 9,
>         "event":"Hippafesti",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Hippakenttä",
>         "soldTickets":3,
>         "capacity":1000,
>         "description":"Kuvaus tapahtumasta tähän.",
>         "datetime":"2021-02-28T13:27:44.796903",
>         "tickets": [
>             {
>                 "ticketID": 13,
>                 "ticketType": {
>                     "ticketTypeID": 8,
>                     "ticketType": "Opiskelija"
>                 },
>                 "price": 20.0,
>                 "description": ""
>             },
>             {
>                 "ticketID": 14,
>                 "ticketType": {
>                     "ticketTypeID": 7,
>                     "ticketType": "Normaali"
>                 },
>                 "price": 20.0,
>                 "description": ""
>             }
>         ]
>     },
>     {
>         "eventID": 10,
>         "event":"Musadiggarit",
>         "city":
>         {
>             "city":"Ilmala"
>         },
>         "eventPlace":"Mutakenttä jäähallin takana",
>         "soldTickets":0,
>         "capacity":6,
>         "description":"",
>         "datetime":"2021-02-28T13:27:44.820266",
>         "tickets": []
>     },
>     {
>         "eventID": 11,
>         "event":"Antin rokkibändi",
>         "city":
>         {
>             "city":"Rovaniemi"
>         },
>         "eventPlace":"Kellariklubi",
>         "capacity":150,
>         "description":"Hieno bändi!",
>         "datetime":"2021-02-28T13:27:44.8259",
>         "tickets": []
>     }
> ]
> ```

</details>


## GET event id

Views information of a spesific event. 

**URL** : `/api/events/:id`

**Method** : GET 

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of an event. 
> 
> ```JSON
> {
>     "eventID": 9,
>     "event":"Hippafesti",
>     "city":
>     {
>         "city":"Rovaniemi"
>     },
>     "eventPlace":"Hippakenttä",
>     "soldTickets":3,
>     "capacity":1000,
>     "description":"Kuvaus tapahtumasta tähän.",
>     "datetime":"2021-02-28T13:27:44.796903",
>     "tickets": [
>         {
>             "ticketID": 13,
>             "ticketType": {
>                 "ticketTypeID": 8,
>                 "ticketType": "Opiskelija"
>             },
>             "price": 20.0,
>             "description": ""
>         },
>         {
>             "ticketID": 14,
>             "ticketType": {
>                 "ticketTypeID": 7,
>                 "ticketType": "Normaali"
>             },
>             "price": 20.0,
>             "description": ""
>         }
>     ]
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

## GET event id tickets

List tickets by eventID. 

**URL** : `/api/events/:id/tickets`

**Method** : GET 

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of events tickets. 
> 
> ```JSON
> [
>   {
>    "ticketID": 9,
>    "ticketType": {
>        "ticketTypeID": 4,
>        "ticketType": "Opiskelija"
>    },
>    "event": {
>        "eventID": 5,
>        "event": "Hippafesti",
>        "eventPlace": "Hippakenttä",
>        "capacity": 1000,
>        "description": "Kuvaus tapahtumasta tähän.",
>        "datetime": "2021-03-08T17:09:43.957227"
>    },
>    "price": 20.0,
>    "description": ""
>   },
>   {
>    "ticketID": 10,
>    "ticketType": {
>        "ticketTypeID": 5,
>        "ticketType": "Aikuinen"
>    },
>    "event": {
>        "eventID": 5,
>        "event": "Hippafesti",
>        "eventPlace": "Hippakenttä",
>        "capacity": 1000,
>        "description": "Kuvaus tapahtumasta tähän.",
>        "datetime": "2021-03-08T17:09:43.957227"
>    },
>    "price": 25.0,
>    "description": ""
>   },
> ]
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

## GET event id tickets

List tickets by eventID. 

**URL** : `/api/events/:id/tickets`

**Method** : GET 

**Auth required** : *not available*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of events tickets. 
> 
> ```JSON
> [
>   {
>    "ticketID": 9,
>    "ticketType": {
>        "ticketTypeID": 4,
>        "ticketType": "Opiskelija"
>    },
>    "event": {
>        "eventID": 5,
>        "event": "Hippafesti",
>        "eventPlace": "Hippakenttä",
>        "capacity": 1000,
>        "description": "Kuvaus tapahtumasta tähän.",
>        "datetime": "2021-03-08T17:09:43.957227"
>    },
>    "price": 20.0,
>    "description": ""
>   },
>   {
>    "ticketID": 10,
>    "ticketType": {
>        "ticketTypeID": 5,
>        "ticketType": "Aikuinen"
>    },
>    "event": {
>        "eventID": 5,
>        "event": "Hippafesti",
>        "eventPlace": "Hippakenttä",
>        "capacity": 1000,
>        "description": "Kuvaus tapahtumasta tähän.",
>        "datetime": "2021-03-08T17:09:43.957227"
>    },
>    "price": 25.0,
>    "description": ""
>   },
> ]
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
 
**Auth required** : *Admin*
 
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

**Auth required** : *Admin*

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
 
**Auth required** : *Admin*
 
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

## Orders endpoint

Each endpoint is used to view orders or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/orders](#GET-orders) | User | Lists ALL orders in database |
| `GET` | [/api/orders/:id](#GET-order-id) | User | Displays information of order with given `:id` |
| `POST` | [/api/orders](#POST-order) | User | Adds new order to database |
| `POST` | [/api/orders/:id](#POST-order-id) | User | Add a ticket to the order with `:id` |
| `PUT` | [/api/orders/:id](#PUT-order-id) | User | Updates the order with given `:id` |
| `DELETE` | [/api/orders/:id](#DELETE-order-id) | User | Deleted the order with given `:id` |



## GET orders

Lists all orders

**URL** : `/api/orders/`

**Method** : `GET`

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : Orders include a timestamp for the ordertime.
> 
> ```JSON
> {
>     "datetime":"2021-03-07T13:27:44."
> }
> ```
> ```

</details>


## GET order id

Views information of a spesific order. 

**URL** : `/api/orders/:id`

**Method** : GET 

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of an order. 
> 
> ```JSON
> {
>   "orderID": 12,
>       "datetime": "2021-03-08T12:59:21.81998",
>       "tickets": [
>         {
>             "ticketOrderID": 14,
>             "ticket": {
>                 "ticketID": 9,
>                 "ticketType": {
>                     "ticketTypeID": 4,
>                     "ticketType": "Opiskelija"
>                 },
>                 "event": {
>                     "eventID": 5,
>                     "event": "Hippafesti",
>                     "eventPlace": "Hippakenttä",
>                     "capacity": 1000,
>                     "description": "Kuvaus tapahtumasta tähän.",
>                     "datetime": "2021-03-08T12:59:21.783632"
>                 },
>                 "price": 20.0,
>                 "description": ""
>             },
>             "price": 20.0
>         },
>         {
>             "ticketOrderID": 15,
>             "ticket": {
>                 "ticketID": 10,
>                 "ticketType": {
>                     "ticketTypeID": 3,
>                     "ticketType": "Normaali"
>                 },
>                 "event": {
>                     "eventID": 5,
>                     "event": "Hippafesti",
>                     "eventPlace": "Hippakenttä",
>                     "capacity": 1000,
>                     "description": "Kuvaus tapahtumasta tähän.",
>                     "datetime": "2021-03-08T12:59:21.783632"
>                 },
>                 "price": 20.0,
>                 "description": ""
>             },
>             "price": 20.0
>        }
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
>     "order":"NOT FOUND"
> }
> ```


</details>

## POST order

Allow creation of new orders to database. 

**URL** : `/api/orders`

**Method** : `POST`
 
**Auth required** : *User*
 
**Data constrains**
 
No JSON-body is required. 

> ```JSON
> {}
>

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
>     "orderID":16,
>     "datetime":"2021-02-28T13:27:44.",
>     "tickets":null
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
>     "datetime":"2021-02-28T13:27:44.
> }
> ```

</details>

## POST order id

Allows adding tickets to an order. 

**URL** : `/api/orders/:id`

**Method** : `POST`
 
**Auth required** : *User*
 
**Data constrains**
 
Following JSON-body is required.

> ```JSON
> [{
>     "ticketID":0,
>     "ticketPrice":00.00
> },
> {
>     "ticketID":0,
>     "ticketPrice":00.00
> }]
> ```

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
>     "orderID":16,
>     "datetime":"2021-02-28T13:27:44.",
>     "tickets":null
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
>     "datetime":"2021-02-28T13:27:44.
> }
> ```


> **Condition** : One or more tickets was sold out.  
> 
> **Code** : `409 CONFLICT`
> 
> **Content example**
> 
> ```JSON
> {
>     "datetime":"2021-02-28T13:27:44.
> }
> ```

</details>

## PUT order id 

This endpoint allows updating of a specific order. 

**URL** : `/api/orders/:id`

**Method** : `PUT`

**Auth required** : *User* 

**Data constrains**
 
Following JSON-body is required.

 ```JSON
> {
    "datetime":"2021-02-28T13:27:44."
 }
```

<details>

#### Success

> **Condition** : order with the `id` exists in the database. 
> 
> **Code** : `200 OK`
> 
> **Content example**
> 
> ```JSON
> {
>     "datetime":"2021-02-28T13:27:44.
> }
> ```

#### Error

> **Condition** : order with the `id` didn't exist in the database. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>

## DELETE order

This endpoint allows deleting of a ticket from an order. 

**URL** : `/api/orders/:id/ticketorder/:id`


**Method** : `DELETE`
 
**Auth required** : *User*
 
<details>

> **Condition** : Ticket with the `id` was found from order with the `id` in database. 
> 
> **Code** : `204 NO CONTENT`
> 
> **Content** : `{}`

#### Error

> **Condition** : Ticket with the `id` wasn't found from the order. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>


## Tickets endpoint

Each endpoint is used to view tickets or to add/update/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/tickets](#GET-ticket) | User | Lists ALL tickets in database |
| `POST` | [/api/tickets](#POST-ticket) | Admin | Adds new ticket to database |
| `DELETE` | [/api/tickets/:id](#DELETE-ticket-id) | Admin | Deleted the ticket with given `:id` |



## GET tickets

Lists all tickets

**URL** : `/api/tickets/`

**Method** : `GET`

**Auth required** : *User*

<details>

#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content including three tickets. 
> 
> ```JSON
>     {
>        "ticketID": 9,
>        "ticketType": {
>            "ticketTypeID": 4,
>            "ticketType": "Opiskelija"
>        },
>        "event": {
>            "eventID": 5,
>            "event": "Hippafesti",
>            "eventPlace": "Hippakenttä",
>            "capacity": 1000,
>            "description": "Kuvaus tapahtumasta tähän.",
>            "datetime": "2021-03-08T17:09:43.957227"
>        },
>        "price": 20.0,
>        "description": ""
>    },
>    {
>        "ticketID": 10,
>        "ticketType": {
>            "ticketTypeID": 3,
>            "ticketType": "Normaali"
>        },
>        "event": {
>            "eventID": 5,
>            "event": "Hippafesti",
>            "eventPlace": "Hippakenttä",
>            "capacity": 1000,
>            "description": "Kuvaus tapahtumasta tähän.",
>            "datetime": "2021-03-08T17:09:43.957227"
>        },
>        "price": 20.0,
>        "description": ""
>    },
>    {
>        "ticketID": 11,
>        "ticketType": {
>            "ticketTypeID": 4,
>            "ticketType": "Opiskelija"
>        },
>        "event": {
>            "eventID": 6,
>            "event": "Musadiggarit",
>            "eventPlace": "Mutakenttä jäähallin takana",
>            "capacity": 6,
>            "description": "",
>            "datetime": "2021-03-08T17:09:43.969195"
>        },
>        "price": 20.0,
>        "description": ""
>    }
>]
> ```

</details>



## POST ticket

Allow creation of new ticket to database. 

**URL** : `/api/tickets`

**Method** : `POST`
 
**Auth required** : *Admin*
 
**Data constrains**
 
Following JSON-body is required.

```JSON
    {
        "ticketType": {
            "ticketTypeID": 0
        },
        "event": {
            "eventID": 0
        },
        "price": 00.00,
        "description": ""
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
 
**Auth required** : *Admin*
 
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


## TicketTypes-endpoint

Each endpoint is used to view tickettypes or to add/delete them.

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/tickettypes](#GET-tickettypes) | User | Lists ALL events in database |
| `POST` | [/api/tickettypes](#POST-tickettypes) | Admin | Adds new event to database |
| `DELETE` | [/api/tickettypes/:id](#DELETE-tickettype-id) | Admin | Deleted the tickettype with given `:id` |

## GET tickettypes

Lists all tickettypes

**URL** : `/api/tickettypes`

**Method** : GET 

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of tickettypes. 
> 
> ```JSON
> {
>     "ticketTypeID":"id",
>     "ticketType": "tickettype",
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
>     "tickettype":"NOT FOUND"
> }
> ```


</details>


## POST tickettype

Allow creation of new tickettypes to database. 

**URL** : `/api/tickettypes`

**Method** : `POST`
 
**Auth required** : *Admin*
 
**Data constrains**
 
Following JSON-body is required.

```JSON
 {
     "ticketTypeID":"id",
     "ticketType": "tickettype",
 }
```

<details>


#### Success

 **Condition** : If all information given was valid. 
 
 **Code** : `201 CREATED`
 
 **Content example**
 
 ```JSON
 {
     "ticketTypeID":"id",
     "ticketType": "tickettype",
 }
 ```
 
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



## DELETE tickettype id

This endpoint allows deleting of a specific tickettype from database. 

**URL** : `/api/tickettypes/:id`

**Method** : `DELETE`
 
**Auth required** : *Admin*
 
<details>


#### Success

> **Condition** : Tickettype with the `id` was found from database. 
> 
> **Code** : `204 NO CONTENT`
> 
> **Content** : `{}`

#### Error

> **Condition** : Tickettype with the `id` wasn't found. 
> 
> **Code** : `404 NOT FOUND`
> 
> **Content** : `{}`

</details>

## SoldTickets-endpoint

Each endpoint is used to view ticket or mark it used. 

| Method | Endpoint | Access | Description|
|----|----|----|----|
| `GET` | [/api/soldtickets?code=:code](#GET-soldticket) | User | Displays ticket information.  |
| `PATCH` | [/api/soldtickets/:id](#PATCH-soldticket) | User | Marks ticket with `:id` as used.  |



## GET soldticket

Displays ticket

**URL** : `/api/soldtickets/`

**Method** : `GET`

**Auth required** : *User*

<details>


#### Success

> **Code** : `200 OK`
> 
> **Content** : An example of possible content for a successful request of a ticket. 
> 
> ```JSON
> [
>   {
>    "ticketID": 9,
>    "eventName": "Name of event",
>    "code": "105x932x104",
>    "used": false,
>    "usedDate": null, 
>    "ticketType": "student"
>   }
> ```

#### Error

> **Condition** : Requested `id` is not found from database. 
> 
> **Code** : `404 NOT FOUND`


</details>

## PATCH soldticket

Mark ticket used. 

**URL** : `/api/soldtickets/:id`

**Method** : `PATCH`
 
**Auth required** : *User*
 
**Data constrains**
 
Following JSON-body is required.

```JSON
    {
        "code": "105x932x104"
    }

```

<details>


#### Success

> **Condition** : If all information given was valid. 
> 
> **Code** : `200 OK`
> 
> **Content example**
> 
> ```JSON
> [
>   {
>    "ticketID": 9,
>    "eventName": "Name of event",
>    "code": "105x932x104",
>    "used": true,
>    "usedDate": "2021-02-28T13:27:44.796903", 
>    "ticketType": "student"
>   }
> ```
 
#### Error
 
> **Condition** : code didn't match the code in ticket
> 
> **Code** : `400 BAD REQUEST`
 

</details>



## DEV API

Last but not least:

GET `/api/dev?reset=ok` as ADMIN to reset database with default data. :)

