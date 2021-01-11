package movieTicketBooking.Reservation.system;

public class MovieBookingThread implements Runnable {
	

	
	public static enum BookingType {
		ADD_TICKET(1), CANCEL_TICKET(2);
 
		private BookingType(int value) {
		}
	};
	
	private Theatre theatre;
	private int numberOfSeatBooking;
	private BookingType bookingType;
	
	public MovieBookingThread(Theatre theatre, int numberOfSeatBooking, BookingType bookingType) {
		this.theatre = theatre;
		this.numberOfSeatBooking = numberOfSeatBooking;
		this.bookingType = bookingType;
	}

	public void run() {
		switch(bookingType) {
			case ADD_TICKET:
				theatre.addTicket(numberOfSeatBooking);
				break;
			case CANCEL_TICKET:
				theatre.cancelTicket(numberOfSeatBooking);
				break;
		}
		
	}
	

}
