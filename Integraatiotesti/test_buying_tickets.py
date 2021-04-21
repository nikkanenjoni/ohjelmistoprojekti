#
#   This test will try making an order and adding tickets to it
#

import rest_query
import requests
# import json


def test_bying_tickets():
    # reset database before tests
    response = rest_query.rest_query("/dev?reset=ok", "get", None, True)
    assert response.status_code == requests.codes.ok

    # Make a new order where we can add tickets
    order = rest_query.rest_query("/orders", "post")
    # make sure returned CREATED
    assert order.status_code == requests.codes.created

    # store orderID
    order_json = order.json()
    order_id = order_json["orderID"]

    # make sure order is empty at the moment
    order = rest_query.rest_query("/orders/"+str(order_id), "get")
    assert order.status_code == requests.codes.ok
    order_json = order.json()
    assert not order_json["tickets"]

    # now we will get tickets for the order
    tickets = rest_query.rest_query("/tickets", "get")
    # make sure returned OK
    assert tickets.status_code == requests.codes.ok

    # store ticket for buying
    tickets_json = tickets.json()
    ticket_id = tickets_json[0]["ticketID"]
    ticket_price = tickets_json[0]["price"]
    assert ticket_id > 0

    # Create a ticket for order and give 10 % discount
    ticket = {'ticketID': ticket_id, 'ticketPrice': ticket_price*0.9}
    response = rest_query.rest_query("/orders/"+str(order_id), "post", ticket)
    assert response.status_code == requests.codes.created

    # Make sure the order now contains that ticket
    response = rest_query.rest_query("/orders/"+str(order_id), "get")
    assert response.status_code == requests.codes.ok
    response_json = response.json()
    assert len(response_json["tickets"]) == 1
    assert response_json["tickets"][0]["price"] == ticket_price*0.9
