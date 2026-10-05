package ru.university.rogachev.model;

import java.util.List;

public class Train {

    private int id;
    private String number;
    private String TrainName;
    private String TrainWay;
    public List<RoutePoint> Points;

    public Train(int id, String trainName, String trainWay) {
        this.id = id;
        TrainName = trainName;
        TrainWay = trainWay;
    }

    public int getId() {
        return id;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        this.TrainName = trainName;
    }

    public String getTrainWay() {
        return TrainWay;
    }

    public void setTrainWay(String trainName) {
        this.TrainName = trainName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber(){
        return number;
    }
}
