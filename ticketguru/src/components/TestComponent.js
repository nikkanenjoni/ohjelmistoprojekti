import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";

export default function TestComponent(props) {

    const test = async () => {
        try {
            const data = await DatabaseAccessApi.markTicketUsed(36, "36p33p30");
            console.log(data);
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <div>
            <button onClick={test}>test</button>
        </div>
    )
}