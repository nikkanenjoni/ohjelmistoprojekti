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


  const updatePrice = (event) => {
    setPrice(event.target.value);
} 

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
        setOrder(orderdata);
        setMessage1('Tilaus luotu, voit myydä lipun.');
    } catch (error) {
        console.log(error);
    }

  }


const addTicket = async () => {

    try{
        const data = await DatabaseAccessApi.addTicketsToOrderById(order.orderID, ticket, price);
        console.log(data);
        setTicket(data);
        //setMessage2('Lippu myyty hintaan ' + price + ' euroa');
        
    } catch (error) {
        console.log(error);
    }
}

    return(
    <div>
   
        <div>
        <form>
        <button style={buttonStyle} onClick={newOrder}>LUO TILAUS</button><br></br>
        <button style={buttonStyle} onClick={addTicket}>Valitse lippu</button><br></br>
        <button style={buttonStyle} onClick={addTicket}>Valitse lippu</button><br></br>
        <label><br></br>
                 Lisää Hinta:<br></br>
                <input type="text" onChange={updatePrice} name="price" />
                </label><br></br>
        <br></br>

        {message1}

            </form>
    </div>
    <br></br>
    {message2}
    </div>);
}

export default MakeOrder;