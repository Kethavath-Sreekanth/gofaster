package com.quickmove.GoFaster.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickmove.GoFaster.dto.BookVehicleDto;
import com.quickmove.GoFaster.entity.Booking;
import com.quickmove.GoFaster.entity.Customer;
import com.quickmove.GoFaster.entity.Driver;
import com.quickmove.GoFaster.entity.Vehicle;
import com.quickmove.GoFaster.exception.CustomerNotFoundException;
import com.quickmove.GoFaster.exception.DriverMobileNoNotFound;
import com.quickmove.GoFaster.exception.DriverNotFoundException;
import com.quickmove.GoFaster.repository.BookingRepository;
import com.quickmove.GoFaster.repository.CustomerRepository;
import com.quickmove.GoFaster.repository.DriverRepository;
import com.quickmove.GoFaster.util.ResponseStructure;

@Service
public class BookingService {
	@Autowired 
	private CustomerRepository customerRepo;
    @Autowired 
    private DriverRepository driverRepo;
    @Autowired 
    private BookingRepository bookingRepo;


        public ResponseStructure<Booking> bookVehicle(BookVehicleDto bookVehicleDto) throws DriverNotFoundException, CustomerNotFoundException {

            Customer customer = customerRepo.findByMobileNo(Long.parseLong(bookVehicleDto.getCustomerMobileNo()));
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with mobile: " + bookVehicleDto.getCustomerMobileNo());
            }

            Driver driver = driverRepo.findByMobileNo(bookVehicleDto.getDriverMobileNo());
            if (driver == null) {
            	
            	throw new DriverMobileNoNotFound();
            }

            Booking booking = new Booking();
            String status=driver.getStatus();
            System.err.println(status);
            
            if(status.equalsIgnoreCase("booked")) {
            	throw new DriverMobileNoNotFound();
            }
            booking.setCustomer(customer);
            booking.setDriver(driver);
            booking.setSourceLocation(bookVehicleDto.getSourceLocation());
            booking.setDestinationLocation(bookVehicleDto.getDestinationLocation());
            booking.setBookingDate(LocalDateTime.now());
            booking.setDistanceTravelled(10.0);
            booking.setFare(500.0);

            bookingRepo.save(booking);
            customer.getBookingList().add(booking);

           // Vehicle vehicle= new Vehicle();
            //vehicle.getDriver().getBookingList().add(booking);
            
            driver.getBookingList().add(booking);
            driver.setStatus("booked");
            driver.getVehicle().setVehicleavailabilityStatus("booked");

           // Vehicle vehicle= new Vehicle();
            //vehicle.getDriver().getBookingList().add(booking);

            customerRepo.save(customer);
            driverRepo.save(driver);

            return new ResponseStructure<>(201, "Booking created successfully", booking);
             
             
            
        }


		public ResponseStructure<Booking> activeBooking(long mobileNo) {
			ResponseStructure<Booking> structure = new ResponseStructure<>();

		    Booking active = bookingRepo.findActiveBooking(mobileNo);

		    if (active == null) {
		        structure.setStatuscode(404);
		        structure.setMessage("No active booking found for this customer");
		        structure.setData(null);
		        return structure;
		    }

		    structure.setStatuscode(200);
		    structure.setMessage("Active booking fetched successfully");
		    structure.setData(active);

		    return structure;
			
		}
    

    


}
