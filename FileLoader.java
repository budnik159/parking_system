package com.parking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FileLoader implements ILoader{
    String pathToFile;

    public FileLoader(String pathToFile){
        this.pathToFile = pathToFile;
    }

    @Override
    public ArrayList<Tariff> getTariffs() {

       /* ArrayList<Integer> time = new ArrayList<>();
        ArrayList<Integer> cost = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))){
            String[] strTime = br.readLine().split(", ");;
            String[] strCost = br.readLine().split(", ");;

            for (String minute : strTime) {
                time.add(Integer.parseInt(minute));
            }

            for (String money : strCost) {
                cost.add(Integer.parseInt(money));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        return null;
    }

}
