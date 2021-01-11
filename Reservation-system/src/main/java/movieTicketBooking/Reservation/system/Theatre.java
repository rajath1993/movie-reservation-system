package movieTicketBooking.Reservation.system;

public class Theatre {
	
	private String screenNumber;
	private int maxCapacity = 10;
	private int remainingSeats;
	private int numberOfTicketsBooked;
		
	public String getScreenNumber() {
		return screenNumber;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}
	
	
	public Theatre(String screenNumber) {
		this.screenNumber = screenNumber;
		this.remainingSeats = maxCapacity;
		this.numberOfTicketsBooked = 0;
	}
	/*If this method is not synchronized - multiple users will be able to book tickets beyond the max capacity*/
	public synchronized boolean addTicket(int addSeats) {
		if((remainingSeats>=addSeats) &&(addSeats>0)) {
			numberOfTicketsBooked+=addSeats;
			System.out.println(Thread.currentThread().getName()+" Booking confirmed!!");
			remainingSeats -=addSeats;
			return true;
		}else {
			System.out.println(Thread.currentThread().getName()+" Sorry! unable to book ticket at this time");
			return false;
		}
			
	}
	/*Similarly, multiple cancellations will be made even if all the tickets are available for booking*/
	public synchronized boolean cancelTicket(int tickets) {
		if((tickets>0) && (maxCapacity>=tickets) && (numberOfTicketsBooked>=tickets) && (tickets<=remainingSeats)){
			remainingSeats+=tickets;
			System.out.println(Thread.currentThread().getName()+" Booking cancelled");
			return true;
		}else {
			System.out.println(Thread.currentThread().getName()+" Invalid! cancellation cannot be processed!");
			return false;
		}
 
	}
	
	
	

}
