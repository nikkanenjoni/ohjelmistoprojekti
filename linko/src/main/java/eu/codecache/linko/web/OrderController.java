package eu.codecache.linko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.codecache.linko.domain.Orders;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderDTO;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	// We need these to add new tickets to an order
	@Autowired
	private TicketOrderRepository toRepo;
	@Autowired
	private TicketRepository tRepo;

	private final String API_BASE = "/api/orders";

	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody Orders getOrder(@PathVariable("id") Long orderID) {
		return orderRepository.findByOrderID(orderID);
	}

	@PostMapping(API_BASE)
	public @ResponseBody Orders postOrder(@RequestBody Orders orders) {
		orderRepository.save(orders);
		return orders;
	}

	@PostMapping(API_BASE + "/{id}")
	public @ResponseBody Orders postTicketOrder(@PathVariable("id") Long orderID,
			@RequestBody TicketOrderDTO ticketOrderDTO) {
		/*
		 * We should make sure we have a valid order here!
		 */
		Orders order = orderRepository.findByOrderID(orderID);
		/*
		 * ... and same goes for the ticket
		 */
		Ticket ticket = tRepo.findByTicketID(ticketOrderDTO.getTicketID());
		double price = ticketOrderDTO.getTicketPrice();
		TicketOrder ticketOrder = new TicketOrder(order, ticket, price);
		toRepo.save(ticketOrder);
		return order;
	}
	
	/*  SOFIAN SOTKUA TÄSTÄ ETEENPÄIN TICKET ORDER GET JA DELETE 
	
	// Get ticketOrder, first orders
	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody Orders getOrder(@PathVariable("id") Long orderID) {
		return orderRepository.findByOrderID(orderID);
	}
	// Get ticketOrder, then ticketorders, where id2=ticketOrderID
	@GetMapping(API_BASE + "/{id}?{id2}")
	public @ResponseBody TicketOrder getTicketOrder(@PathVariable("id2") Long ticketOrderID, Model model) {
		return toRepo.findByTicketOrderID(ticketOrderID);
	}
	
	// Delete ticketOrder (delete one ticket from ticketorder):
	@RequestMapping(value = API_BASE + "/{id2}?{id}", method = RequestMethod.GET) // {id} is the path variable. you can
	public String deleteTicketOrder(@PathVariable("id") Long ticketOrderID, @PathVariable("id2") Long orderID) {
	toRepo.deleteById(ticketOrderID);
	// alla testailua
	//Order orderID=Order.getOrderID(@PathVariable("id2") Long orderID, Model model) {
	
	return "Ticket removed from order";
}
*/

}
