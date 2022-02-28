package com.parking;

import java.util.ArrayList;

// стратегия оплаты стоянки, если время стоянки превышает максимальное время в тарифной сетке
public interface IPaymentStrategy {
    int calculatePayment(double parkingTimeForPayment, ArrayList<Tariff> tariffs);


}
