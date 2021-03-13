package eu.codecache.linko.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

	// Now we can POST an empty POST-request and we will receive orderID as response
	// thus we can start adding tickets to the order by id returned
	@PostMapping(API_BASE)
	public @ResponseBody Orders postOrder() {
//	public @ResponseBody Orders postOrder(@RequestBody Orders orders) {
		Orders order = new Orders(LocalDateTime.now());
		orderRepository.save(order);
		return order;
	}

	/*
	 * This method allows us to save tickets to an order
	 */
	@PostMapping(API_BASE + "/{id}")
	public @ResponseBody Orders postTicketOrder(@PathVariable("id") Long orderID,
			@RequestBody List<TicketOrderDTO> ticketOrderDTOs) {
		/*
		 * Throw 404 if event isn't found
		 */
		Orders order = orderRepository.findByOrderID(orderID);
		if (order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		/*
		 * go trough tickets in the order and save all valid tickets
		 */
		for (TicketOrderDTO ticketOrderDTO : ticketOrderDTOs) {
			Ticket ticket = tRepo.findByTicketID(ticketOrderDTO.getTicketID());
			if (ticket != null) {
				double price = ticketOrderDTO.getTicketPrice();
				TicketOrder ticketOrder = new TicketOrder(order, ticket, price);
				toRepo.save(ticketOrder);
			}
		}
		return order;
	}

	// Delete will only delete tickets from order,
	// We DO NOT delete whole orders, instead we make them cancelled,
	// but that will require some tweaks to dokumentation first, so
	// let's not implement that for now

	// Delete ticket from order
	@RequestMapping(value = API_BASE + "/{id}/ticketorder/{id2}", method = RequestMethod.DELETE)
	public @ResponseBody Orders deleteTicketFromOrder(@PathVariable("id") Long orderID,
			@PathVariable("id2") Long ticketOrderID) {
		Orders order = orderRepository.findByOrderID(orderID);
		if (order == null) {
			// There is no order with the given id
			// This needs better handling!
			return null;
		}
		TicketOrder ticketOrder = toRepo.findByTicketOrderID(ticketOrderID);
		if (ticketOrder == null) {
			// Now ticketOrder found, better handling needed again!
			return null;
		}
		if (ticketOrder.getOrder().getOrderID() != order.getOrderID()) {
			// We did fetch order and ticketOrder from database, but they don't match
			// this ticket DOES NOT belong to the given order!
			return null;
		} else {
			// this ticket DOES belong to given order, let's remove it from the order
			toRepo.delete(ticketOrder);
		}
		return orderRepository.findByOrderID(orderID);
	}

	/*
	 * SOFIAN SOTKUA TÄSTÄ ETEENPÄIN TICKET ORDER GET JA DELETE
	 * 
	 * // Get ticketOrder, first orders
	 * 
	 * @GetMapping(API_BASE + "/{id}") public @ResponseBody Orders
	 * getOrder(@PathVariable("id") Long orderID) { return
	 * orderRepository.findByOrderID(orderID); } // Get ticketOrder, then
	 * ticketorders, where id2=ticketOrderID
	 * 
	 * @GetMapping(API_BASE + "/{id}?{id2}") public @ResponseBody TicketOrder
	 * getTicketOrder(@PathVariable("id2") Long ticketOrderID, Model model) { return
	 * toRepo.findByTicketOrderID(ticketOrderID); }
	 * 
	 * // Delete ticketOrder (delete one ticket from ticketorder):
	 * 
	 * @RequestMapping(value = API_BASE + "/{id2}?{id}", method = RequestMethod.GET)
	 * // {id} is the path variable. you can public String
	 * deleteTicketOrder(@PathVariable("id") Long
	 * ticketOrderID, @PathVariable("id2") Long orderID) {
	 * toRepo.deleteById(ticketOrderID); // alla testailua //Order
	 * orderID=Order.getOrderID(@PathVariable("id2") Long orderID, Model model) {
	 * 
	 * return "Ticket removed from order"; }
	 */

}
