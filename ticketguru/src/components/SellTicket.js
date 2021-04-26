import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";
import MakeOrder from "./MakeOrder.js"

export default function SellTicket(props) {

        const [id, setId] = React.useState(0);

        // tapahtumamuuttuja tässä komponentissa 
        const [tapahtuma, setEvent] = React.useState({

          ticketID: "",
          //ticketType: "",
          ticketType: "",
          },
           {
            ticketTypeID:0, 
            ticketType:"",
          },
          {
            eventID: id,
            event: "",
            eventPlace: "",
            capacity: 0,
            description: "",
            dateTime: "",
        },
        {
            price: 0.0, 
            description: "",
      });




        const updateId = (event) => {
          setId(event.target.value);
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
        const data = await DatabaseAccessApi.getEventTicketsByEventId(tapahtuma.eventID);
        setEvent(data);
        //setTicket(data);
        setDisplayEvent(true);
        setMessage('Event löytyy, myy lippuja');
        
    } catch (error) {
        console.log(error);
        setMessage('Virhe');
    }
  
    if(tapahtuma.length===0){
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
              <h4>Myy lippuja</h4>
              <form>
                <label>
                  Tapahtuman ID:<br></br>
                  <input type="text" onChange={updateId} name="id" />
                </label>
              </form>
              <p><button style={buttonStyle} onClick={checkEvent} >Hae</button><br></br>
              </p>
              {displayEvent && <div>
                Tapahtuma:  {tapahtuma.event}<br />
                Aika:       {tapahtuma.dateTime}<br />
                Lipputyyppi:{tapahtuma.ticketType}<br />
                Hinta:      {tapahtuma.price}<br />
               <p>{message}</p>
               <MakeOrder tapahtumat={ tapahtuma } />
            </div>}<br></br>
        </div>
    )
}