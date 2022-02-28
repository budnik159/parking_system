package com.parking;

import java.util.*;
import java.util.stream.Collectors;

//TODO: отдельные классы FileLoader, MemoryLoader, интерфейс LoaderTariff (метод getTariffs)
//TODO: Стратегия расчета стоимости стоянки если выше тарифной сетки
public class ParkingManager {

    private ArrayList<ParkingSession> activeParkingSession = new ArrayList<>();
    private ArrayList<ParkingSession> closedParkingSession = new ArrayList<>();
    private ArrayList<ParkingSession> allParkingSession = new ArrayList<>();
    private final int parkingCapacity;
    private final ArrayList<Tariff> tariffs;
    private final int freeLeavePeriod;
    private IPaymentStrategy paymentStrategy;

    public ArrayList<Tariff> getTariff() {
        return tariffs;
    }

    //TODO: передавать сюда ILoader вместо Tariff
    public ParkingManager(int parkingCapacity, ILoader loader, IPaymentStrategy paymentStrategy) {
        this.parkingCapacity = parkingCapacity;
        this.tariffs = loader.getTariffs();
        this.paymentStrategy = paymentStrategy;
        freeLeavePeriod = tariffs.get(0).getTime();

    }

    public ParkingSession enterParking(String carPlateNumber) {
        if ((parkingCapacity == activeParkingSession.size()) || (activeParkingSession.stream().anyMatch(t -> t.getCarPlateNumber().equals(carPlateNumber))))
            return null;

        int ticketNumber = getNewTicketNumber();
        ParkingSession parkingSession = new ParkingSession(DateUtil.getInstance().getCurrentDate(), carPlateNumber, ticketNumber);
        activeParkingSession.add(parkingSession);
        allParkingSession.add(parkingSession);

        return parkingSession;
    }


    public int getNewTicketNumber() {
        List<ParkingSession> sortedAPS = activeParkingSession.stream().sorted(Comparator.comparing(ParkingSession::getTicketNumber)).collect(Collectors.toList());

        for (int i = 0; i < sortedAPS.size(); i++) {
            if (i != sortedAPS.get(i).getTicketNumber()) {
                return i;
            }
        }
        return sortedAPS.size();
    }

    public boolean tryLeaveParkingWithTicket(int ticketNumber) {
        ParkingSession parkingSession = getSessionByTicketNumber(ticketNumber);
        if (checkTimePeriod(parkingSession)) {
            parkingSession.setExitDt(DateUtil.getInstance().getCurrentDate());
            activeParkingSession.remove(parkingSession);
            closedParkingSession.add(parkingSession);
            return true;
        }

        return false;
    }


    private boolean checkTimePeriod(ParkingSession parkingSession) {
        Calendar timeNow = DateUtil.getInstance().getCurrentDate();
        Calendar timePay = (parkingSession.getPaymentDt() != null) ? parkingSession.getPaymentDt() : parkingSession.getEntryDt();

        return TimeSpan.diffDates(timePay, timeNow).getTotalMinutes() <= freeLeavePeriod;
    }

    public int getRemainingCost(int ticketNumber) throws Exception {
        ParkingSession parkingSession = getSessionByTicketNumber(ticketNumber);

        if (parkingSession == null) {
            throw new Exception("Билет с указанным номером не существует");
        }

        Calendar timePay = parkingSession.getPaymentDt() != null ? parkingSession.getPaymentDt() : parkingSession.getEntryDt();
        TimeSpan span = TimeSpan.diffDates(timePay, DateUtil.getInstance().getCurrentDate());
        double parkingTimeForPayment = span.getTotalMinutes();

        return paymentStrategy.calculatePayment(parkingTimeForPayment, tariffs);
    }

    public int calculatePayment(double parkingTimeForPayment) {
        Tariff tariff = tariffs.stream().filter(t -> t.getTime() >= parkingTimeForPayment).findFirst().
                orElse(tariffs.get(tariffs.size() - 1));
        return tariff.getCost();
    }

    public void payForParking(int ticketNumber, int amount) {
        ParkingSession parkingSession = getSessionByTicketNumber(ticketNumber);
        parkingSession.setTotalPayment(parkingSession.getTotalPayment() + amount);
        parkingSession.setExitDt(DateUtil.getInstance().getCurrentDate());
        parkingSession.setPaymentDt(DateUtil.getInstance().getCurrentDate());
    }


    public ParkingSession getSessionByTicketNumber(int ticketNumber) {
        return activeParkingSession.stream().filter(t -> t.getTicketNumber() == ticketNumber).findFirst().orElse(null);

    }

}
