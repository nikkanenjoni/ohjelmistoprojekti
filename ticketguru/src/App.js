import React from "react";
import './App.css';
import SellTicket from './components/SellTicket';
import MakeOrder from './components/MakeOrder';
import TestComponent from "./components/TestComponent.js"
import { DatabaseAccessApi } from "./classes/DatabaseAccessApi.js";
import gradient from './gradient.jpeg'

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
    const bodyStyle = {
      margin: '0px',
      border: '3px solid black',
      backgroundImage:`url(${gradient})`
    };

    const divStyle = {
      margin: '0px',
      textAlign: 'center'
    };

    const pStyle = {
      fontSize: '30px',
      textAlign: 'center',
      border: 5
    };

    const Header = {
      fontSize: '50px',
      color: 'white',
      textAlign: 'center',
      border: 5
    };

    const Header2 = {
      fontSize: '20px',
      textAlign: 'center',
      color: 'white',
      border: 5
    };

    const Header3 = {
      fontSize: '20px',
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
    
    return (
           <body style={bodyStyle}>
            <div style={divStyle}>
              <p style={Header}>Welcome to TicketGuru!</p>
              <form>
                <label style={Header3}>
                  Syötä lippukoodi:<br></br>
                  <input type="text" onChange={updateCode} name="code" />
                </label>
              </form>
            <p><button style={buttonStyle} onClick={pressButton} >TARKISTA LIPPUKOODI</button><br></br> 
            </p>
            {displayTicket && <div>
                <b>Tapahtuma:</b>  {ticket.eventName}<br />
                <b>Lipun koodi:</b>  {ticket.code}<br />
                <b>Käytetty:</b>  {ticket.used ? "kyllä" : "ei"}<br />
                <b>Lipun tyyppi:</b>  {ticket.ticketType}<br />
                <p>{message}</p>
            </div>}<br></br><br></br><br></br>
            <SellTicket/>

              
          </div>
        </body>
    )
    
}
