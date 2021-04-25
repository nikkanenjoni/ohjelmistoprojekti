import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";

export default function TestComponent(props) {

    const test = async () => {
        try {
            const data = await DatabaseAccessApi.createOrder();
            console.log(data);
        } catch (error) {
            console.log(error);
        }
    }

    const test2 = async () => {

        try{
            const data2 = await DatabaseAccessApi.addTicketsToOrderById(737, 729, 20)
            console.log(data2);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div>
            <button onClick={test}>test</button><br></br>
            <button onClick={test2}>test</button>
        </div>
    )
}