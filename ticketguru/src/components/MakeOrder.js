import React from 'react';
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";
import SellTicket from "./SellTicket.js"

function MakeOrder(props) {
    return(
    <div>
    {props.tapahtumat.map( tapahtuma => {
    return (
        <p key= {tapahtuma.eventID }>
       <h4>{ tapahtuma.event }</h4>
         { tapahtuma.dateTime } <br/>
       </p>);
    })
    }
    </div>);
}
export default MakeOrder;