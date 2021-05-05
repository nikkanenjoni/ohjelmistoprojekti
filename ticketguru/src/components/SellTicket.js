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
            margin: '40px',
            border: '5px solid grey',
            padding: 5
          };
          const pStyle = {
            fontSize: '20px',
            textAlign: 'center',
            border: 5
          };
          const buttonStyle = {
              color: 'white',
              marginTop: 5,
              padding: 10,
              backgroundColor: 'Grey',
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
        <div>
              <h3>MYY LIPPUJA</h3>
              <ListEvents updateId={insertEventId} />
              <form>
                <label>
                  Tapahtuman ID:<br></br>
                  <input type="text" value={eventId} onChange={updateId} name="id" />
                </label>
              </form>
              <p><button style={buttonStyle} onClick={checkEvent} >Hae</button><br></br>
              </p>
              <p>{message}</p>
              {displayEvent && <div>
                <h4>TAPAHTUMAN TIEDOT</h4>
                Tapahtuma:  {tapahtuma.event}<br />
                Aika:       {tapahtuma.datetime}<br />
                Paikka:{tapahtuma.eventPlace}<br />
                Lippuja jäljellä:  {tapahtuma.capacity - tapahtuma.soldTickets}<br />
                LippuID:{tapahtuma.ticketID}<br />
                Lipputyyppi:{tapahtuma.ticketType}<br />
            </div>}<br></br>
            <MakeOrder tapahtumat={ tapahtuma } />
        </div>
    )
}