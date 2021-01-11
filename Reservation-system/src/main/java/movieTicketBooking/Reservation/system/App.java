package movieTicketBooking.Reservation.system;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	        Scanner sc = new Scanner(System.in);
	        final String uuid = UUID.randomUUID().toString().replace("-", "");
	        Theatre theatre = new Theatre(uuid);
	        int count = 0;
	        ArrayList<Integer> addTickets = new ArrayList<Integer>();
	        ArrayList<Integer> cancelTickets = new ArrayList<Integer>();
	        
	        System.out.println("Note: This is a server handling multiple booking and cancellation requests.");
	        System.out.println();
    		System.out.println("For the purpose of simulating multithreading & race conditions: ");
    		System.out.println("We are allowing multiple bookings/cancellation at once: ");
    		System.out.println();
    		
    		while(count!=4) {
    			
	    		System.out.println( "------Choose: 1. Book tickets 2. Cancel Tickets-----");
	    		int input = sc.nextInt();
	    		switch(input) {
	    			case 1:
	    				System.out.println("-----Welcome to AMC booking----");
	    				System.out.println();
	    				System.out.println("Enter the number of tickets to be booked");
	    				addTickets.add(sc.nextInt());
	    				break;
	    			case 2:
	    				System.out.println("Are you sure you want to Cancel? Y/N");
	    				String s = sc.next();
	    				char c = s.toLowerCase().charAt(0);
	    				switch(c) {
	    				case 'y':
	    					System.out.println("Go ahead with cancellation: ");
	    					System.out.println("Enter the number of tickets to be cancelled: ");
	    					cancelTickets.add(sc.nextInt());
	    					break;
	    				case 'n':
	    					break;
	    				}
	    				break;
	    			default:
	    					System.out.println("Invalid entry!");
	    					break;
	    		}
	    		count++;
    		}
    		
    		/*Each thread will be handling a request*/

    		for(int i=0;i<addTickets.size();i++) {
    			Runnable runnable = new MovieBookingThread(theatre,addTickets.get(i),MovieBookingThread.BookingType.ADD_TICKET);
    			Thread t = new Thread(runnable);
    			t.start();
    		}
    		
    		for(int i=0;i<cancelTickets.size();i++) {
    			Runnable runnable = new MovieBookingThread(theatre,cancelTickets.get(i),MovieBookingThread.BookingType.CANCEL_TICKET);
    			Thread t = new Thread(runnable);
    			t.start();
    		}
    }
    
}
