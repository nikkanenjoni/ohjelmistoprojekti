import React from 'react';
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";



function MakeOrder(props) {

  const [tickets, setTickets] = React.useState([]);
  const [orderId, setOrderId] = React.useState(0);
  const [orderData, setOrderData] = React.useState([]);
  //  const [ticket, setTicket] = React.useState('');
  //  const [price, setPrice] = React.useState('');

  const addTicketToOrder = (ticket) => {
    setTickets([...tickets, ticket]);
  }
  // errors
  const [message1, setMessage1] = React.useState('');

  // errors2
  const [message2, setMessage2] = React.useState('');

  // show order
  const [displayOrder, setDisplayOrder] = React.useState(false);


  const [order, setOrder] = React.useState({
    orderID: 0,
    ticketID: 0,
    ticketPrice: 0.0,
  });

  const pStyle = {
    color: 'white',
    fontSize: '15px',
    textAlign: 'center',
    border: 5
  };

  const buttonStyle = {
    color: 'white',
    marginTop: 5,
    padding: 10,
    backgroundColor: 'Green',
    cursor: 'pointer',
  };

  const Header = {
    fontSize: '20px',
    color: 'white',
    textAlign: 'center',
    border: '3px solid black',
    border: 5
  };

  const newOrder = async () => {
    try {
      const orderData = await DatabaseAccessApi.createOrder();
      //      console.log(orderdata);
      // asetetaan luotu ID lipputilauksen tilausid:ksi
      setOrderId(orderData.orderID)
      //      setOrder(orderdata, 0, 0);
      setMessage1('Tilaus luotu, voit myydä lipun.');
    } catch (error) {
      console.log(error);
    }

  }

  // Lippuhinnan täyttäminen
  //  const updatePrice = (event) => {
  //    setPrice(event.target.value);
  //  }

  // lisätään lippu tilaukseen
  /*  const addTicket = async () => {
  
      try {
        const data = await DatabaseAccessApi.addTicketToOrderById(order.orderID, ticket, price);
        console.log(data);
        setOrder(data);
        //setMessage2('Lippu myyty hintaan ' + price + ' euroa');
  
      } catch (error) {
        console.log(error);
      }
    }
  */

  // funktio MYY LIPUT JA TULOSTA-painikkeen painallukselle (tälle ei taida olla fetchiä)
  const sellOrder = async (event) => {
    var soldTickets = [];
    var ticketIDs = [];
    var ticketPrices = [];
    tickets.map(ticket => (
      soldTickets.push({ "ticketID": ticket.ticketID, "ticketPrice": ticket.price })
    ));
    for (let i = 0; i < soldTickets.length; i++) {
      ticketIDs.push(soldTickets[i].ticketID);
      ticketPrices.push(soldTickets[i].ticketPrice);
    }
    var newOrder = "";
    try {
      newOrder = await DatabaseAccessApi.createOrder();
    } catch (error) {
      console.log(error);
    }
    if (newOrder == null) {
      console.log("Cannot create order");
      return;
    }
    var data;
    try {
      data = await DatabaseAccessApi.addTicketsToOrder(newOrder.orderID, ticketIDs, ticketPrices);
    } catch (error) {
      console.log(error);
    }
    console.log(data);
    try {
      if (data.status === 400) {
        console.log("Virhe!");
      }
      if (data.status === 409) {
        console.log("Loppuunmyyty!");
      }
    } catch {
      return;
    }
    if (data != null) {
      setOrderData(data);
      setDisplayOrder(true);
    }
  }

  return (
    <div>

      <div style={Header}>
        <p>{message1}</p>
        <p style={pStyle}>Valitse lippu: </p>
        {props.tapahtumat.tickets.map(ticket => (
          <div>
            <button style={buttonStyle} onClick={() => addTicketToOrder(ticket)}>{ticket.ticketType.ticketType}</button>
            <span style={{ marginLeft: "1em" }}>{ticket.price} eur</span>
          </div>
        ))}
        {/*          <label><br></br>
                 Lisää Hinta:<br></br>
            <input type="text" onChange={updatePrice} name="price" />
          </label><br></br>
*/}
        <br></br>
        <p style={pStyle}>Ostoskori: </p>
        {tickets.map(ticket => (
          <div>
            {ticket.ticketType.ticketType} - {ticket.price} eur
          </div>
        ))}

        <button style={buttonStyle} onClick={sellOrder}>VAHVISTA TILAUS JA TULOSTA</button><br></br>

      </div>
      <br></br>
      {displayOrder && <div style={pStyle}>
        <p>Tilausvahvistus</p>
                Tilausnnumero:  {orderData.orderID}<br />
        {orderData.tickets.map(ticket => (
          <div>
            {ticket.ticket.ticketType.ticketType}, {ticket.price} eur, code: {ticket.code}
          </div>
        ))}
      </div>}
      {message2}
    </div>)
    ;
}

export default MakeOrder;