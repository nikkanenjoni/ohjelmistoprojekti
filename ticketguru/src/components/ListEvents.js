import React, { useEffect } from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi"

export default function ListEvents(props) {

    const [events, setEvents] = React.useState([]);

    const divStyle = {
        padding: 20,
        textAlign: 'center'
      };

    const table = {
        padding: 20,
        textAlign: 'center',
      };

      const Header2 = {
        fontSize: '20px',
        textAlign: 'center',
        color: 'white',
        margin: 'auto',
        border: 5
      };

    useEffect(async () => {
        const eventArray = await DatabaseAccessApi.getEvents();
        if (eventArray !== null) {
            setEvents(eventArray);
        }
    }, []);


    return (
        <table style={Header2}>
            <tr style={table}>
                <th style={table}>Tapahtuman nimi</th>
                <th style={table}>Myydyt liput</th>
            </tr>
            {events.map(event => (
                <tr style={{cursor: "pointer", marginBottom: "1em"}} key={event.eventID} onClick={() => props.updateId(event.eventID)}>
                        <td>{event.event}</td>
                        <td>{event.soldTickets} / {event.capacity}</td>
                </tr>
            ))}
        </table>

    );
}
