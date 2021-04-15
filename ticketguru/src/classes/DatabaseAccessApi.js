import { Log } from "./Log.js";
import base64 from "base-64";

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
            return response;
        } catch (error) {
            return null;
        }
    }

    static async markTicketUsed(id, code) {
        try {
            const url = this.#urlBase + "/soldtickets/" + id;
            const response = await InternalMethods.patchData(url, "{ \"code\": \"" + code + "\" }");
            return response;
        } catch (error) {
            return null;
        }
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

    static createHeaders() {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', 'Basic ' + this.getUserBase64());
        return headers;
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
