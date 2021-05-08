import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";
import MakeOrder from "./MakeOrder.js";
import ListEvents from "./ListEvents";

export default function SellTicket(props) {

        const [eventId, setId] = React.useState("");

        // tapahtumamuuttuja tässä komponentissa 
        const [tapahtuma, setEvent] = React.useState({
            eventID: 0,
            event: "",
            eventPlace: "",
            soldTickets: 0,
            capacity: 0,
            description: "",
            datetime: "",
            ticketID: 0,
            ticketTypeID: 0,
            ticketType: "",
            tickets: [],
            price: 0.0,
            descriptionT: "",
    });


        const updateId = (event) => {
          setId(event.target.value);
      }

      const insertEventId = (newId) => {
        setId(newId);
      }

        // tapahtumamuuttuja, joka näyttää tapahtuman
        const [displayEvent, setDisplayEvent] = React.useState(false);

           // messages
        const [message, setMessage] = React.useState('');

        // styles (näitä luokkia voit käyttää ja muokata returnin sisällä)
        const divStyle = {
            margin: '0px',
            padding: 0,
            textAlign: 'center',
          };

          const lippuStyle = {
            margin: '0px',
            padding: 0,
            textAlign: 'center',
          };
          
          const Header = {
            fontSize: '30px',
            textAlign: 'center',
            color: 'white',
            marginTop: 0,
            border: 5
          };

          const Header2 = {
            fontSize: '20px',
            textAlign: 'center',
            color: 'white',
            marginTop: 30,
            border: 5
          };

          const table = {
            fontSize: '15px',
            textAlign: 'center',
            color: 'white',
            margin: 'auto',
            border: 5
          };

          const pStyle = {
            fontSize: '15px',
            textAlign: 'center',
            color: 'white',
            border: 5
          };
          const buttonStyle = {
              color: 'white',
              marginTop: 5,
              padding: 10,
              backgroundColor: 'green',
              cursor: 'pointer',
          };

//Etsitään event
const checkEvent = async () => {
    try {
        const data = await DatabaseAccessApi.getEventsByEventId(eventId);
        setEvent(data);
        //setTicket(data);
        setDisplayEvent(true);
        setMessage('Lippuja löytyy, myy lippuja luomalla tyhjä tilaus');
        
    } catch (error) {
        console.log(error);
        setMessage('Virhe');
    }
  
// jos tapahtumaa ei anneta, ei näytetä Eventiä, ja annetaan viesti
    if(eventId<=0){
        setDisplayEvent(false);
        setMessage('Tapahtumaa ei annettu');
  }
    
  }

  /*//Etsitään liput eventtiin
const checkEvent = async () => {
  try {
      const data = await DatabaseAccessApi.getEventTicketsByEventId(id);
      setDisplayEvent(true);
      setEvent(data);
      setMessage('Event löytyy, myy lippuja');
      
  } catch (error) {
      console.log(error);
      setMessage('Virhe');
  }

  if(event.length===0){
      setMessage('Tapahtumaa ei annettu');
}
  
}
*/

           /* Lipun hakemiseen tarkoitettu funktio
 function haeLippu(){
    checkEvent();
   }  */

    
    return (
      <body style={divStyle}>
        <div>
              <p style={Header}>Valitse tapahtuma ja hae lipputyypit</p>
              <ListEvents updateId={insertEventId} />
        </div>
              <form style={Header2}>
                <label>
                  Tapahtuman ID:<br></br>
                  <input type="text" value={eventId} onChange={updateId} name="id" />
                </label>
              </form>
              <p><button style={buttonStyle} onClick={checkEvent} >TARKASTA LIPPUTYYPIT</button><br></br>
              </p>
              <div>
              <p style={pStyle}>{message}</p>
              </div>
              {displayEvent && <div style={pStyle}>
                <table style={table}>
                  <h4>TAPAHTUMAN TIEDOT</h4>
                    <tr>
                     <td><b>Tapahtuma:</b> {tapahtuma.event}</td>
                    </tr>
                    <tr>
                      <td><b>Aika:</b> {tapahtuma.datetime}</td>
                    </tr>
                    <tr>
                      <td><b>Paikka:</b> {tapahtuma.eventPlace}</td>
                    </tr>
                    <tr>
                      <td><b>Lippuja jäljellä:</b> {tapahtuma.capacity - tapahtuma.soldTickets}</td>
                    </tr>
                </table>
            </div>}<br></br>
            <MakeOrder tapahtumat={ tapahtuma } />
      </body>
    )
}