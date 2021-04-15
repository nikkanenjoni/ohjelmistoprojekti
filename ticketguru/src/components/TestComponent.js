import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";

export default function TestComponent(props) {

    const test = async () => {
        try {
            const data = await DatabaseAccessApi.getOrders();
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