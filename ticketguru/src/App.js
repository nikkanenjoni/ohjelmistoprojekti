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

            setMessage('Lippu löytyy, tarkistus OK');
            
        

        } catch (error) {
            console.log(error);
            setMessage('Lippua ei löydy');
        }

        if(ticket.length===0){
          setMessage('Koodia ei annettu');
    }
        
  }
    
  // mark ticket used

  const markTicketUsed = async () => {
    try {
        const data = await DatabaseAccessApi.markTicketUsed(ticket.ticketID, ticket.code);
        console.log(data);

    } catch (error) {
        console.log(error);
        setMessage("Haku epäonnistui");
        
    }

    if(ticket.used === true){
      setMessage("Lippu on jo käytetty");
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

    // styles
    const divStyle = {
      margin: '40px',
      border: '5px solid grey'
    };
    const pStyle = {
      fontSize: '20px',
      textAlign: 'center',
      border: 5
    };
    const buttonStyle = {
        color: 'white',
        marginTop: 20,
        padding: 10,
        backgroundColor: 'lightGrey',
        cursor: 'pointer',
    };
    
    

    return (

            <div style={divStyle}>
            <p style={pStyle}>Welcome to TicketGuru!</p>
            <h4>Hae ja Tarkista lippu</h4>
            <form>
                <label>
                 Lippukoodi: 
                <input type="text" onChange={updateCode} name="code" />
                </label>
            </form>
            <p><button style={buttonStyle} onClick={ () =>{ checkTicket(); markTicketUsed()} } >TARKISTA</button><br></br> 
            </p>
            {displayTicket && <div>
                Tapahtuma: {ticket.eventName}<br />
                Lipun koodi: {ticket.code}<br />
                Käytetty: {ticket.used ? "kyllä" : "ei"}<br />
                Lipun tyyppi: {ticket.ticketType}<br />
            </div>}<br></br>
              <p>{message}</p>
        </div>
    )
    
}
