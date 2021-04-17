import { Log } from "./Log.js";
import base64 from "base-64";

/*
 *  Usage:
 *    DatabaseAccessApi.setUser(username, password) to set credentials for api-calls
 *    DatabaseAccessApi.setUrlBase(base) to set api url (default: https://ticketguru.codecache.eu/api)
 *                                       DO NOT SET TRAILING / here!
 * 
 *    DatabaseAccessApi.getEvents() lists all events in JSON-format
 *    DatabaseAccessApi.getTicketByCode(code) returns ticket with given code in JSON-format
 *    DatabaseAccessApi.markTicketUsed(id, code) marks ticket with matching id & code as used and returns it in JSON-format
 */

export class DatabaseAccessApi {

    static #urlBase = "https://ticketguru.codecache.eu/api";

    static setUrlBase(base) {
        this.#urlBase = base;
    }

    static setUser(username, password) {
        InternalMethods.setUser(username, password);
    }

    static async getEvents() {
        try {
            const response = await InternalMethods.getData(this.#urlBase + "/events");
            return response;
        } catch (error) {
            return null;
        }
    }

    static async getOrders() {
        try {
            const response = await InternalMethods.getData(this.#urlBase + "/orders");
            return response;
        } catch (error) {
            return null;
        }
    }

    static async getTicketByCode(code) {
        try {
            const url = this.#urlBase + "/soldtickets?code=" + code;
            const response = await InternalMethods.getData(url);
            return Parser.parseTicketInstance(response);
        } catch (error) {
            return null;
        }
    }

    static async markTicketUsed(id, code) {
        try {
            const url = this.#urlBase + "/soldtickets/" + id;
            const data = {
                "code": code
            };
            const response = await InternalMethods.patchData(url, data);
            return Parser.parseTicketInstance(response);
        } catch (error) {
            return null;
        }
    }
}

class Parser {
    static parseTicketInstance(data) {
        const ticket = {
            "ticketID": data.ticketID,
            "eventName": data.eventName,
            "code": data.code,
            "used": data.used,
            "usedDate": data.usedDate,
            "ticketType": data.ticketType
        };
        return ticket;
    }
}

class InternalMethods {

    static className = "DatabaseAccessApi.InternalMethods";
    static #username = "admin";
    static #password = "password";

    static setUser(username, password) {
        this.#username = username;
        this.#password = password;
    }

    static getUserBase64() {
        return base64.encode(this.#username + ":" + this.#password);
    }

    /*
     *  These are the actual methods responsible of the fetch-calls
     */

    static async getData(url) {
        try {
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + this.getUserBase64()
                },
            });
            const responseJson = await response.json();
            return responseJson;
        } catch (error) {
            Log.writeLog(this.className + ".getData(): " + error, 1);
            return null;
        }
    }

    static async postDate(url, data) {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + this.getUserBase64()
                },
                body: JSON.stringify(data),
            });
            const responseJson = await response.json();
            return responseJson;
        } catch (error) {
            Log.writeLog(this.className + ".postData(): " + error, 1);
            return null;
        }
    }

    static async patchData(url, data) {
        try {
            const response = await fetch(url, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + this.getUserBase64()
                },
                body: JSON.stringify(data),
            });
            const responseJson = await response.json();
            return responseJson;
        } catch (error) {
            Log.writeLog(this.className + ".patchData(): " + error, 1);
            return null;
        }
    }
}
