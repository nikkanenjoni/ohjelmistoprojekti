import React from "react";
import './App.css';
// import TestComponent from './components/TestComponent';
import { DatabaseAccessApi } from "./classes/DatabaseAccessApi.js";

export default function App(props) {

    const [displayTicket, setDisplayTicket] = React.useState(false);
    const [code, setCode] = React.useState('');
    const [ticket, setTicket] = React.useState({
        ticketID: 0,
        eventName: "",
        code: "",
        used: false,
        usedDate: "",
        ticketType: ""
    });

    //const [usedTicket, markUsed] = React.useState(false);
    // errors
    const [message, setMessage] = React.useState('');


    const updateCode = (event) => {
        setCode(event.target.value);
    }


    const checkTicket = async () => {
        try {
            const data = await DatabaseAccessApi.getTicketByCode(code);
            setDisplayTicket(true);
            setTicket(data);
            // merkitään käytetyksi

            setMessage('Lippu OK ja käytetty');
        

        } catch (error) {
            console.log(error);
            setMessage('Lippua ei löydy');
        }
    }
    
  // mark ticket used

  const markTicketUsed = async () => {
    try {
        const data = await DatabaseAccessApi.markTicketUsed(ticket.ticketID, ticket.code);
        console.log(data);

    } catch (error) {
        console.log(error);
    }
}
    
 // TESTING
   /* const ticketState = () => {
      const [isUsed, setUsed] = React.useState(false);
    
      const used = React.useCallback(
        () => setUsed(!isUsed),
        [isUsed, setUsed],
      );
    }*/

    return (
        <div> 
            <div>
            <h2>Welcome to TicketGuru!</h2><br></br>
            <h4>Hae lippu</h4>
            <form>
                <label>
                 Lippukoodi: 
                <input type="text" onChange={updateCode} name="code" />
                </label>
            </form>
            <h4>Tarkista lippu</h4>
            <button onClick={{checkTicket}} >TARKISTA</button><br></br> 
            <button onClick={markTicketUsed} >KÄYTÄ</button><br></br> 
            </div>
            {displayTicket && <div>
                Tapahtuma: {ticket.eventName}<br />
                Lipun koodi: {ticket.code}<br />
                Käytetty: {ticket.used ? "kyllä" : "ei"}<br />
                Lipun tyyppi: {ticket.ticketType}<br />
            </div>}<br></br>

        </div>
    )
}
