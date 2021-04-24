# https://docs.python-requests.org/en/master/

import requests


def rest_query(endpoint, method, body = None, admin = False):
    user = "user"
    password = "password"
    if admin:
        user = "admin"
    url = "https://ticketguru.codecache.eu/api" + endpoint
    if method=="get":
        return requests.get(url, auth=(user, password))
    if method=="post":
        return requests.post(url, json=body, auth=(user, password))
    if method=="patch":
        return requests.patch(url, json=body, auth=(user, password))
