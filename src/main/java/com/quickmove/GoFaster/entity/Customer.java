package com.quickmove.GoFaster.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private int age;
	    private String gender;
	    private Long mobileNo;
	    private String emailId;
	    private String currentLocation;
        // Customer has multiple bookings
	    @OneToMany(cascade = CascadeType.ALL)
	    private List<Booking> bookingList;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Long getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(Long mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getCurrentLocation() {
			return currentLocation;
		}
		public void setCurrentLocation(String currentLocation) {
			this.currentLocation = currentLocation;
		}
		public List<Booking> getBookingList() {
			return bookingList;
		}
		public void setBookingList(List<Booking> bookingList) {
			this.bookingList = bookingList;
		}
		public Customer(Long id, String name, int age, String gender, Long mobileNo, String emailId,
				String currentLocation, List<Booking> bookingList) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.mobileNo = mobileNo;
			this.emailId = emailId;
			this.currentLocation = currentLocation;
			this.bookingList = bookingList;
		}
		public Customer() {
			super();
		}
		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobileNo="
					+ mobileNo + ", emailId=" + emailId + ", currentLocation=" + currentLocation + ", bookingList="
					+ bookingList + "]";
		}

}
