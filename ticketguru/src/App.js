
import './App.css';
// import TestComponent from './components/TestComponent';
import { DatabaseAccessApi } from "./classes/DatabaseAccessApi.js";

export default function App(props) {

    const checkTicket = async () => {
        try {
            const data = await DatabaseAccessApi.getOrders();
            console.log(data);
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <div> 
            <h2>Welcome to TicketGuru!</h2><br></br>
            <h4>Hae lippu</h4>
            <form>
                <label>
                 Lippu ID: 
                <input type="text" name="name" />
                </label>
            </form>
            <h4>Tarkista lippu</h4>
            <button onClick={checkTicket}>TARKISTA</button><br></br>  
        </div>
    )
}
