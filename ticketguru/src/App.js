import React from "react";
import './App.css';
import SellTicket from './components/SellTicket';
import MakeOrder from './components/MakeOrder';
import TestComponent from "./components/TestComponent.js"
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

        if(code.length===0){
            setMessage('Koodin luku epäonnistui');
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

    if(code === "used"){
      setMessage("Lippu on jo käytetty");
  }
}

 // funktio painikkeen painallukselle (ajaa kummatkin yllä)
function pressButton(){
  checkTicket();
  markTicketUsed();
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
    
    

    return (

            <div style={divStyle}>
            <p style={pStyle}>Welcome to TicketGuru!</p>
            <h4>Hae ja Tarkista lippu</h4>
            <form>
                <label>
                 Lippukoodi:<br></br>
                <input type="text" onChange={updateCode} name="code" />
                </label>
            </form>
            <p><button style={buttonStyle} onClick={pressButton} >TARKISTA</button><br></br> 
            </p>
            {displayTicket && <div>
                Tapahtuma:  {ticket.eventName}<br />
                Lipun koodi:  {ticket.code}<br />
                Käytetty:  {ticket.used ? "kyllä" : "ei"}<br />
                Lipun tyyppi:  {ticket.ticketType}<br />
                <p>{message}</p>
            </div>}<br></br><br></br><br></br>
            <SellTicket/>
            <MakeOrder/>

              
        </div>
    )
    
}
