import React from "react";
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";

export default function SellTicket(props) {

        // tapahtumamuuttuja tässä komponentissa 
        const [event, setEvent] = React.useState({
            eventID: 0,
            event: "",
            eventPlace: "",
            capacity: 0,
            description: "",
            dateTime: "" 
        });

        // tapahtumamuuttuja, joka näyttää tapahtuman
        const [displayEvent, setDisplayEvent] = React.useState(false);
           // errors
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
        const data = await DatabaseAccessApi.getEventsByEventId(event.eventID);
        setDisplayEvent(true);
  
        setMessage('Event löytyy, myy lippuja');
        
    } catch (error) {
        console.log(error);
        setMessage('Tapahtumaa ei löydy');
    }
  
    if(event.length===0){
        setMessage('Tapahtumaa ei annettu');
  }
    
  }

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
                  <input type="text" name="tapahtuma"/>
                </label>
              </form>
              <p><button style={buttonStyle} onClick={checkEvent} >Hae</button><br></br>
              </p>
              {displayEvent && <div>
                Tapahtuma: <br />
            </div>}<br></br>
        </div>
    )
}