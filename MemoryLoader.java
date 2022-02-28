package com.parking;

import java.util.ArrayList;
import java.util.Collections;

public class MemoryLoader implements ILoader{

    ArrayList<Tariff> tariffs = new ArrayList<>();

    public MemoryLoader() {
        tariffs.add(new Tariff(15, 0));
        for (int i = 1; i < 11; i++) {
            tariffs.add(new Tariff(i*60, i*50));
        }

    }

    @Override
    public ArrayList<Tariff> getTariffs() {

        return tariffs;

    }
}
