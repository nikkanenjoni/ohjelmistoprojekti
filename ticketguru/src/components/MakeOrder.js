import React from 'react';
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";



function MakeOrder(props) {

  const [ticket, setTicket] = React.useState('');
  const [price, setPrice] = React.useState('');

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
        setOrder(orderdata, 0 , 0);
        setMessage1('Tilaus luotu, voit myydä lipun.');
    } catch (error) {
        console.log(error);
    }

  }

// Lippuhinnan täyttäminen
  const updatePrice = (event) => {
    setPrice(event.target.value);
} 

// lisätään lippu tilaukseen
const addTicket = async () => {

    try{
        const data = await DatabaseAccessApi.addTicketToOrderById(order.orderID, ticket, price);
        console.log(data);
        setOrder(data);
        //setMessage2('Lippu myyty hintaan ' + price + ' euroa');
        
    } catch (error) {
        console.log(error);
    }
}


// funktio MYY LIPUT JA TULOSTA-painikkeen painallukselle (tälle ei taida olla fetchiä)
const sellOrder = (event) => {
  setDisplayOrder(true);
} 

    return(
    <div>
   
        <div>
        <form>
        <button style={buttonStyle} onClick={newOrder} >LUO TILAUS</button><br></br>
        <p>{ message1 }</p>
        <button style={buttonStyle} onClick={addTicket}>Valitse lippu</button><br></br>
        <button style={buttonStyle} onClick={addTicket}>Valitse lippu</button><br></br>
        <label><br></br>
                 Lisää Hinta:<br></br>
                <input type="text" onChange={updatePrice} name="price" />
                </label><br></br>
        <br></br>
        <button style={buttonStyle} onClick={sellOrder}>VAHVISTA TILAUS JA TULOSTA</button><br></br>

            </form>
    </div>
    <br></br>
    {displayOrder && <div>
                <h4>TAPAHTUMAN TIEDOT</h4>
                Tilausnnumero:  {order.orderID}<br />
                Lipputyyppi:       {order.ticketPrice}<br />
                Hinta:     {order.ticketPrice}<br />
    </div>}
    {message2}
    </div>)
;
}

export default MakeOrder;