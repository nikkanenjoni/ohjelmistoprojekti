package eu.codecache.linko.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.codecache.linko.domain.Orders;
import eu.codecache.linko.domain.Ticket;
import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderDTO;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.TicketRepository;
import eu.codecache.linko.domain.UserAuthorizationRepository;
import eu.codecache.linko.domain.UserEntityRepository;
import eu.codecache.linko.domain.OrderRepository;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	// We need these to add new tickets to an order
	@Autowired
	private TicketOrderRepository toRepo;
	@Autowired
	private TicketRepository tRepo;

	@Autowired
	private UserEntityRepository ueRepo;

	@Autowired
	private UserAuthorizationRepository uaRepo;

	private final String API_BASE = "/api/orders";

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE)
	public @ResponseBody List<Orders> getOrders() {
		return orderRepository.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody Orders getOrder(@PathVariable("id") Long orderID) {
		return orderRepository.findByOrderID(orderID);
	}

	// Now we can POST an empty POST-request and we will receive orderID as response
	// thus we can start adding tickets to the order by id returned
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(API_BASE)
	public @ResponseBody Orders postOrder() throws Exception {
//	public @ResponseBody Orders postOrder(@RequestBody Orders orders) {
		try {
			Orders order = new Orders(LocalDateTime.now());
			orderRepository.save(order);
			return order;
		} catch (Exception e) {

			// response in this case (should it be changed?)
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(API_BASE + "/{id}")
	public @ResponseBody Orders postTicketOrder(@PathVariable("id") Long orderID,
			@Valid @RequestBody TicketOrderDTO ticketOrderDTO) throws Exception {
		/*
		 * We should make sure we have a valid order here! We do not need Admin
		 * authorization here, everybody must be able to make orders and sell tickets
		 */

		Orders order = orderRepository.findByOrderID(orderID);
		Ticket ticket = tRepo.findByTicketID(ticketOrderDTO.getTicketID());
		double price = ticketOrderDTO.getTicketPrice();

		if (order != null) {
			/*
			 * ... and same goes for the ticket
			 */
			TicketOrder ticketOrder = new TicketOrder(order, ticket, price);
			toRepo.save(ticketOrder);
			ticketOrder.generateCode();
			toRepo.save(ticketOrder);
			return order;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");

		}

	}

	// Delete will only delete tickets from order,
	// We DO NOT delete whole orders, instead we make them cancelled,
	// but that will require some tweaks to dokumentation first, so
	// let's not implement that for now

	// Delete ticket from order
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = API_BASE + "/{id}/ticketorder/{id2}", method = RequestMethod.DELETE)
	public @ResponseBody Orders deleteTicketFromOrder(@PathVariable("id") Long orderID,
			@PathVariable("id2") Long ticketOrderID) throws Exception {
		Orders order = orderRepository.findByOrderID(orderID);
		if (order == null) {
			// There is no order with the given id
			// This needs better handling!
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");

		}
		TicketOrder ticketOrder = toRepo.findByTicketOrderID(ticketOrderID);
		if (ticketOrder == null) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
		}
		if (ticketOrder.getOrder().getOrderID() != order.getOrderID()) {
			// We did fetch order and ticketOrder from database, but they don't match
			// this ticket DOES NOT belong to the given order!
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
		} else {
			// this ticket DOES belong to given order, let's remove it from the order
			toRepo.delete(ticketOrder);
		}
		return orderRepository.findByOrderID(orderID);
	}

	// This is a private method for checking to see, if user is actually an admin
	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}

}
