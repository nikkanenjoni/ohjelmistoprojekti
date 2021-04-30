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

    const buttonStyle = {
        color: 'white',
        marginTop: 5,
        padding: 10,
        backgroundColor: 'Green',
        cursor: 'pointer',
    };

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
            <button style={buttonStyle} onClick={test}>Valitse lippu</button><br></br>
            <button style={buttonStyle} onClick={test2}>Osta lippu</button>
        </div>
    )
}