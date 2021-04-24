package eu.codecache.linko.web;

import java.awt.image.BufferedImage;
import java.security.Principal;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import eu.codecache.linko.domain.TicketOrder;
import eu.codecache.linko.domain.TicketOrderRepository;
import eu.codecache.linko.domain.UserEntityRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TicketOrderController {

	/*
	 * This class is capsulated inside this controller to be used only here!
	 */
	private class ClientTicket {
		private long ticketID;
		private String eventName;
		private String code;
		private boolean used;
		private LocalDateTime usedDate;
		private String ticketType;

		public ClientTicket(long ticketID, String eventName, String code, boolean used, LocalDateTime usedDate,
				String ticketType) {
			this.ticketID = ticketID;
			this.eventName = eventName;
			this.code = code;
			this.used = used;
			this.usedDate = usedDate;
			this.ticketType = ticketType;
		}

		public long getTicketID() {
			return ticketID;
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

	/*
	 * this is also for this controller only, I like to glue stuff together do I? ;)
	 */
	private static class PatchBody {
		private String code;

		public PatchBody() {

		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
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
		long ticketOrderID = ticketOrder.getTicketOrderID();
		String eventName = ticketOrder.getTicket().getEvent().getEvent();
		String ticketCode = ticketOrder.getCode();
		boolean used = ticketOrder.isUsed();
		LocalDateTime usedDate = ticketOrder.getUsedDate();
		String ticketType = ticketOrder.getTicket().getTicketType().getTicketType();
		ClientTicket ct = new ClientTicket(ticketOrderID, eventName, ticketCode, used, usedDate, ticketType);
		return ct;
	}

	/*
	 * This method is for marking ticket used
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = API_BASE + "/{id}", method = RequestMethod.PATCH)
	public @ResponseBody ClientTicket markUsed(@PathVariable("id") Long ticketOrderID,
			@Valid @RequestBody PatchBody patchBody) {
		// Make sure the id actually exists in the database
		TicketOrder ticketOrder = toRepo.findByTicketOrderID(ticketOrderID);
		if (ticketOrder == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Make sure ticket is not already used
		if(ticketOrder.isUsed()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		// Make sure the code in body is the code for the ticket
		if (!ticketOrder.getCode().equals(patchBody.getCode())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		// Now formalities are taken care of, let's mark the ticket used and return it
		ticketOrder.setUsed(true);
		ticketOrder.setUsedDate(LocalDateTime.now());
		toRepo.save(ticketOrder);
		String eventName = ticketOrder.getTicket().getEvent().getEvent();
		String ticketType = ticketOrder.getTicket().getTicketType().getTicketType();
		return new ClientTicket(ticketOrderID, eventName, ticketOrder.getCode(), true, ticketOrder.getUsedDate(),
				ticketType);
	}

	/*
	 * This method is for returning QR-code
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(API_BASE + "/{id}")
	public @ResponseBody BufferedImage getQRCode(@PathVariable("id") long ticketOrderID) throws Exception {
		TicketOrder ticketOrder = toRepo.findByTicketOrderID(ticketOrderID);
		if (ticketOrder == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return this.generateQRCodeImage(ticketOrder.getCode());
	}

	// Copy & paste
	// https://www.baeldung.com/java-generating-barcodes-qr-codes
	private BufferedImage generateQRCodeImage(String code) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, 200, 200);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	private boolean isAdmin(Principal p) {
		return ueRepo.findByUsername(p.getName()).getUserAuth().getAuthorization().equals("ADMIN");
	}
}
