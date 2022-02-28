package com.parking;

import java.util.Calendar;

public class ParkingSession {

    private Calendar entryDt;
    // хранит дату и время последнего платежа
    private Calendar paymentDt;
    private Calendar exitDt;
    //хранит общую сумму всех платежей за парковку
    private int totalPayment;
    private String carPlateNumber;
    private int ticketNumber;

    public ParkingSession(Calendar entryDt, String carPlateNumber, int ticketNumber) {
        this.entryDt = entryDt;
        this.carPlateNumber = carPlateNumber;
        this.ticketNumber = ticketNumber;
    }

    public Calendar getEntryDt() {
        return entryDt;
    }

    public void setEntryDt(Calendar entryDt) {
        this.entryDt = entryDt;
    }

    public Calendar getPaymentDt() {
        return paymentDt;
    }

    public void setPaymentDt(Calendar paymentDt) {
        this.paymentDt = paymentDt;
    }

    public Calendar getExitDt() {
        return exitDt;
    }

    public void setExitDt(Calendar exitDt) {
        this.exitDt = exitDt;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

}
