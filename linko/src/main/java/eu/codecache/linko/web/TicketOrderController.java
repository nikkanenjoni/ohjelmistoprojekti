package eu.codecache.linko.web;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.UserEntityRepository;

@RestController
public class TicketOrderController {

	/*
	 * This class is capsulated inside this controller to be used only here!
	 */
	private class ClientTicket {
		private String eventName;
		private String code;
		private boolean used;
		private LocalDateTime usedDate;
		private String ticketType;

		public ClientTicket(String eventName, String code, boolean used, LocalDateTime usedDate, String ticketType) {
			this.eventName = eventName;
			this.code = code;
			this.used = used;
			this.usedDate = usedDate;
			this.ticketType = ticketType;
		}

		public String getEventName() {
			return eventName;
		}

		public String getCode() {
			return code;
		}

		public boolean isUsed() {
			return used;
		}

		public LocalDateTime getUsedDate() {
			return usedDate;
		}

		public String getTicketType() {
			return ticketType;
		}

	}

	@Autowired
	private UserEntityRepository ueRepo;

	@Autowired
	private TicketOrderRepository toRepo;

	/*
	 * This controller is for the people at the door, when they need to check if
	 * ticket is valid or now and to mark tickets used
	 */
	private final String API_BASE = "/api/soldtickets";

	/*
	 * This method is for validating a ticket
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE)
	public @ResponseBody ClientTicket getClientTicket(@RequestParam String code) throws Exception {
		TicketOrder ticketOrder = toRepo.findByCode(code);
		if (ticketOrder == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// ok, ticket was found -> let's create a ClientTicket and send response

		// let's get the id's out from the code
		char c = ticketOrder.getCharacter();
		String[] pieces = ticketOrder.getCode().split("" + c);
		long eventID = 0;
		long orderID = 0;
		long ticketID = 0;
		try {
			eventID = Long.valueOf(pieces[0]);
			orderID = Long.valueOf(pieces[1]);
			ticketID = Long.valueOf(pieces[2]);
		} catch (Exception e) {
			// Little humor is always wellcome? :)
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
		}

		// (String eventName, String code, boolean used, LocalDateTime usedDate, String
		// ticketType)
		String eventName = ticketOrder.getTicket().getEvent().getEvent();
		String ticketCode = ticketOrder.getCode();
		boolean used = ticketOrder.isUsed();
		LocalDateTime usedDate = ticketOrder.getUsedDate();
		String ticketType = ticketOrder.getTicket().getTicketType().getTicketType();
		ClientTicket ct = new ClientTicket(eventName, ticketCode, used, usedDate, ticketType);
		return ct;
	}

	/*
	 * This method is for marking ticket used
	 */

	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
