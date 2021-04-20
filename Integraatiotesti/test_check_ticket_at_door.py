#
#   This will check if ticket is valid and will mark it used
#


import rest_query
import requests


def test_ticket_and_mark_it_used():
    # first we will reset database
    response = rest_query.rest_query("/dev?reset=ok", "get", None, True)
    assert response.status_code == requests.codes.ok

    # first let's get all orders just to find a correct ticket code
    all_orders = rest_query.rest_query("/orders", "get")
    assert all_orders.status_code == requests.codes.ok
    all_orders_json = all_orders.json()
    ticket_code = all_orders_json[0]["tickets"][0]["code"]
    assert len(ticket_code) > 5

    # now we will fetch ticket information with the code
    ticket = rest_query.rest_query("/soldtickets?code="+ticket_code, "get")
    assert ticket.status_code == requests.codes.ok
    ticket_json = ticket.json()
    assert ticket_code == ticket_json["code"]
    assert not ticket_json["used"]

    # now we will mark ticket used (with wrong code!)
    response = rest_query.rest_query(
        "/soldtickets/"+str(ticket_json["ticketID"]), "patch", {'code': 'foobar'})
    assert response.status_code == requests.codes.bad_request
    # and now we mark it with correct code
    response = rest_query.rest_query(
        "/soldtickets/"+str(ticket_json["ticketID"]), "patch", {'code': ticket_code})
    assert response.status_code == requests.codes.ok
    response_json = response.json()
    assert response_json["used"]
    # and now we try to re-use the ticket
    response = rest_query.rest_query(
        "/soldtickets/"+str(ticket_json["ticketID"]), "patch", {'code': ticket_code})
    assert response.status_code == requests.codes.conflict
