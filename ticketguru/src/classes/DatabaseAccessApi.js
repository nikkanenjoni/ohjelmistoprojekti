import { Log } from "./Log.js";

export class DatabaseAccessApi {

    static setUser(username, password) {
        InternalMethods.setUser(username, password);
    }

    static async getEvents() {
        const response = await InternalMethods.getData("https://ticketguru.codecache.eu/api/events");
        console.log(response);
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
        return Buffer.from(this.#username + ":" + this.#password);
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
                headers: this.createHeaders(),
            });
            const responseJson = await response.json();
            return responseJson;
        } catch (error) {
            Log.writeLog(this.className + ".getData(): " + error, 1);
            return null;
        }
    }
}