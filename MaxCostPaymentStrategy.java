package com.parking;

import java.util.ArrayList;

public class MaxCostPaymentStrategy implements IPaymentStrategy{
    @Override
    public int calculatePayment(double parkingTimeForPayment, ArrayList<Tariff> tariffs) {
        Tariff tariff = tariffs.stream().filter(t -> t.getTime() >= parkingTimeForPayment).findFirst().
                orElse(tariffs.get(tariffs.size() - 1));
        return tariff.getCost();
    }
}
