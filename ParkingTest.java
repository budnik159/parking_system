package com.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ParkingTest {

    ParkingManager parkingManager;
    ParkingSession parkingSession;

    //Нахождение на парковке 15 мин. Выезд без оплаты
    @Test
    public void enterAndExitIn15Min() {

        MemoryLoader memoryLoader = new MemoryLoader();
        MaxCostPaymentStrategy paymentStrategy = new MaxCostPaymentStrategy();
        parkingManager = new ParkingManager(10, memoryLoader, paymentStrategy);

        //устанавливаем время вьезда на парковку
        Calendar calendar1 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 0);
        DateUtil.getInstance().setCurrentDate(calendar1);
        parkingSession = parkingManager.enterParking("в677не159");
        int ticketNumber = parkingSession.getTicketNumber();

        // устанавливаем время выезда с парковки
        Calendar calendar2 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 15);
        DateUtil.getInstance().setCurrentDate(calendar2);
        assertTrue(parkingManager.tryLeaveParkingWithTicket(ticketNumber));
    }

    //Нахождение на парковке более 15 мин. Выезд без оплаты. Оплата. Выезд в течернии 15 мин. после оплаты
    @Test
    public void enterAndExitMoreThan15Min() {

        MemoryLoader memoryLoader = new MemoryLoader();
        MaxCostPaymentStrategy paymentStrategy = new MaxCostPaymentStrategy();
        parkingManager = new ParkingManager(10, memoryLoader, paymentStrategy);

        //устанавливаем время вьезда на парковку
        Calendar calendar1 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 0);
        DateUtil.getInstance().setCurrentDate(calendar1);
        parkingSession = parkingManager.enterParking("в677не159");
        int ticketNumber = parkingSession.getTicketNumber();

        // устанавливаем время выезда с парковки
        Calendar calendar2 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 16);
        DateUtil.getInstance().setCurrentDate(calendar2);
        assertFalse(parkingManager.tryLeaveParkingWithTicket(ticketNumber));

        try {
            int amount = parkingManager.getRemainingCost(ticketNumber);
            parkingManager.payForParking(ticketNumber, amount);

        }catch (Exception exc){
            exc.printStackTrace();
        }
        assertTrue(parkingManager.tryLeaveParkingWithTicket(ticketNumber));
    }


    //Нахождение на парковке более 15 мин. Оплата. Продолжение нахождения на парковке 20 мин.
    // Попытка выезда. Оплата. Выезд в течении 15 мин. после оплаты.
    @Test
    public void enterAndExitMoreThan15Min_2() {

        MemoryLoader memoryLoader = new MemoryLoader();
        MaxCostPaymentStrategy paymentStrategy = new MaxCostPaymentStrategy();
        parkingManager = new ParkingManager(10, memoryLoader, paymentStrategy);

        //устанавливаем время вьезда на парковку
        Calendar calendar1 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 0);
        DateUtil.getInstance().setCurrentDate(calendar1);
        parkingSession = parkingManager.enterParking("в677не159");
        int ticketNumber = parkingSession.getTicketNumber();

        // устанавливаем время оплаты парковки
        Calendar calendar2 = new GregorianCalendar(2021, Calendar.JULY, 27, 10, 30);
        DateUtil.getInstance().setCurrentDate(calendar2);

        try {
            int amount = parkingManager.getRemainingCost(ticketNumber);
            parkingManager.payForParking(ticketNumber, amount);

        }catch (Exception exc){
            exc.printStackTrace();
        }

        // устанавливаем время выезда с парковки
        Calendar calendar3 = new GregorianCalendar(2021, Calendar.JULY, 27, 11, 0);
        DateUtil.getInstance().setCurrentDate(calendar3);
        assertFalse(parkingManager.tryLeaveParkingWithTicket(ticketNumber));

        // повторная оплата парковки
        try {
            int amount = parkingManager.getRemainingCost(ticketNumber);
            parkingManager.payForParking(ticketNumber, amount);

        }catch (Exception exc){
            exc.printStackTrace();
        }
        assertTrue(parkingManager.tryLeaveParkingWithTicket(ticketNumber));

    }








}
