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

    const [used, isUsed] = React.useState(false);


    const updateCode = (event) => {
        setCode(event.target.value);
    }


    const checkTicket = async () => {
        try {
            const data = await DatabaseAccessApi.getTicketByCode(code);
            setDisplayTicket(true);
            setTicket(data);

        } catch (error) {
            console.log(error);
        }
    }

  // merkitään lippu käytetyksi samalla kun haetaan
    const setUsed = async () => {
      try {
          const data = await DatabaseAccessApi.markTicketUsed(data.orderID, data.code);
          console.log(data);
          isUsed(!used)
      } catch (error) {
          console.log(error);
      }
  }

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
                 Lippu ID: 
                <input type="text" onChange={updateCode} name="code" />
                </label>
            </form>
            <h4>Tarkista lippu</h4>
            <button onClick={checkTicket}>TARKISTA</button><br></br> 
            <button onClick={setUsed}>MERKITSE KÄYTETYKSI</button><br></br> 
            </div>
            {displayTicket && <div>
                Tapahtuma: {ticket.eventName}<br />
                Lipun koodi: {ticket.code}<br />
                Käytetty: {ticket.used ? "kyllä" : "ei"}<br />
                Lipun tyyppi: {ticket.ticketType}<br />
            </div>}
        </div>
    )
}
