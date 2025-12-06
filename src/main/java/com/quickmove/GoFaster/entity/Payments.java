package com.quickmove.GoFaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Payments {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String paymentMode; // CASH, UPI, CARD, WALLET etc.
    private String paymentStatus; // PAID, FAILED, PENDING
    @OneToOne
    private Booking booking;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public Payments(Long id, double amount, String paymentMode, String paymentStatus, Booking booking) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.booking = booking;
	}
	public Payments() {
		super();
	}
	@Override
	public String toString() {
		return "Payments [id=" + id + ", amount=" + amount + ", paymentMode=" + paymentMode + ", paymentStatus="
				+ paymentStatus + ", booking=" + booking + "]";
	}


}
