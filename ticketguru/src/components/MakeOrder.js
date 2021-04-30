import React from 'react';
import { DatabaseAccessApi } from "../classes/DatabaseAccessApi.js";



function MakeOrder(props) {
    return(
    <div>
    {props.tapahtumat.map( tapahtuma => {
    return (
        <p key= {tapahtuma.eventID }>
       <h4>{ tapahtuma.event }</h4>
         { tapahtuma.dateTime } <br/>
       </p>
       );
    })
    }
    <br></br>
    </div>);
}
export default MakeOrder;