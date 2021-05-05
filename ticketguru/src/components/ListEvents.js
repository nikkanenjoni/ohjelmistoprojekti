import React, { useEffect } from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi"

export default function ListEvents(props) {

    const [events, setEvents] = React.useState([]);

    useEffect(async () => {
        const eventArray = await DatabaseAccessApi.getEvents();
        if (eventArray !== null) {
            setEvents(eventArray);
        }
    }, []);

    return (
        <div>
            {events.map(event => (
                <div style={{cursor: "pointer", marginBottom: "1em"}} key={event.eventID} onClick={() => props.updateId(event.eventID)}>
                    - {event.event} || Lippuja myyty: {event.soldTickets} / {event.capacity}
                </div>
            ))}
        </div>
    );
}
