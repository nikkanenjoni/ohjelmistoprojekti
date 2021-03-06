package eu.codecache.linko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		Orders order = orderRepository.findByOrderID(orderID);
		Ticket ticket = tRepo.findByTicketID(ticketOrderDTO.getTicketID());
		double price = ticketOrderDTO.getTicketPrice();
		TicketOrder ticketOrder = new TicketOrder(order, ticket, price);
		toRepo.save(ticketOrder);
		return order;
	}
}
