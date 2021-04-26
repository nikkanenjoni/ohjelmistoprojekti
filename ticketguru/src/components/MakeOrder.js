import React from 'react';
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";



function MakeOrder(props) {

  const [ticketID, setTicketID] = React.useState('');

  const [price, setPrice] = React.useState('');

  // errors
  const [message1, setMessage1] = React.useState('');

  // errors2
  const [message2, setMessage2] = React.useState('');

  const [order, setOrder] = React.useState('');

  // show order


    
  const updateId = (event) => {
    setTicketID(event.target.value);
}

  const updatePrice = (event) => {
    setPrice(event.target.value);
} 

  const [ticket, setTicket] = React.useState({
    orderId: order,
    ticketId: ticketID,
    ticketPrice: price,
  });


const buttonStyle = {
  color: 'white',
  marginTop: 5,
  padding: 10,
  backgroundColor: 'Green',
  cursor: 'pointer',
};

  const newOrder = async () => {
    try {
        const orderdata = await DatabaseAccessApi.createOrder();
        console.log(orderdata);
        // asetetaan luotu ID lipputilauksen tilausid:ksi
        setOrder(orderdata);
        setMessage1('Tilaus luotu, voit myydä lipun.');
    } catch (error) {
        console.log(error);
    }

  }


const addTicket = async () => {

    try{
        const data = await DatabaseAccessApi.addTicketsToOrderById(ticket.orderId, ticket.ticketId, ticket.ticketPrice);
        console.log(data);
        setTicket(data);
        setMessage2('Lippu myyty hintaan ' + price + ' euroa');
        
    } catch (error) {
        console.log(error);
    }
}

    return(
    <div>
   
        <div>
        <button style={buttonStyle} onClick={newOrder}>Luo tilaus</button><br></br>
        <form><br></br>
        {message1}
                <label>
                 Lisää LippuID:<br></br>
                <input type="text" onChange={updateId} name="ticketID" />
                </label><br></br>
                <label>
                 Lisää Hinta:<br></br>
                <input type="text" onChange={updatePrice} name="price" />
                </label><br></br>
            </form>
        <button style={buttonStyle} onClick={addTicket}>Osta lippu</button>
    </div>
    <br></br>
    {message2}
    </div>);
}

export default MakeOrder;